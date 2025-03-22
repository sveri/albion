package de.sveri.albion.db;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Service
public class AlbionMetadataImporter {

	ObjectMapper mapper = JsonMapper.builder().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	WorldRepository worldRepository;

	public void importMetaData() throws IOException {
//		ClassLoader classLoader = getClass().getClassLoader();
//
//		File itemsFile = new File(classLoader.getResource("items.json").getFile());
//		List<Item> items = mapper.readerForListOf(Item.class).readValue(itemsFile);
//
//		itemRepository.insertItems(items);

//		File worldsFile = new File(classLoader.getResource("world.json").getFile());
//		List<World> worlds = mapper.readerForListOf(World.class).readValue(worldsFile);
//
//		worldRepository.insertWorld(worlds);
	}

}
