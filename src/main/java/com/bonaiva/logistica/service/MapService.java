package com.bonaiva.logistica.service;

import java.util.Map;
import java.util.Set;

import org.springframework.lang.NonNull;

import com.bonaiva.logistica.service.model.AddMap;
import com.bonaiva.logistica.service.model.AddedMap;

public interface MapService {

	AddedMap add(@NonNull final AddMap addMap);

	Set<String> getMaps();

	Map<String, Map<String, Integer>> getRelationships();

}
