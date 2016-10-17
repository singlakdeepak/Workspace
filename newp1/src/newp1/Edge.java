package newp1;


//Code from the link given in the assignment
public class Edge {
	
	private final Vertex source;
	private final Vertex destination;
	private final int cost;
	public Edge( Vertex source, Vertex destination, int cost) {
	
	this.source = source;
	this.destination = destination;
	this.cost = cost;
	}
	
	public Vertex getDestination() {
	return destination;
	}
	public Vertex getSource() {
	return source;
	}
	public int getCost() {
	return cost;
	}
	
	public String toString() {
	return source + " " + destination;
	}
	
	
}

