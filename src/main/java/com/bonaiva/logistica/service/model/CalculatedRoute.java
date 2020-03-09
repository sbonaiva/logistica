package com.bonaiva.logistica.service.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculatedRoute implements Serializable {

	private static final long serialVersionUID = 1L;

	private String route;
	private Double cost;

}
