package de.sveri.albion.entity;

public record MarketHistory(long id, int itemAmount, int silverAmount, int locationid, int qualityLevel,
		int enchantmentLevel, int unitPriceSilver, int amount, String auctionType, String expires) {

}
