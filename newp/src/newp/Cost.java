package newp;

public class Cost {
private String vertex;
private int cost;
public Cost(String vertex, int cost){
	this.vertex= vertex;
	this.cost=cost;
}
public String getVertex(){
	return vertex;
}
public int getCost(){
	return cost;
}
public boolean equals(Cost w){
	return vertex.equals(w.getVertex());
}
}
