package de.sveri.albion.service;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class ObjectMapperProvider {

	private static final ObjectMapper mapper = JsonMapper.builder()
			.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
			.configure(Feature.INCLUDE_SOURCE_IN_LOCATION, true).build();

	public static ObjectMapper getMapper() {
		return mapper;
	}

}
