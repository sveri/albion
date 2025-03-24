package de.sveri.albion.entity;

import java.sql.Timestamp;

import de.sveri.albion.db.MarketHistoryRepository;

public record MarketHistory(int itemAmount, int silverAmount, long timestamp) {

	public String getHumanReadableTime() {
		return new Timestamp((timestamp / 10000) - MarketHistoryRepository.TICK_MINUITOR).toString();
	}

}
