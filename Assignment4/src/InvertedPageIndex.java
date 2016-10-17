import java.util.NoSuchElementException;
import java.io.IOException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author G500
 */
public class InvertedPageIndex {
    MyHashTable hst;
    MySet<String> pagenames;
    public InvertedPageIndex(){
        hst= new MyHashTable();
        pagenames= new MySet<String>();
    }
    public void addPage(PageEntry p){
        pagenames.addElement(p.pageNames());
    	PageIndex index= p.getPageIndex();
      
        MyLinkedList<WordEntry> index1=index.getWordEntries();
        
        for (int i=0;i<index1.getSize();i++){
            WordEntry childi= index1.beta(i);
           
            hst.addPositionsForWord(childi);
        }
    }
     private int getHashIndex1(String str) {
        String str2=str.toLowerCase();
    int h=0;
    for (int i=0;i<str2.length();i++){
        int ati= (int)str2.charAt(i);
        h += ati*(33^i);
    }
    return((50*h+49)%501);
   }
    public MySet<PageEntry> getPagesWhichContainWord(String str){
    	String a9= new String(str);
        a9=a9.toLowerCase();
        if (a9.equals("stacks"))
    		a9="stack";
    	if (a9.equals("structures"))
    		a9="structure";
    	if (a9.equals("applications"))
    		a9="application";
    	if (a9.equals("functions"))
    		a9="function";
        MySet<PageEntry> ms= new MySet<PageEntry>();
        int a=getHashIndex1(a9);
        MySet1 reqw = hst.set(a);
        for(int i=0;i<reqw.giveSize();i++){
         if( reqw.nthobj(i).word().equals(a9)){
             MyLinkedList<Position> entpos= reqw.nthobj(i).list();
             for(int j=0;j<entpos.getSize();j++){
                 PageEntry childi=entpos.beta(j).getPageEntry();
                 ms.addElement(childi);
             }
             return ms;
         }
           
        }//throw new NoSuchElementException ("No webpage contains word "+ str);-------------->create this exception in searchengine
        return ms;
        }
    //new function of phrases   
    public MySet<SearchResult> getPagesWhichContainPhrase(String str[]){
        	 MySet<SearchResult> ms= new MySet<SearchResult>();
             int a=getHashIndex1(str[0]);
             MySet1 reqw = hst.set(a);
             for(int i=0;i<reqw.giveSize();i++){
              if( reqw.nthobj(i).word().equals(str[0])){
            	  AVLTree entpos=reqw.nthobj(i).getAllPositionsForThisWord();
            	 ms= doinordersearchforphrase(entpos.root,ms,str);
            	 
                  /*MyLinkedList<Position> entpos= reqw.nthobj(i).list();               		 
                  for(int j=0;j<entpos.getSize();j++){*/
                   	 /* Position forthis=entpos.getRoot();
                   	int pos1=forthis.PageEntryListPosition;
                   	int index= forthis.getWordIndex();
                   	  PageEntry forit=forthis.getPageEntry();
                   	  int aux=pos1;
                   	  boolean rec= false;
                   	  for (int l=1;l<str.length;l++){
                   		  if (wordentrycontainpos(str[l],forit,aux+1))
                   			  {rec=true;
                   			  aux++;
                   			  } else {
                   				  rec=false;
                   				  break;
                   			  }
                   	  }if (rec){
                   		  for (int x=0; x<=ms.giveSize();x++){
                   			
                   			if(x==ms.giveSize()){
                 				  SearchResult make= new SearchResult(forit,(float)(1/((float)index*index)));
                 				  ms.addElement(make);
                 				  break;
                   			}
                 				  SearchResult req=ms.nthobj(x);
                   			  if(req.getPageEntry().pageNames().equals(forit.pageNames())){
                   				  req.relev=req.relev + (float)(1/((float)index*index));
                   				  break;
                   			  }
                   			   
                   			  
                   			   }
                   		  
                   	  }
                  }
              }*/}
             }return ms;
        //throw new NoSuchElementException ("No webpage contains word "+ str);
    }//end of new function
    
