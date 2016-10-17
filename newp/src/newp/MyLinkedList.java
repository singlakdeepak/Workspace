package newp;
import java.util.NoSuchElementException;

//Code from the previous assignment LinkedList
public class MyLinkedList<E> {
    protected Node<E> start;
   protected Node<E> end;
   public int size ;

   /*  Constructor  */
   public MyLinkedList()
   {
       start = null;
       end = null;
       size = 0;
   }
   /*  Function to check if list is empty  */
   public boolean isEmpty()
   {
       return start == null;
   }
   /*  Function to get size of list  */
   public int getSize()
   {
       return size;
       
   }    
   public E beta(int i){
       if (size==0)
           throw new NoSuchElementException ("Linked List is empty");
       else if (i>=size)
           throw new NoSuchElementException ("child not found");
       Node<E> ptr =start;
       for (int j=0;j<i;j++){
           ptr=ptr.getLink();
       }
       return ptr.getData();
   }
   /*  Function to insert an element at begining  */
   public void insertAtStart(E val)
   {
       Node<E> nptr = new Node<E>(val, null);    
       size++ ; 
       
       if(start == null) 
       {
           start = nptr;
           end = start;
           
       }
       else 
       {
           nptr.setLink(start);
           start = nptr;
       }
   }
   /*  Function to insert an element at end  */
   public void insertAtEnd(E val)
   {
       Node<E> nptr = new Node<E>(val,null);    
       size++ ; 
       
       if(start == null) 
       {
           start = nptr;
           end = start;
       }
       else 
       {
           end.setLink(nptr);
           end = nptr;
       }
   }
   /*  Function to insert an element at position  */
   public void insertAtPos(E val , int pos)
   {
        if (pos>=size)
           throw new NoSuchElementException ("child not found");
       Node<E> nptr = new Node<E>(val, null);                
       Node<E> ptr = start;
       pos = pos - 1 ;
       for (int i = 1; i < size; i++) 
       {
           if (i == pos) 
           {
               Node<E> tmp = ptr.getLink() ;
               ptr.setLink(nptr);
               nptr.setLink(tmp);
               break;
           }
           ptr = ptr.getLink();
       }
       size++ ;
   }
   /*  Function to delete an element at position  */
   public void delete1 (E o)
   {        
       if (isEmpty())
           throw new NoSuchElementException ("Linked List is empty");
       
       Node<E> ptr = start;
       Node<E> ptx= start.getLink();
       if (o==start.getData()){
       	start=ptx;
       }
       while ((o!=ptx.getData()) && ptx.getLink()!=null) 
       {
            ptr = ptx;
            ptx= ptr.getLink();
       }
       if (o==ptx.getData()) 
           {	if (ptx.getLink()==null){
           	end=ptr;
           }
           else{  Node<E> tmp = ptx.getLink();
               ptr.setLink(tmp);}
               }
       size-- ;
           
   }

}
