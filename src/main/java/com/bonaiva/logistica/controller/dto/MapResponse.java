package com.bonaiva.logistica.controller.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MapResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("origem")
	private String origin;
	
	@JsonProperty("destino")
	private String destination;
	
	@JsonProperty("distancia")
	private Double distance;

}
