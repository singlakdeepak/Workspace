package newp1;



//java library: Arraylist and other libraries used

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {
	public final List<Vertex> nodes;
	public final List<Edge> edges;
	private Set<Vertex> settledNodes;
	
	private Set<Vertex> unSettledNodes;
	private Map<Vertex, Vertex> predecessors;
	private Map<Vertex, Integer> cost;
	public DijkstraAlgorithm(Graph graph) {
	// create a copy of the array so that we can operate on this array
	this.nodes = new ArrayList<Vertex>(graph.getVertexes());
	this.edges = new ArrayList<Edge>(graph.getEdges());
	}
	public void execute(Vertex source) {
	settledNodes = new HashSet<Vertex>();
	unSettledNodes = new HashSet<Vertex>();
	cost = new HashMap<Vertex, Integer>();
	predecessors = new HashMap<Vertex, Vertex>();
	cost.put(source, 0);
	unSettledNodes.add(source);
	while (unSettledNodes.size() > 0) {
	Vertex node = getMinimum(unSettledNodes);
	settledNodes.add(node);
	unSettledNodes.remove(node);
	findMinimalCosts(node);
	}
	}
	private void findMinimalCosts(Vertex node) {
	List<Vertex> adjacentNodes = getNeighbors(node);
	for (Vertex target : adjacentNodes) {
	if (getMinimumCost(target) > getMinimumCost(node)
	+ getCost(node, target)) {
	cost.put(target, getMinimumCost(node)
	+ getCost(node, target));
	predecessors.put(target, node);
	unSettledNodes.add(target);
	}
	}
	}
	private int getCost(Vertex node, Vertex target) {
	for (Edge edge : edges) {
	if (edge.getSource().equals(node)
	&& edge.getDestination().equals(target)) {
	return edge.getCost();
	}
	}
	throw new RuntimeException("Should not happen");
	}
	private List<Vertex> getNeighbors(Vertex node) {
	List<Vertex> neighbors = new ArrayList<Vertex>();
	for (Edge edge : edges) {
	if (edge.getSource().equals(node)
	&& !isSettled(edge.getDestination())) {
	neighbors.add(edge.getDestination());
	}
	}
	return neighbors;
	}
	private Vertex getMinimum(Set<Vertex> vertexes) {
	Vertex minimum = null;
	for (Vertex vertex : vertexes) {
	if (minimum == null) {
	minimum = vertex;
	} else {
	if (getMinimumCost(vertex) < getMinimumCost(minimum)) {
	minimum = vertex;
	}
	}
	}
	return minimum;
	}
	private boolean isSettled(Vertex vertex) {
	return settledNodes.contains(vertex);
	}
	public int getMinimumCost(Vertex destination) {
	Integer d = cost.get(destination);
	if (d == null) {
	return Integer.MAX_VALUE;
	} else {
	return d;
	}
	}
	/*
	* This method returns the path from the source to the selected target and
	* NULL if no path exists
	*/
	public LinkedList<Vertex> getPath(Vertex target) {
	LinkedList<Vertex> path = new LinkedList<Vertex>();
	Vertex step = target;
	// check if a path exists
	if (predecessors.get(step) == null) {
	return null;
	}
	path.add(step);
	while (predecessors.get(step) != null) {
	step = predecessors.get(step);
	path.add(step);
	}
	// Put it into the correct order
	Collections.reverse(path);
	return path;
	}
}