    public MySet<SearchResult> doinordersearchforphrase(AvlNode root, MySet<SearchResult> ms,String str[]){
    	if(root.left!=null){
    	ms=doinordersearchforphrase(root.left,ms, str);}
    	if(root.right!=null){
    	ms=doinordersearchforphrase(root.right,ms, str);}
    	Position forthis=root.key;
       	int pos1=forthis.PageEntryListPosition;
       	int index= forthis.getWordIndex();
       	  PageEntry forit=forthis.getPageEntry();
       	  int aux=pos1;
       	  boolean rec= false;
       	  for (int l=1;l<str.length;l++){
       		  if (wordentrycontainpos(str[l],forit,aux+1,index))
       			  {rec=true;
       			  aux++;
       			  } else {
       				  rec=false;
       				  break;
       			  }
       	  }if (rec){
       		  for (int x=0; x<=ms.giveSize();x++){
       			
       			if(x==ms.giveSize()){
     				  SearchResult make= new SearchResult(forit,(float)(1/((float)index*index)));
     				  ms.addElement(make);
     				  break;
       			}
     				  SearchResult req=ms.nthobj(x);
       			  if(req.getPageEntry().pageNames().equals(forit.pageNames())){
       				  req.relev=req.relev + (float)(1/((float)index*index));
       				  break;
       			  }
       			   
       			  
       			   }
       		  
       	  }
  
  
 return ms;
    }
    	
    
             public boolean wordentrycontainpos(String str,PageEntry p,int index,int realindex){
            	// boolean bs= false;
    	 int a=getHashIndex1(str);
         MySet1 reqw = hst.set(a);
         for(int i=0;i<reqw.giveSize();i++){
          if( reqw.nthobj(i).word().equals(str)){
              MyLinkedList<Position> entpos= reqw.nthobj(i).list();               		 
             // AVLTree entpos=reqw.nthobj(i).getAllPositionsForThisWord();
        	  for(int j=0;j<entpos.getSize();j++){
               	  Position forthis=entpos.beta(j);
             // AvlNode forit=entpos.root;
               if ((forthis.getPageEntry().pageNames().equals(p.pageNames()))&&(index==forthis.PageEntryListPosition))
             //  	 bs=inorderfindboolean(forit,p,index,realindex);
             return true;
             }
          }
         }//return bs;
         return false;
    }
         /*    public boolean inorderfindboolean(AvlNode root,PageEntry p,int index,int realindex){
            	 boolean res=false;
            	 if(root!=null){
            	 if (root.key.getWordIndex()>realindex){
            		 	res=inorderfindboolean(root.left,p,index,realindex);
            		 	return res;
            	 }
            	 else if(root.key.getWordIndex()<realindex){
            		 res=inorderfindboolean(root.right,p,index,realindex);
            		 return res;
            	 }else{
            		 Position forthis=root.key;
            		 if ((forthis.getPageEntry().pageNames().equals(p.pageNames()))&&(index==forthis.PageEntryListPosition)){
            			 return true;
            			 }
            		 else{
            			 if(inorderfindboolean(root.left,p,index,realindex))
            				 return true;
            			 else if(inorderfindboolean(root.right,p,index,realindex))
            				 return true;
            			 else return false;
            		 }
            	 }
             }return res;
             }*/
    public MySet<Integer> getPageIndexesWhichContainWord(String str,String p){
    	if(pagenames.IsMember(p)){
        MySet<Integer> ms= new MySet<Integer>();
        int a=getHashIndex1(str);
        MySet1 reqw = hst.set(a);
        for(int i=0;i<reqw.giveSize();i++){
         if( reqw.nthobj(i).word().equals(str)){
             MyLinkedList<Position> entpos= reqw.nthobj(i).list();
             for(int j=0;j<entpos.getSize();j++){
                 String childi=entpos.beta(j).getPageEntry().pageNames();
                 
                 if(childi.equals(p))
                	 {
                 ms.addElement(entpos.beta(j).getWordIndex());
                	 }
                 }
         }
             
           
         }return ms;
        }
    	else throw new NoSuchElementException("Webpage "+p+ " has not been added");
    }
    public MySet<PageEntry> andquery(String str[]) throws IOException{
    	MySet<PageEntry> ms= new MySet<PageEntry> ();
    	ms=getPagesWhichContainWord(str[0]);
    	for (int i=1;i<str.length;i++){
    		ms=ms.intersection(getPagesWhichContainWord(str[i]));
    	}
    	return ms;
    }
    public MySet<PageEntry> orquery(String str[]) throws IOException{
    	MySet<PageEntry> ms= new MySet<PageEntry> ();
    	ms=getPagesWhichContainWord(str[0]);
    	for (int i=1;i<str.length;i++){
    		ms=ms.union(getPagesWhichContainWord(str[i]));
   
    	}
    	return ms;
    }
}
