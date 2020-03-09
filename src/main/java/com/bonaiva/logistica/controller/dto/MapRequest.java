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
public class MapRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Size(message = "Atributo 'origem' deve conter 1 caractere", min = 1, max = 1)
	@NotBlank(message = "Atributo 'origem' é obrigatório")
	@JsonProperty("origem")
	private String origin;
	
	@Size(message = "Atributo 'destino' deve conter 1 caractere", min = 1, max = 1)
	@NotBlank(message = "Atributo 'destino' é obrigatório")
	@JsonProperty("destino")
	private String destination;
	
	@Positive(message = "Atributo 'distancia' deve ser positivo")
	@NotNull(message = "Atributo 'distancia' é obrigatório")
	@JsonProperty("distancia")
	private Integer distance;

}
