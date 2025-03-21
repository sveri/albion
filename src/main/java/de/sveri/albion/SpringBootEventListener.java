package de.sveri.albion;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

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
			.build();

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) throws InterruptedException, IOException {
		log.info("Context refreshed.");

		nc = Nats.connect("nats://public:thenewalbiondata@nats.albion-online-data.com:34222");

		Dispatcher dispatcher = nc.createDispatcher((msg) -> {
//			System.out.printf("%s on subject %s\n", new String(msg.getData(), StandardCharsets.UTF_8),
//					msg.getSubject());

//					JsonValue jsonValue = JsonParser.parse(msg.getSubject());
//					System.out.println(jsonValue.);
			try {
//				JsonMapper.builder().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true):
//				mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
				List<MarketOrder> orders = mapper.readerForListOf(MarketOrder.class).readValue(msg.getData());
				log.info(orders.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

//		dispatcher.subscribe("markethistories.deduped.*");

		dispatcher.subscribe("marketorders.deduped.*");

//        	 Thread.sleep(10000);

//        }
	}

	@PreDestroy
	public void shutdown() throws InterruptedException {
		nc.close();
		log.info("Closed NATS");
	}

}
