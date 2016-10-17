/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author G500
 */
public class WordEntry {
    public String str;
    Position pos;
    AVLTree posts;
    public WordEntry(String word){
        str=word;
        posts= new AVLTree();
    }
    public String word(){
        return str;
    }
    public void addPosition(Position position){
        posts.insert(position);
    }
    public void addPositions(AVLTree positions){
       /* while(positions.getRoot()!=null){
        	posts.insert(positions.getRoot());
        	positions.remove(positions.getRoot());
        }*/	if (positions.getRoot()==null)
        	return;
        else addPositionss(positions.root);
    }
    
    public void addPositionss(AvlNode rook){
    	if (rook==null)
    		return;
    	else {
    		addPositionss(rook.left);
    		posts.insert(rook.key);
    		addPositionss(rook.right);
    	}
    }
    public AVLTree getAllPositionsForThisWord(){
        return posts;
    }
    public MyLinkedList<Position> list(){
    	return posts.getPositions();
    }
    public MyLinkedList<Integer> getAllIndicesforsamePage(String p){
        MyLinkedList<Position> p1=list();
        MyLinkedList<Integer> a1= new MyLinkedList<Integer>();
       for (int i=0;i<p1.getSize();i++){
        	if (p1.beta(i).getPageEntry().pageNames().equals(p)){
        		a1.insertAtEnd(p1.beta(i).getWordIndex());
        	}
        }return a1;
    }
    //auxiliary function
   public MyLinkedList<Integer> getAllIndexesforsamePage(String p){
    MyLinkedList<Position> p1=list();
    MyLinkedList<Integer> a1= new MyLinkedList<Integer>();
   for (int i=0;i<p1.getSize();i++){
    	if (p1.beta(i).getPageEntry().pageNames().equals(p)){
    		a1.insertAtEnd(p1.beta(i).PageEntryListPosition);
    	}
    }return a1;
}
}