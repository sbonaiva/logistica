package com.bonaiva.logistica.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonaiva.logistica.service.MapService;
import com.bonaiva.logistica.service.RouteService;
import com.bonaiva.logistica.service.exception.RouteNotExistsException;
import com.bonaiva.logistica.service.model.CalculateRoute;
import com.bonaiva.logistica.service.model.CalculatedRoute;
import com.bonaiva.logistica.service.model.Edge;
import com.bonaiva.logistica.service.model.Vertex;
import com.bonaiva.logistica.util.Dijkstra;

@Service
public class RouteServiceImpl implements RouteService {
	
	private static final String SOURCE_ROUTE_NOT_EXISTS = "Não existe rota de origem cadastrada para %s";
	private static final String TARGET_ROUTE_NOT_EXISTS = "Não existe rota de destino cadastrada para %s";
	
	private final MapService mapService;
	
	@Autowired
	public RouteServiceImpl(final MapService mapService) {
		this.mapService = mapService;
	}

	@Override
	public CalculatedRoute calculate(final CalculateRoute calculateRoute) {
		
		final List<Vertex> vertex = buildVertex();
		
		final Vertex sourceVertex = getSourceVertex(vertex, calculateRoute.getOrigin());
		final Vertex targetVertex = getTargetVertex(vertex, calculateRoute.getDestination());

		final Dijkstra dijkstra = new Dijkstra();
		dijkstra.computeShortestPaths(sourceVertex);
		
		final int distance = targetVertex.getDistance();
		final String route = dijkstra.getShortestPathTo(targetVertex);
		
		final double cost = calculateCost(distance, calculateRoute.getPerformance(), calculateRoute.getPrice());
		
		return CalculatedRoute
				.builder()
				.route(route)
				.cost(cost)
				.build();
	}

	private double calculateCost(final int distance, final int performance, final double price) {
		
		final int necessaryFuel = distance / performance;
		
		return necessaryFuel * price;
	}

	private void addNeighbours(List<Vertex> vertex) {
		
		final Map<String, Map<String, Integer>> relationships = this.mapService.getRelationships();
		
		relationships.entrySet().forEach(sourceEntry -> {
			
				final Optional<Vertex> sourceVertexOpt = getVertex(vertex, sourceEntry.getKey());
				
				if(sourceVertexOpt.isPresent()) {
					
					final Vertex sourceVertex = sourceVertexOpt.get();
					
					sourceEntry.getValue().entrySet().forEach(targetEntry -> {
						
						final Optional<Vertex> targetVertexOpt = getVertex(vertex, targetEntry.getKey());
						
						if(targetVertexOpt.isPresent()) {
							
							final Vertex targetVertex = targetVertexOpt.get();
							
							sourceVertex.addNeighbour(new Edge(targetEntry.getValue(), sourceVertex, targetVertex));
						}
					});
				}
		});
	}
	
	private Vertex getSourceVertex(List<Vertex> vertex, String name) {
		
		return vertex.stream()
				.filter(e -> e.getName().equals(name))
				.findAny()
				.orElseThrow(() -> new RouteNotExistsException(String.format(SOURCE_ROUTE_NOT_EXISTS, name)));
	}
	
	private Vertex getTargetVertex(List<Vertex> vertex, String name) {
		
		return vertex.stream()
				.filter(e -> e.getName().equals(name))
				.findAny()
				.orElseThrow(() -> new RouteNotExistsException(String.format(TARGET_ROUTE_NOT_EXISTS, name)));
	}
	
	private Optional<Vertex> getVertex(List<Vertex> vertex, String name) {
		
		return vertex.stream()
				.filter(e -> e.getName().equals(name))
				.findAny();
	}

	private List<Vertex> buildVertex() {
		
		final Set<String> maps = this.mapService.getMaps();
		
		final List<Vertex> vertex = maps.stream()
				.map(Vertex::new)
				.collect(Collectors.toList());
		
		addNeighbours(vertex);
		
		return vertex;
	}
	
}
