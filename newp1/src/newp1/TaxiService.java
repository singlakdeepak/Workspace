package newp1;


import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TaxiService{
	Graph graph;
	
	TaxiList taxisf;
	public TaxiService() {
		graph= new Graph();
		taxisf=new TaxiList();
	}
	//DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		public LinkedList<Vertex> taxipath(Taxi t1, Vertex source) throws NoSuchElementException{
			DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
			//for (int i=0; i<dijkstra.nodes.size();i++){
			//	if (dijkstra.nodes.get(i)==t1.givecurrpos())
					dijkstra.execute(t1.givecurrpos());					
			//}
			//for (int i=0; i<dijkstra.nodes.size();i++){
				//if (dijkstra.nodes.get(i)==source)
		return dijkstra.getPath(source);
			//}
			//return null;
		}
	public int getMinCost(Taxi t1, Vertex source) throws NoSuchElementException{
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	
				dijkstra.execute(t1.givecurrpos());					
		
	return dijkstra.getMinimumCost(source);
		
		
	}
	public Taxi getreqtaxi(Vertex source){
		int a= Integer.MAX_VALUE;
		Taxi t= null;
		for (int i=0; i<taxisf.taxis.size();i++){
			Taxi onetime=taxisf.taxis.get(i);
			if (onetime.availability==0){
			int req= getMinCost(onetime, source);
			if (req<a){
				a=req;
				t=onetime;
			}
		}
		}
			return t;
	}
	public LinkedList<Vertex> route(Vertex source, Vertex destination) throws NoSuchElementException{
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		//for (int i=0; i<dijkstra.nodes.size();i++){
		//	if (dijkstra.nodes.get(i)==source)
				dijkstra.execute(source);					
		//}
		//for (int i=0; i<dijkstra.nodes.size();i++){
		//	if (dijkstra.nodes.get(i)==destination)
	return dijkstra.getPath(destination);
		//}
		//return null;
	}
public int getMinCostv(Vertex source, Vertex destination) throws NoSuchElementException{
	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		//for (int i=0; i<dijkstra.nodes.size();i++){
			//if (dijkstra.nodes.get(i)==source)
				dijkstra.execute(source);					
		//}
		//for (int i=0; i<dijkstra.nodes.size();i++){
			//if (dijkstra.nodes.get(i)==destination)
	return dijkstra.getMinimumCost(destination);
		//}
		//return Integer.MAX_VALUE;
	}
	public void performAction(String actionMessage) throws NoSuchElementException,IOException{
		
		//....
		 try{
			 String s[]= actionMessage.split(" ");
		        String a1;
		        String a2;
		        String a3;
		        switch (s[0]){
		        case "edge":
		        	System.out.println("action to be performed: " + actionMessage);
		        	a1=s[1];
		        	a2 = s[2];
		        	Vertex v1= new Vertex(a1,a1);
		        	Vertex v2= new Vertex(a2,a2);
		        	Edge newed= new Edge(v1,v2,Integer.parseInt(s[3]));
		        	Edge newed2= new Edge (v1,v1,0);
		        	Edge newed3 = new Edge (v2,v2,0);
		        	Edge newed4 = new Edge (v2,v1,Integer.parseInt(s[3]));
		        	graph.mkGraph(newed);
		        	graph.mkGraph(newed2);
		        	graph.mkGraph(newed3);
		        	graph.mkGraph(newed4);
		        	break;
		        	
		        case "taxi":
		        	System.out.println("action to be performed: " + actionMessage);
		        	a1= s[1];
		        	a2= s[2];
		        	taxisf.addtaxi(a1,a2, graph);
		        	break;
		        	
		        case "customer":
		        	System.out.println("action to be performed: " + actionMessage);
		        	a1= s[1];
		        	a2= s[2];
		        	a3= s[3];
		        	Vertex v3 = new Vertex(a1,a1);
		        	Vertex v4 = new Vertex(a2,a2);
		        	taxisf.setTimeForAll(Integer.parseInt(a3));
		        	int a= Integer.MAX_VALUE;
		    		Taxi t= null;
		        	System.out.println("Available taxis:");
		        	for (int i=0; i<taxisf.taxis.size();i++){
		        		Taxi txi= taxisf.taxis.get(i);
		        		if (txi.availability==0){
		        			LinkedList<Vertex> taxip= taxipath(txi,v3);
		        			String request= "";
		        			if (taxip==null){
		        				if(getMinCost(txi,v3)==0)
		        					request=a1+". ";
		        				else if (getMinCost(txi,v3)==Integer.MAX_VALUE)
		        					request="No such path exists. ";
		        			}else
		        			for (int j=0;j<taxip.size();j++){
		        				request= request + taxip.get(j).getName() + ", ";
		        			}
		        			
		        			request= request.substring(0, request.length()-2) + ". ";
		        			int getCost = getMinCost(txi,v3);
		        			
		        			if (getCost<a){
		        				a=getCost;
		        				t=txi;
		        			}
		        			System.out.println("Path of " + txi.givetaxiname()+ ": "+ 
		        			request+"time taken is "+ getCost + " units");
		        		}
		        	}if (t==null)
        				throw new NoSuchElementException("No such path exists.");
		        	System.out.println("** Chose "+ t.givetaxiname()+ " to service the customer request ***");
		        LinkedList<Vertex> rout= route(v3,v4);
		        int getCos = getMinCostv(v3,v4);
		        if (getCos==Integer.MAX_VALUE)
    				throw new NoSuchElementException("No such path exists to drive to " + a2);
		        taxisf.setavlfortaxi(t, getCos, v4);
		        String request= "";
    			for (int j=0;j<rout.size();j++){
    				request= request + rout.get(j).getName() + ", ";
    			}
    			request= request.substring(0, request.length()-2) + ". ";
    			System.out.println("Path of customer: "+ request+ "time taken is "+ getCos+ " units");
    			System.out.println();
		        break;
		        
		        case "printTaxiPosition":
		        	System.out.println("action to be performed: " + actionMessage);
		        	a1=s[1];
		        	taxisf.setTimeForAll(Integer.parseInt(a1));
		        	for (int i=0;i<taxisf.taxis.size();i++){
		        		Taxi txi= taxisf.taxis.get(i);
		        		//if (txi.giveavl()!=0)
		        			//System.out.println(txi.givetaxiname()+ " is in between "+txi.givecurrpos().getName()+" and "+txi.nextposition.getName());
		        		//else
		        		if (txi.giveavl()==0)
		        		System.out.println(txi.givetaxiname()+ ": "+txi.givecurrpos().getName());	
		        	}System.out.println();
		        }
		        
		 }
    catch (NoSuchElementException e1)
    {
        System.out.println(e1.getMessage());
    
    }  /*catch (IOException e1)
    {
        System.out.println(e1.getMessage() +" not found");
    
    }*/
    }
		

	
}

