package com.bonaiva.logistica.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bonaiva.logistica.controller.dto.MapRequest;
import com.bonaiva.logistica.controller.dto.MapResponse;
import com.bonaiva.logistica.mapper.MapMapper;
import com.bonaiva.logistica.service.MapService;
import com.bonaiva.logistica.service.model.AddMap;
import com.bonaiva.logistica.service.model.AddedMap;

@RestController
@RequestMapping("mapas")
public class MapController {
	
	private final MapService mapService;
	
	@Autowired
	public MapController(final MapService mapService) {
		this.mapService = mapService;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MapResponse add(@Valid @RequestBody final MapRequest mapRequest) {
		
		final AddMap addMap = MapMapper.toModel(mapRequest);
		
		final AddedMap addedMap = this.mapService.add(addMap);
		
		return MapMapper.toDTO(addedMap);
	}

}
