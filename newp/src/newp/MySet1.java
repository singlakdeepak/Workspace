package newp;

//Code from the previous assignment MySet
public class MySet1{
    MyLinkedList<AdjElement> a1 ;
    public MySet1()
    {
        a1= new MyLinkedList<AdjElement>();
    }
   public boolean IsEmpty()
   {
       return (a1.isEmpty());
               }
   public int giveSize(){
       
       return a1.getSize();
   }
   public AdjElement nthobj(int i){
       return a1.beta(i);
   }
  public boolean IsMember(AdjElement o){
       if (IsEmpty())
           return false;
       else{
           Node<AdjElement> ptr=a1.start;
        while ((o!=ptr.getData())&&(ptr.getLink()!=null) )
        {
            
              ptr= ptr.getLink();
        }
        return (o==ptr.getData());
   }
   }
   public void addElement(AdjElement o)
        {    if (!IsMember(o))
         a1.insertAtEnd(o);
       }
   public void delete (AdjElement o){
       a1.delete1(o);
   }    
   public MySet1 union(MySet1 a){
       MySet1 ms= new MySet1();
       Node<AdjElement> ptr = a1.start;
       while (ptr!=null){
           if (!a.IsMember(ptr.getData())){
               ms.addElement(ptr.getData());
               ptr=ptr.getLink();
           }
           Node<AdjElement> pt2 = a.a1.start;//myset a ki linked list a1 ka start
           while (pt2!=null){
               ms.addElement(pt2.getData());
               pt2=pt2.getLink();
           }
       }
       return ms;
   }
   public MySet1 intersection (MySet1 a){
       MySet1 ms= new MySet1();
        Node<AdjElement> ptr = a1.start;
       while (ptr!=null){
           if (a.IsMember(ptr.getData())){
               ms.addElement(ptr.getData());}
               ptr=ptr.getLink();
           
   }
       return ms;
}
}

