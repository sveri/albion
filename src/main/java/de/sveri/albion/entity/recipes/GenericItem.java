package de.sveri.albion.entity.recipes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GenericItem(@JsonProperty("@uniquename") String uniquename,
		List<Craftingrequirements> craftingrequirements) {

}
