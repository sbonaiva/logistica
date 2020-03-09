package com.bonaiva.logistica.mapper;

import org.modelmapper.ModelMapper;

import com.bonaiva.logistica.controller.dto.MapRequest;
import com.bonaiva.logistica.controller.dto.MapResponse;
import com.bonaiva.logistica.service.model.AddMap;
import com.bonaiva.logistica.service.model.AddedMap;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MapMapper {
	
	private static final ModelMapper MAPPER = new ModelMapper();
	
	public static MapResponse toDTO(final AddedMap addedMap) {
		return MAPPER.map(addedMap, MapResponse.class);
	}
	
	public static AddMap toModel(final MapRequest mapRequest) {
		return MAPPER.map(mapRequest, AddMap.class);
	}

}
