package de.sveri.albion.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LocalizedNames(@JsonProperty("EN-US") String en, @JsonProperty("DE-DE") String de) {

}
