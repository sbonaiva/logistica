package com.bonaiva.logistica.controller.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RouteResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("rota")
	private String route;
	
	@JsonProperty("custo")
	private Double cost;

}
