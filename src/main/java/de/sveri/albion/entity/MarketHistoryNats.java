package de.sveri.albion.entity;

import java.util.List;

public record MarketHistoryNats(long albionId, int locationid, int qualityLevel, int timescale,
		List<MarketHistory> marketHistories, String albionIdString) {

}
