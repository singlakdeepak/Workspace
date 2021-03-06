/*import java.util.*;
import java.io.IOException;
class AVLNode {
AVLNode left,right;
Position data;
int height;
public AVLNode(){
	data=null;
	left=null;
	right=null;
	height=0;
}
public AVLNode(Position pos){
	data=pos;
	left=null;
	right=null;
	height=0;
}
public int height1(){
	return height;
}
}

class AVLTree{
	AVLNode avlpos;
	public AVLTree(){
	 avlpos= null;
	}
	public boolean isEmpty(){
		return avlpos==null;
	}
	public int height(){
		return avlpos.height;
	}
	public void insert (Position p){
		avlpos= insert(p,avlpos);
	}
	public AVLNode insert (Position p,AVLNode t){
		if(t==null)
			t=new AVLNode(p);
		else if (p.getWordIndex()<t.data.getWordIndex())
		{
			t.left=insert(p,t.left);
			if (t.left.height1()-t.right.height1()==2)
			{
				if (p.getWordIndex()<t.left.data.getWordIndex())
					t=singlerotatel(t);
				else t=doublerotatel(t);
			}
		}
		else {
			t.right=insert(p,t.right);
			if (t.right.height1()-t.left.height1()==2)
			{
				if (p.getWordIndex()<t.left.data.getWordIndex())
					t=doublerotater(t);
				else t=singlerotater(t);
			}
			t.right=insert(p,t.right);
		}
		t.height=t.height+1;
		return t;
	}
	public int max(int i1,int i2){
		if (i1>i2)
			return i1;
		else return i2;
	}
	public AVLNode singlerotatel(AVLNode t){
		AVLNode t1=t;
		AVLNode t2=t.left;
		t1.left=t2.right;
		t2.right=t1;
		t1.height=max(t1.left.height,t1.right.height);
		t2.height=max(t2.left.height,t2.right.height);
		t=t2;
		return t;
	}
	public AVLNode doublerotatel(AVLNode t){
		AVLNode t1=t;
		AVLNode t2=t.left;
		AVLNode t3=t.left.right;
		t1.left=t3.right;
		t2.right=t3.left;
		t3.left=t2; t3.right=t1;
		t1.height=max(t1.left.height,t1.right.height);
		t2.height=max(t2.left.height,t2.right.height);
		t3.height=max(t3.left.height,t3.right.height);
		t=t3;
		return t;
	}
	public AVLNode singlerotater(AVLNode t){
		AVLNode t1=t;
		AVLNode t2=t.right;
		t1.right=t2.left;
		t2.left=t1;
		t1.height=max(t1.left.height,t1.right.height);
		t2.height=max(t2.left.height,t2.right.height);
		t=t2;
		return t;
	}
	public AVLNode doublerotater(AVLNode t){
		AVLNode t1=t;
		AVLNode t2=t.right;
		AVLNode t3=t.right.left;
		t1.right=t3.left;
		t2.left=t3.right;
		t3.right=t2; t3.left=t1;
		t1.height=max(t1.left.height,t1.right.height);
		t2.height=max(t2.left.height,t2.right.height);
		t3.height=max(t3.left.height,t3.right.height);
		t=t3;
		return t;
	}
	
	public Position remove(AVLNode t1) throws NullPointException{
		Position a1;
		if (t1.data==avlpos.data){
		if ((avlpos.left==null)&&(avlpos.right==null))
		{a1=avlpos.data;
			avlpos=null;
		}
		else if ((avlpos.left==null)&&(avlpos.right!=null)){
			a1=avlpos.data;
			avlpos=avlpos.right;
		}
		else if ((avlpos.left!=null)&&(avlpos.right==null)){
			a1=avlpos.data;
			avlpos=avlpos.left;
		}
		else {
			a1=avlpos.data;
			AVLNode a2= avlpos.right;
			while (a2.left!=null){
				a2=a2.left;
			}
			Position p=a2.data;
			avlpos.data=p;
		}
		else if (t1.data<avlpos.data){
			
		}
		}
	}
}*/
 class AVLTree {
 
 protected AvlNode root; // the root node
 public Position getRoot(){
	 if(root!=null)
		 return root.key;
	return null;
 }

 public void insert(Position k) {
  // create new node
  AvlNode n = new AvlNode(k);
  // start recursive procedure for inserting the node
  insertAVL(this.root,n);
 }
 public void insertAVL(AvlNode p, AvlNode q) {
  // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree.
  if(p==null) {
   this.root=q;
  } else {
   
   // If compare node is smaller, continue with the left node
   if(q.key.getWordIndex()<p.key.getWordIndex()) {
    if(p.left==null) {
     p.left = q;
     q.parent = p;
     
     // Node is inserted now, continue checking the balance
     recursiveBalance(p);
    } else {
     insertAVL(p.left,q);
    }
    
   } else if(q.key.getWordIndex()>=p.key.getWordIndex()) {
    if(p.right==null) {
     p.right = q;
     q.parent = p;
     
     // Node is inserted now, continue checking the balance
     recursiveBalance(p);
    } else {
     insertAVL(p.right,q);
    }
   } else {
    // do nothing: This node already exists
   }
  }
 }

 public void recursiveBalance(AvlNode cur) {
  
  // we do not use the balance in this class, but the store it anyway
  setBalance(cur);
  int balance = cur.balance;
  
  // check the balance
  if(balance==-2) {
   
   if(height(cur.left.left)>=height(cur.left.right)) {
    cur = rotateRight(cur);
   } else {
    cur = doubleRotateLeftRight(cur);
   }
  } else if(balance==2) {
   if(height(cur.right.right)>=height(cur.right.left)) {
    cur = rotateLeft(cur);
   } else {
    cur = doubleRotateRightLeft(cur);
   }
  }
  
  // we did not reach the root yet
  if(cur.parent!=null) {
   recursiveBalance(cur.parent);
  } else {
   this.root = cur;
  }
 }

 public void remove(Position k) {
  // First we must find the node, after this we can delete it.
  removeAVL(this.root,k);
 }

 public void removeAVL(AvlNode p,Position q) {
  if(p==null) {
   
   return;
  } else {
   if(p.key.getWordIndex()>q.getWordIndex())  {
    removeAVL(p.left,q);
   } else if(p.key.getWordIndex()<q.getWordIndex()) {
    removeAVL(p.right,q);
   } else if(p.key.getWordIndex()==q.getWordIndex()) {
    // we found the node in the tree.. now lets go on!
    removeFoundNode(p);
   }
  }
 }
 
 public void removeFoundNode(AvlNode q) {
  AvlNode r;
  // at least one child of q, q will be removed directly
  if(q.left==null ||q.right==null) {
   // the root is deleted
   if(q.parent==null) {
    this.root=null;
    q=null;
    return;
   }
   r = q;
  } else {
   // q has two children --> will be replaced by successor
   r = successor(q);
   q.key = r.key;
  }
  
  AvlNode p;
  if(r.left!=null) {
   p = r.left;
  } else {
   p = r.right;
  }
  
  if(p!=null) {
   p.parent = r.parent;
  }
  
  if(r.parent==null) {
   this.root = p;
  } else {
   if(r==r.parent.left) {
    r.parent.left=p;
   } else {
    r.parent.right = p;
   }
   // balancing must be done until the root is reached.
   recursiveBalance(r.parent);
  }
  r = null;
 }

 public AvlNode rotateLeft(AvlNode n) {
  
  AvlNode v = n.right;
  v.parent = n.parent;
  
  n.right = v.left;
  
  if(n.right!=null) {
   n.right.parent=n;
  }
  
  v.left = n;
  n.parent = v;
  
  if(v.parent!=null) {
   if(v.parent.right==n) {
    v.parent.right = v;
   } else if(v.parent.left==n) {
    v.parent.left = v;
   }
  }
  
  setBalance(n);
  setBalance(v);
  
  return v;
 }

 public AvlNode rotateRight(AvlNode n) {
  
  AvlNode v = n.left;
  v.parent = n.parent;
  
  n.left = v.right;
  
  if(n.left!=null) {
   n.left.parent=n;
  }
  
  v.right = n;
  n.parent = v;
  
  if(v.parent!=null) {
   if(v.parent.right==n) {
    v.parent.right = v;
   } else if(v.parent.left==n) {
    v.parent.left = v;
   }
  }
    setBalance(n);
  setBalance(v);
  
  return v;
 }

 public AvlNode doubleRotateLeftRight(AvlNode u) {
  u.left = rotateLeft(u.left);
  return rotateRight(u);
 }

 public AvlNode doubleRotateRightLeft(AvlNode u) {
  u.right = rotateRight(u.right);
  return rotateLeft(u);
 }
 
 
 public AvlNode successor(AvlNode q) {
  if(q.right!=null) {
   AvlNode r = q.right;
   while(r.left!=null) {
    r = r.left;
   }
   return r;
  } else {
   AvlNode p = q.parent;
   while(p!=null && q==p.right) {
    q = p;
    p = q.parent;
   }
   return p;
  }
 }

 private int height(AvlNode cur) {
  if(cur==null) {
   return -1;
  }
  if(cur.left==null && cur.right==null) {
   return 0;
  } else if(cur.left==null) {
   return 1+height(cur.right);
  } else if(cur.right==null) {
   return 1+height(cur.left);
  } else {
   return 1+maximum(height(cur.left),height(cur.right));
  }
 }

 private int maximum(int a, int b) {
  if(a>=b) {
   return a;
  } else {
   return b;
  }
 }
 private void setBalance(AvlNode cur) {
  cur.balance = height(cur.right)-height(cur.left);
 }
 public MyLinkedList<Position> getPositions(){
	 MyLinkedList<Position> list=new MyLinkedList<Position>();
	 list=getPositions(root,list);
	 return list;
 }
 public MyLinkedList<Position> getPositions(AvlNode n,MyLinkedList<Position> list){
 	if(n==null){
 		return list;
 	}
 	list=getPositions(n.left,list);
 	list.insertAtEnd(n.key);
 	list=getPositions(n.right,list);
 	return list;
 }
}


 class AvlNode {
 public AvlNode left;
 public AvlNode right;
 public AvlNode parent;
 public Position key;
 public int balance;

 public AvlNode(Position k) {
  left = right = parent = null;
  balance = 0;
  key = k;
 }

}
