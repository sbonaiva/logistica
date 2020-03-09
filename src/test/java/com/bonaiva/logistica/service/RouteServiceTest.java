package com.bonaiva.logistica.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bonaiva.logistica.BaseTest;
import com.bonaiva.logistica.service.exception.RouteNotExistsException;
import com.bonaiva.logistica.service.model.CalculateRoute;
import com.bonaiva.logistica.service.model.CalculatedRoute;

@DisplayName("Teste operações do serviço de mapas")
public class RouteServiceTest extends BaseTest {
	
	@Autowired
	private MapService mapService;
	
	@Autowired
	private RouteService routeService;
	
	@BeforeEach
	public void beforeEach() {
		this.mapService.getMaps().clear();
		this.mapService.getRelationships().clear();
	}
	
	@Test
	@DisplayName("Calcula melhor rota com sucesso")
	public void tryToCalculateSuccessfully() {
		
		this.mapService.add(buildMap("A", "B", 10));
		this.mapService.add(buildMap("B", "D", 15));
		this.mapService.add(buildMap("A", "C", 20));
		this.mapService.add(buildMap("C", "D", 30));
		this.mapService.add(buildMap("B", "E", 50));
		this.mapService.add(buildMap("D", "E", 30));
		
		final CalculateRoute calculateRoute = CalculateRoute
				.builder()
				.origin("A")
				.destination("D")
				.performance(10)
				.price(2.50)
				.build();
		
		final CalculatedRoute calculatedRoute = this.routeService.calculate(calculateRoute);
		
		Assertions.assertNotNull(calculatedRoute, "Resultado do cálculo de rota não pode ser nulo");
		Assertions.assertEquals("A B D", calculatedRoute.getRoute(), "Rota diferente da esperada");
		Assertions.assertEquals(5.00, calculatedRoute.getCost(), "Custo diferente do esperado");
	}
	
	@Test
	@DisplayName("Tenta calcular sem mapa de origem existente")
	public void tryToCalculateWithoutSourceMap() {
		
		this.mapService.add(buildMap("A", "B", 10));
		this.mapService.add(buildMap("B", "D", 15));
		
		final CalculateRoute calculateRoute = CalculateRoute
				.builder()
				.origin("C")
				.destination("D")
				.performance(10)
				.price(2.50)
				.build();
		
		Assertions.assertThrows(
				RouteNotExistsException.class, 
				() -> this.routeService.calculate(calculateRoute),
				"Exceção não lançada"
		);
	}
	
	@Test
	@DisplayName("Tenta calcular sem mapa de destino existente")
	public void tryToCalculateWithoutTargetMap() {
		
		this.mapService.add(buildMap("A", "B", 10));
		this.mapService.add(buildMap("B", "D", 15));
		
		final CalculateRoute calculateRoute = CalculateRoute
				.builder()
				.origin("A")
				.destination("J")
				.performance(10)
				.price(2.50)
				.build();
		
		Assertions.assertThrows(
				RouteNotExistsException.class, 
				() -> this.routeService.calculate(calculateRoute),
				"Exceção não lançada"
		);
	}

}
