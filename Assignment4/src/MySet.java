/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author G500
 */
import java.util.*;
public class MySet<E> {
    MyLinkedList<E> a1 ;
    public MySet()
    {
        a1= new MyLinkedList<E>();
    }
   public boolean IsEmpty()
   {
       return (a1.isEmpty());
               }
   public int giveSize(){
       
       return a1.getSize();
   }
   public E nthobj(int i){
       return a1.beta(i);
   }
  public boolean IsMember(E o){
       if (IsEmpty())
           return false;
       else{
           Node<E> ptr=a1.start;
        while ((!o.equals(ptr.getData()))&&(ptr.getLink()!=null) )
        {
            
              ptr= ptr.getLink();
        }
        return (o.equals(ptr.getData()));
   }
   }
   public void addElement(E o)
        {    if (!IsMember(o))
         a1.insertAtEnd(o);
       }
   public void delete (E o){
       a1.delete1(o);
   }    
   public MySet<E> union(MySet<E> a){
       MySet<E> ms= new MySet<E>();
       Node<E> ptr = a1.start;
       while (ptr!=null){
           ms.addElement(ptr.getData());
               ptr=ptr.getLink();
            }
           Node<E> pt2 = a.a1.start;//myset a ki linked list a1 ka start
           while (pt2!=null){
               ms.addElement(pt2.getData());
               pt2=pt2.getLink();
           }
           
       return ms;
   }
   public MySet<E> intersection (MySet<E> a){
       MySet<E> ms= new MySet<E>();
        Node<E> ptr = a1.start;
       while (ptr!=null){
           if (a.IsMember(ptr.getData())){
               ms.addElement(ptr.getData());}
               ptr=ptr.getLink();
           
   }
       return ms;
}
}
