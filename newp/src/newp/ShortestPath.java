package newp;
//used ArrayList and other required libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortestPath {
	
	public MyGraph graph;
	private Set<String> settledNodes;
	private Set<String> unSettledNodes;
	private Map<String,String> predecessors;
	private Map<String, Integer> cost;
	public ShortestPath(MyGraph graph){
		this.graph= graph;
	}
	public void execute(String source){
		settledNodes = new HashSet<String>();
		unSettledNodes = new HashSet<String>();
		cost = new HashMap<String,Integer>();
		predecessors= new HashMap<String, String>();
		cost.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size()>0){
			String node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalCosts(node);
		}
	}
	private void findMinimalCosts(String node){
		LinkedList<Cost> costs= graph.getListFor(node);
		for (int i=0;i<costs.size();i++){
			String target= costs.get(i).getVertex();
			int a= getMinimumCost(target);
			int b= getMinimumCost(node);
			int c= costs.get(i).getCost();
			if (a>b+c){
				cost.put(target, b+c);
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}
	}
	public int getCost(String source, String destination){
		return graph.getCostFor(source, destination);
	}
	public int getMinimumCost(String node){
		Integer d= cost.get(node);
		if (d==null)
			return Integer.MAX_VALUE;
		else return d;
	}
	private String getMinimum(Set<String> unSettledNodes){
		String s= null;
		for (String temp: unSettledNodes){
			if (s==null)
				s=temp;
			else if (getMinimumCost(temp)<getMinimumCost(s))
				s= temp;
		}return s;
	}
	public LinkedList<String> getPath(String target) {
		LinkedList<String> path = new LinkedList<String>();
		String temp = target;
		if (predecessors.get(temp) == null) {
		return null;
		}
		path.add(temp);
		while (predecessors.get(temp) != null) {
		temp = predecessors.get(temp);
		path.add(temp);
		}
		Collections.reverse(path);
		return path;
		}
}
