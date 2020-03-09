package com.bonaiva.logistica.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bonaiva.logistica.service.MapService;
import com.bonaiva.logistica.service.model.AddMap;
import com.bonaiva.logistica.service.model.AddedMap;

@Service
public class MapServiceImpl implements MapService {
	
	private final Set<String> maps;
	private final Map<String, Map<String, Integer>> relationships;
	
	public MapServiceImpl() {
		this.maps = new HashSet<>();
		this.relationships = new HashMap<>();
	}

	@Override
	public AddedMap add(final AddMap addMap) {
		
		this.maps.add(addMap.getOrigin());
		this.maps.add(addMap.getDestination());
		
		if(this.relationships.containsKey(addMap.getOrigin())) {
			this.relationships.get(addMap.getOrigin()).put(addMap.getDestination(), addMap.getDistance());
		} else {
			Map<String, Integer> destinationMap = new HashMap<>();
			destinationMap.put(addMap.getDestination(), addMap.getDistance());
			this.relationships.put(addMap.getOrigin(), destinationMap);
		}
		
		return AddedMap
				.builder()
				.origin(addMap.getOrigin())
				.destination(addMap.getDestination())
				.distance(addMap.getDistance())
				.build();
	}
	
	@Override
	public Set<String> getMaps() {
		return Collections.synchronizedSet(this.maps);
	}
	
	@Override
	public Map<String, Map<String, Integer>> getRelationships() {
		return Collections.synchronizedMap(this.relationships);
	}

}
