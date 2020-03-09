package com.bonaiva.logistica.service;

import org.springframework.lang.NonNull;

import com.bonaiva.logistica.service.model.CalculateRoute;
import com.bonaiva.logistica.service.model.CalculatedRoute;

public interface RouteService {

	CalculatedRoute calculate(@NonNull final CalculateRoute calculateRoute);

}
