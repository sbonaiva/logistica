package com.bonaiva.logistica.service.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Vertex implements Comparable<Vertex> {

	private String name;
	private List<Edge> adjacenciesList;
	private boolean visited;
	private Vertex predecessor;
	private int distance = Integer.MAX_VALUE;

	public Vertex(String name) {
		this.name = name;
		this.adjacenciesList = new ArrayList<>();
	}

	public void addNeighbour(Edge edge) {
		this.adjacenciesList.add(edge);
	}

	@Override
	public int compareTo(Vertex o) {
		return Integer.compare(this.distance, o.getDistance());
	}

}
