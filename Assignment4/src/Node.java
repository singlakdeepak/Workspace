/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author G500
 */
public class Node<E> {
     protected E data;
    protected Node<E> link;
 
    /*  Constructor  */
    public Node()
    {   
        data=null;
        link = null;        
    }    
    /*  Constructor  */
    public Node(E d,Node<E> n)
    {
        data = d;
        link = n;
    }    
    /*  Function to set link to next Node  */
    public void setLink(Node<E> n)
    {
        link = n;
    }    
    /*  Function to set data to current Node  */
    public void setData(E d)
    {
        data = d;
    }    
    /*  Function to get link to next node  */
    public Node<E> getLink()
    {
        return link;
    }    
    /*  Function to get data from current Node  */
    public E getData()
    {
        return data;
    }
}
