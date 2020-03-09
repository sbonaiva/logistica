package com.bonaiva.logistica.controller.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("mensagem")
	private String message;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("erro")
	private String error;

	@JsonProperty("timestamp")
	private Long timestamp;

	@JsonProperty("path")
	private String path;

}
