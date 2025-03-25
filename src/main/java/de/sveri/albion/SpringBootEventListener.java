package de.sveri.albion;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import de.sveri.albion.db.MarketHistoryRepository;
import de.sveri.albion.db.MarketOrderService;
import de.sveri.albion.entity.MarketHistoryNats;
import de.sveri.albion.entity.MarketOrder;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import jakarta.annotation.PreDestroy;

@Component
public class SpringBootEventListener {

	private static final Log log = LogFactory.getLog(SpringBootEventListener.class);

	private Connection nc;

	ObjectMapper mapper = JsonMapper.builder().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();

	@Autowired
	MarketOrderService marketOrderService;

	@Autowired
	MarketHistoryRepository marketHistoryRepository;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) throws InterruptedException, IOException {
		log.info("Context refreshed.");

		nc = Nats.connect("nats://public:thenewalbiondata@nats.albion-online-data.com:34222");

		Dispatcher marketOrderDispatcher = nc.createDispatcher((msg) -> {
			try {
				List<MarketOrder> orders = mapper.readerForListOf(MarketOrder.class).readValue(msg.getData());
				marketOrderService.insertOrders(orders);
				log.trace(orders.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
//		marketOrderDispatcher.subscribe("marketorders.deduped.*");

		Dispatcher marketHistoryDispatcher = nc.createDispatcher((msg) -> {
			try {
				MarketHistoryNats marketHistory = mapper.readValue(msg.getData(), MarketHistoryNats.class);
				marketHistoryRepository.insertMarketHistory(marketHistory);
				log.info(marketHistory.marketHistories().get(0).getHumanReadableTime());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
//		marketHistoryDispatcher.subscribe("markethistories.deduped");

	}

	@PreDestroy
	public void shutdown() throws InterruptedException {
		nc.close();
		log.info("Closed NATS");
	}

}
