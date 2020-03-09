package com.bonaiva.logistica;

import org.springframework.boot.test.context.SpringBootTest;

import com.bonaiva.logistica.service.model.AddMap;

@SpringBootTest
public abstract class BaseTest {
	
	protected final static AddMap buildMap(String origin, String destination, Integer distance) {
		
		return AddMap
				.builder()
				.origin(origin)
				.destination(destination)
				.distance(distance)
				.build();
	}

}
