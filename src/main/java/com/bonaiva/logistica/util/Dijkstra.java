package com.bonaiva.logistica.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import com.bonaiva.logistica.service.model.Edge;
import com.bonaiva.logistica.service.model.Vertex;

public class Dijkstra {

	public void computeShortestPaths(Vertex sourceVertex) {

		sourceVertex.setDistance(0);
		PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(sourceVertex);
		sourceVertex.setVisited(true);

		while (!priorityQueue.isEmpty()) {
			
			Vertex actualVertex = priorityQueue.poll();

			for (Edge edge : actualVertex.getAdjacenciesList()) {

				Vertex targetVertex = edge.getTargetVertex();
				
				if (!targetVertex.isVisited()) {
					int newDistance = actualVertex.getDistance() + edge.getWeight();

					if (newDistance < targetVertex.getDistance()) {
						priorityQueue.remove(targetVertex);
						targetVertex.setDistance(newDistance);
						targetVertex.setPredecessor(actualVertex);
						priorityQueue.add(targetVertex);
					}
				}
			}
			
			actualVertex.setVisited(true);
			
		}
	}

	public String getShortestPathTo(Vertex targetVertex) {
		
		List<Vertex> path = new ArrayList<>();

		for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPredecessor()) {
			path.add(vertex);
		}

		Collections.reverse(path);
		
		return path.stream()
				.map(Vertex::getName)
				.collect(Collectors.joining(" "));
	}

}
