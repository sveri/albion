package de.sveri.albion.entity.recipes;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Craftresource(@JsonProperty("@uniquename") String uniqueName, @JsonProperty("@count") String count) {

}
