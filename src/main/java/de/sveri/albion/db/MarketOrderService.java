package de.sveri.albion.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.sveri.albion.entity.MarketOrder;

@Service
public class MarketOrderService {

	@Autowired
	MarketOrderRepository repository;

	@Transactional
	public void insertOrders(List<MarketOrder> orders) {
		repository.insertOrders(orders);
	}

}
