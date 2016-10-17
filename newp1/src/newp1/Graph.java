package newp1;

//java library: Arraylist used, List used
import java.util.ArrayList;

import java.util.List;

public class Graph {
	public List<Vertex> vertexes;
	public List<Edge> edges;
	public Graph(){
		vertexes=new ArrayList();
		edges=new ArrayList();
	}
	public Graph(List<Vertex> vertexes, List<Edge> edges) {
	this.vertexes = vertexes;
	this.edges = edges;
	}
	public List<Vertex> getVertexes() {
	return vertexes;
	}
	public List<Edge> getEdges() {
	return edges;
	}
	public void makeGraph(List<Edge> edge){
			edges.addAll(edge);
			for (int i=0;i<edge.size();i++){
				Edge ed=edge.get(i);
				Vertex source= ed.getSource();
				Vertex destination= ed.getDestination();
				if (!vertexes.contains(source))
					vertexes.add(source);
				if (!vertexes.contains(destination)){
					vertexes.add(destination);
				}
			}
	}
	public void mkGraph(Edge edge){
		if(!edges.contains(edge))
		edges.add(edge);
		Vertex source= edge.getSource();
			Vertex destination= edge.getDestination();
			if (!vertexes.contains(source))
				vertexes.add(source);
			if (!vertexes.contains(destination)){
				vertexes.add(destination);
			}
		}

	
}

