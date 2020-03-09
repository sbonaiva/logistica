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
public class CalculateRoute implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String origin;
	private String destination;
	private Integer performance;
	private Double price;

}
