package newp;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class TaxiService{
	MyGraph graph;
	TaxiList taxisf;
	public TaxiService() {
		graph= new MyGraph();
		taxisf=new TaxiList();
	}
	
	public LinkedList<String> taxipath(Taxi t1, String source) throws NoSuchElementException{
		ShortestPath dijkstra= new ShortestPath(graph);
		dijkstra.execute(t1.givecurrpos());	
		return dijkstra.getPath(source);
	}
	
	public int getMinCost(Taxi t1, String source) throws NoSuchElementException{
		ShortestPath dijkstra = new ShortestPath(graph);
	dijkstra.execute(t1.givecurrpos());					
	return dijkstra.getMinimumCost(source);
	}
		
	public Taxi getreqtaxi(String source){
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
	

	public LinkedList<String> route(String source, String destination) throws NoSuchElementException{
		ShortestPath dijkstra = new ShortestPath (graph);
				dijkstra.execute(source);					
	return dijkstra.getPath(destination);
	}
	

	public int getMinCostv(String source, String destination) throws NoSuchElementException{
		ShortestPath dijkstra = new ShortestPath(graph);
					dijkstra.execute(source);
		return dijkstra.getMinimumCost(destination);
			}
	
public void performAction(String actionMessage) throws NoSuchElementException,IOException{
		
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
		        	a3= s[3];
		        	graph.addNeighbourForSr(a1, a2, Integer.parseInt(a3));
		        	graph.addNeighbourForSr(a2, a1, Integer.parseInt(a3));
		        	break;
		        	
		        case "taxi":
		        	System.out.println("action to be performed: " + actionMessage);
		        	a1= s[1];
		        	a2= s[2];
		        	taxisf.addtaxi(a1,a2);
		        	break;
		        	
		        case "customer":
		        	System.out.println("action to be performed: " + actionMessage);
		        	a1= s[1];
		        	a2= s[2];
		        	a3= s[3];
		        	if (Integer.parseInt(a3)<0)
		        		throw new NoSuchElementException("Given time is negative.");
		        	taxisf.setTimeForAll(Integer.parseInt(a3));
		        	int a= Integer.MAX_VALUE;
		    		Taxi t= null;
		        	System.out.println("Available taxis:");
		        	for (int i=0; i<taxisf.taxis.size();i++){
		        		Taxi txi= taxisf.taxis.get(i);
		        		if (txi.availability==0){
		        			LinkedList<String> taxip= taxipath(txi,a1);
		        			String request= "";
		        			if (taxip==null){
		        				if(getMinCost(txi,a1)==0)
		        					request=a1+". ";
		        				else if (getMinCost(txi,a1)==Integer.MAX_VALUE)
		        					request="No such path exists. ";
		        			}else
		        			for (int j=0;j<taxip.size();j++){
		        				request= request + taxip.get(j) + ", ";
		        			}
		        			
		        			request= request.substring(0, request.length()-2) + ". ";
		        			int getCost = getMinCost(txi,a1);
		        			
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
		        LinkedList<String> rout= route(a1,a2);
		        int getCos = getMinCostv(a1,a2);
		        if (getCos==Integer.MAX_VALUE)
    				throw new NoSuchElementException("No such path exists to drive to " + a2);
		        taxisf.setavlfortaxi(t, getCos, a2);
		        String request= "";
    			for (int j=0;j<rout.size();j++){
    				request= request + rout.get(j) + ", ";
    			}
    			request= request.substring(0, request.length()-2) + ". ";
    			System.out.println("Path of customer: "+ request+ "time taken is "+ getCos+ " units");
    			System.out.println();
		        break;
		        
		        case "printTaxiPosition":
		        	System.out.println("action to be performed: " + actionMessage);
		        	a1=s[1];
		        	boolean abrupt= false;
		        	if (Integer.parseInt(a1)<0)
		        		throw new NoSuchElementException("Given time is negative.");
		        	taxisf.setTimeForAll(Integer.parseInt(a1));
		        	for (int i=0;i<taxisf.taxis.size();i++){
		        		Taxi txi= taxisf.taxis.get(i);
		        		if (txi.giveavl()==0){
		        			abrupt=true;
		        		System.out.println(txi.givetaxiname()+ ": "+txi.givecurrpos());	}
		        	}if (abrupt==false)
		        		System.out.println("No taxi is free");
		        	System.out.println();
		        	break;
		        }
		        
		 }
    catch (NoSuchElementException e1)
    {
        System.out.println(e1.getMessage());
    
    } 
    }

	
}
