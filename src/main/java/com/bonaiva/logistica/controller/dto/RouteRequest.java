package com.bonaiva.logistica.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Size(message = "Atributo 'origem' deve conter 1 caractere", min = 1, max = 1)
	@NotBlank(message = "Atributo 'origem' é obrigatório")
	@JsonProperty("origem")
	private String origin;
	
	@Size(message = "Atributo 'destino' deve conter 1 caractere", min = 1, max = 1)
	@NotBlank(message = "Atributo 'destino' é obrigatório")
	@JsonProperty("destino")
	private String destination;
	
	@Positive(message = "Atributo 'autonomia' deve ser positivo")
	@NotNull(message = "Atributo 'autonomia' é obrigatório")
	@JsonProperty("autonomia")
	private Integer performance;
	
	@Positive(message = "Atributo 'valorLitro' deve ser positivo")
	@NotNull(message = "Atributo 'valorLitro' é obrigatório")
	@JsonProperty("valorLitro")
	private Double price;

}
