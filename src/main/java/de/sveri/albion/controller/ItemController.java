package de.sveri.albion.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.sveri.albion.entity.Item;
import de.sveri.albion.entity.recipes.ItemRecipes;
import de.sveri.albion.service.ObjectMapperProvider;

@RestController
public class ItemController {

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("/api/items")
	public List<Item> getItems() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();

		File itemsFile = new File(classLoader.getResource("items.json").getFile());
		return ObjectMapperProvider.getMapper().readerForListOf(Item.class).readValue(itemsFile);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping("/api/itemrecipes")
	public ItemRecipes getItemRecipes() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();

		File itemsFile = new File(classLoader.getResource("item_recipes.json").getFile());
		return ObjectMapperProvider.getMapper().readValue(itemsFile, ItemRecipes.class);
	}

}
