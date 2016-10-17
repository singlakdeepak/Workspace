/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author G500
 */
import java. util .*;
public class MyHashTable {
    
   MySet1[] hashtable;
public MyHashTable(){
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
   public void  addPositionsForWord(WordEntry w){
       String req= w.word();
       int x= getHashIndex(req);
       
       MySet1 reqw= new MySet1();
       reqw= this.hashtable[x];
       
       for (int i=0;i<reqw.giveSize();i++){
           if (reqw.nthobj(i).word().equals(req)){
               AVLTree positions= w.getAllPositionsForThisWord();
               reqw.nthobj(i).addPositions(positions);
               return;
           }                       
       }
       reqw.addElement(w);       
}
    
    
        
    
}
