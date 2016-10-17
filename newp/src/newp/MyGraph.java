package newp;
import java. util .*;

public class MyGraph {
	MySet1[] hashtable;
	public MyGraph(){
	       hashtable= new MySet1[501];
	       for (int i=0;i<501;i++)
	    	   hashtable[i]= new MySet1();
	   }
	   private int getHashIndex(String str) {
	        String str2=str.toLowerCase();
	    int h=0;
	    for (int i=0;i<str2.length();i++){
	        int ati= (int)str2.charAt(i);
	        h += ati*(33^i);
	    }
	    return((50*h+49)%501);
	   }
	   public MySet1 set(int x){
	       return this.hashtable[x];
	   }
	   public void  addNeighbourForSr(String source,String destination, int cost){
	       
	       int x= getHashIndex(source);
	       
	       MySet1 reqw= new MySet1();
	       reqw= this.hashtable[x];
	       
	       for (int i=0;i<reqw.giveSize();i++){
	           if (reqw.nthobj(i).getVer().equals(source)){
	               
	               reqw.nthobj(i).addCost(destination, cost);
	               return;
	           }                       
	       }
	       AdjElement newsr= new AdjElement(source);
	       newsr.addCost(destination, cost);
	       reqw.addElement(newsr);       
	}
	 public int getCostFor(String source, String destination){
		 int x = getHashIndex(source);
		 MySet1 reqw= new MySet1();
		 reqw= this.hashtable[x];
		 for (int i=0; i< reqw.giveSize();i++){
			 AdjElement req= reqw.nthobj(i);
			 if (req.getVer().equals(source)){
				 LinkedList<Cost> costs= req.getNeigh();
				 for (int j=0;j<costs.size();j++){
					 Cost temp= costs.get(j);
					 if (temp.getVertex().equals(destination))
						 return temp.getCost();
				 }
			 }
		 }return Integer.MAX_VALUE;
	 }
	    public LinkedList<Cost> getListFor(String source){
	    	int x= getHashIndex(source);
	    	MySet1 reqw= new MySet1();
	    	reqw= this.hashtable[x];
	    	for (int i=0;i< reqw.giveSize();i++){
	    		AdjElement req= reqw.nthobj(i);
	    		if (req.getVer().equals(source)){
					 LinkedList<Cost> costs= req.getNeigh();
					 return costs;
	    	}
	    	}
	    	throw new NoSuchElementException("No such "+ source+ " exists.");
	    }
	        
	    
	

}
