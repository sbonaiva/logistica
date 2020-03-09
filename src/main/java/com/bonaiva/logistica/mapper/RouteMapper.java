package com.bonaiva.logistica.mapper;

import org.modelmapper.ModelMapper;

import com.bonaiva.logistica.controller.dto.RouteRequest;
import com.bonaiva.logistica.controller.dto.RouteResponse;
import com.bonaiva.logistica.service.model.CalculateRoute;
import com.bonaiva.logistica.service.model.CalculatedRoute;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RouteMapper {
	
	private static final ModelMapper MAPPER = new ModelMapper();
	
	public static CalculateRoute toModel(final RouteRequest routeRequest) {
		return MAPPER.map(routeRequest, CalculateRoute.class);
	}
	
	public static RouteResponse toDTO(final CalculatedRoute calculatedRoute) {
		return MAPPER.map(calculatedRoute, RouteResponse.class);
	}

}
