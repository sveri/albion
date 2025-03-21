package de.sveri.albion.entity;

public record MarketOrder(long id, String itemTypeId, String itemGroupTypeId, int locationid, int qualityLevel,
		int enchantmentLevel, int unitPriceSilver, int amount, String auctionType, String expires) {

}
