package com.bonaiva.logistica.service;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bonaiva.logistica.BaseTest;
import com.bonaiva.logistica.service.model.AddedMap;

@DisplayName("Teste operações do serviço de mapas")
public class MapServiceTest extends BaseTest {
	
	@Autowired
	private MapService mapService;
	
	@BeforeEach
	public void beforeEach() {
		this.mapService.getMaps().clear();
		this.mapService.getRelationships().clear();
	}
	
	@Test
	@DisplayName("Cria mapa com sucesso")
	public void tryToCreateMapSuccessfully() {
		
		final AddedMap addedMap = this.mapService.add(buildMap("A", "B", 12));
		
		Assertions.assertNotNull(addedMap, "Mapa cadastrado não pode ser nulo");
		Assertions.assertEquals("A", addedMap.getOrigin(), "Origem diferente da esperada");
		Assertions.assertEquals("B", addedMap.getDestination(), "Destino diferente do esperado");
		Assertions.assertEquals(12, addedMap.getDistance(), "Distância diferente da esperada");
	}
	
	@Test
	@DisplayName("Recupera mapa com sucesso")
	public void tryToRetrieveMapsSuccessfully() {
		
		this.mapService.add(buildMap("A", "B", 10));
		this.mapService.add(buildMap("B", "D", 15));
		this.mapService.add(buildMap("A", "C", 20));
		this.mapService.add(buildMap("C", "D", 30));
		this.mapService.add(buildMap("B", "E", 50));
		this.mapService.add(buildMap("D", "E", 30));
		
		final Set<String> actualMaps = this.mapService.getMaps();
		
		Assertions.assertTrue(actualMaps.contains("A"), "Valor A ausente");
		Assertions.assertTrue(actualMaps.contains("B"), "Valor B ausente");
		Assertions.assertTrue(actualMaps.contains("C"), "Valor C ausente");
		Assertions.assertTrue(actualMaps.contains("D"), "Valor D ausente");
		Assertions.assertTrue(actualMaps.contains("E"), "Valor E ausente");
	}
	
	@Test
	@DisplayName("Recupera relacionamentos com sucesso")
	public void tryToRetrieveRelationShipsSuccessfully() {
		
		this.mapService.add(buildMap("A", "B", 10));
		this.mapService.add(buildMap("B", "C", 12));
		this.mapService.add(buildMap("B", "D", 15));
		
		Map<String, Map<String, Integer>> relationships = this.mapService.getRelationships();
		
		Assertions.assertTrue(relationships.containsKey("A"), "Chave A ausente");
		Assertions.assertTrue(relationships.containsKey("B"), "Chave B ausente");
		
		Assertions.assertEquals(1, relationships.get("A").size(), "Quantidade de relacionamentos incorreta");
		Assertions.assertEquals(2, relationships.get("B").size(), "Quantidade de relacionamentos incorreta");
	}

}
