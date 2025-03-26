package de.sveri.albion.entity.recipes;

import java.util.List;

public record Item(List<GenericItem> trackingItem, List<GenericItem> equipmentitem, List<GenericItem> weapon,
		List<GenericItem> mount, List<GenericItem> furnitureitem, List<GenericItem> journalitem,
		List<GenericItem> transformationweapon, List<GenericItem> crystalleagueitem, List<GenericItem> siegebanner) {

}
