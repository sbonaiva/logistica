package com.bonaiva.logistica.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bonaiva.logistica.controller.dto.RouteRequest;
import com.bonaiva.logistica.controller.dto.RouteResponse;
import com.bonaiva.logistica.mapper.RouteMapper;
import com.bonaiva.logistica.service.RouteService;
import com.bonaiva.logistica.service.model.CalculateRoute;
import com.bonaiva.logistica.service.model.CalculatedRoute;

@RestController
@RequestMapping("rotas")
public class RouteController {
	
	private final RouteService routeService;
	
	@Autowired
	public RouteController(final RouteService routeService) {
		this.routeService = routeService;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public RouteResponse calculate(@Valid @RequestBody RouteRequest routeRequest) {
		
		final CalculateRoute calculateRoute = RouteMapper.toModel(routeRequest);
		
		final CalculatedRoute calculatedRoute = this.routeService.calculate(calculateRoute);
		
		return RouteMapper.toDTO(calculatedRoute);
	}

}
