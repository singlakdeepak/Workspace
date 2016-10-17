package newp;

import java.util.LinkedList;
import java.util.NoSuchElementException;
public class AdjElement {
String vertex;
LinkedList<Cost> neighbours;
public AdjElement(String vertex){
	this.vertex= vertex;
	neighbours = new LinkedList<Cost>();
}
public String getVer(){
	return vertex;
}
public LinkedList<Cost> getNeigh(){
	return neighbours;
}
public int getCostTo(String vert){
	for (int i=0;i<neighbours.size();i++){
		Cost temp=neighbours.get(i);
		if (temp.getVertex().equals(vert))
			return temp.getCost();
	}return Integer.MAX_VALUE;
}
public void addCost(String vert,int cost){
	for (int i=0;i<neighbours.size();i++){
		Cost temp=neighbours.get(i);
		if (temp.getVertex().equals(vert))
			throw new NoSuchElementException("The neighbour "+ vert+ " is already present.");
		}	
		Cost newCost= new Cost(vert,cost);
		neighbours.add(newCost);
	}
}
