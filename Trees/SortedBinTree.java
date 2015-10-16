import java.util.*;
import java.lang.*;

public class SortedBinTree {

  class TNode{

    int data ;
    TNode parent ;
    TNode lchild;
    TNode rchild;

    public TNode(int data){

       this.data = data ;
       this.parent = null;
       this.lchild = null;
       this.rchild = null;

    }

  }
  

  TNode root  = null;
  
  public boolean isBST(TNode nd,Integer min, Integer max){

    if(nd == null) return true ;

    if((max != null && nd.data > max) || (min != null && nd.data <= min  )) 
      return false;


    return (isBST(nd.lchild,min,nd.data) && isBST(nd.rchild,nd.data,max));    

  }


  

  public TNode createBinTree(int start,int end,int[] arr){
 
    TNode node = null;
    if(start > end)  {  return node ; }
    else{
     	    node = new TNode(arr[(start+end)/2]); 
    	    int mid = (start + end )/ 2;
    	    node.lchild = createBinTree(start,mid - 1,arr);
          node.rchild = createBinTree(mid + 1,end, arr) ;
          return node ; 
    }
  }
  
  public void preOrder(TNode node){

    if(node != null){
      int pdata = (node.parent == null ? -1 : node.parent.data);
      System.out.println(node.data + " : " +  pdata);
      preOrder(node.lchild);
      preOrder(node.rchild);

    }
  }
 
  


  public void postOrder(TNode node){

   if(node !=null){

   postOrder(node.lchild);
   postOrder(node.rchild);
   System.out.println(node.data);

   }

  }

  Queue<TNode>  que = new LinkedList<TNode>();
  
  public void createList(TNode node,int level){
     
	 TNode temp = null; 
	 que.add(node);
	 Iterator it = que.iterator();
	 while(!que.isEmpty()){
		 
		 temp = que.remove();
		 System.out.println(temp.data);
		 if(temp.lchild !=null)	 que.add(temp.lchild);
		 if(temp.rchild !=null)	 que.add(temp.rchild);	 
		  
	 }
     
  } 
 
 public void createListIteratively(TNode node){
	 
	LinkedList<TNode> lsNodes = new LinkedList<TNode>();
	lsNodes.add(node);
   
	ArrayList<LinkedList<TNode>> arrLsNodes = new ArrayList<LinkedList<TNode>>() ;
	arrLsNodes.add(lsNodes);
	
   while(!lsNodes.isEmpty()){
   
    LinkedList<TNode> tlsNode = new LinkedList<TNode>();
   
    for(TNode tn : lsNodes){
    	  if(tn.lchild != null)
    	  tlsNode.add(tn.lchild);
    	  if(tn.rchild !=null)
    	  tlsNode.add(tn.rchild);
   }
   
    if(!tlsNode.isEmpty())
	arrLsNodes.add(tlsNode);
	
	lsNodes = tlsNode ;  
 }	 
   int i = 0;
   System.out.println("Tester");
   for(LinkedList<TNode> la : arrLsNodes){
   	  System.out.println("Level:  " +i++);
   	  for(TNode tn : la)
   		   System.out.println(tn.data);
   }
 
 }
  
  
  public void createLinkedList(TNode node,ArrayList<LinkedList<TNode>> lists,int level){
	  
	  // do a postorder traversal adding the node at the level to the list
	  
	  if(node == null) return;
	  
	  LinkedList<TNode> levelList = null;
  
	  if(lists.size() == level){
		  levelList = new LinkedList<TNode>();
		  lists.add(levelList);
      }else{
    	  levelList = lists.get(level);
	  }
      
    levelList.add(node);
	  createLinkedList(node.lchild,lists,level + 1);
	  createLinkedList(node.rchild,lists,level + 1);
 
	  
  }

  public void testTheFlow(int num){




  }
  
  public int checkLevels(TNode node){
	  
	  if(node == null) return 0;
	  return Math.max(checkLevels(node.lchild),checkLevels(node.rchild)) + 1;		  
  }
  
  //This is wrong return the max of left and the right subtree
  // Math.max(checkLevels(root.lchild),checkLevels(root.rchild))
  
  public void checkBalanced(TNode root){
	  
	  int lleft = checkLevels(root.lchild);
	  int lright = checkLevels(root.rchild);
	  
	  System.out.println(" The left and the right levels are "+ lleft + " : "+ lright);
	  
  }
  

  public void findNextInorder(TNode nd){

     TNode temp = null;
     if(nd.rchild != null)  temp = findleftmost(nd.rchild) ;
     
     else{

            TNode currNode ;
            TNode parent ;
     
            currNode = nd;
            parent = nd.parent ;

            if(nd == nd.parent.lchild) System.out.println("Success");


            //trr.findNextInorder(trr.root.lchild);
            System.out.println("Parent" + nd.parent.data + " : " + parent.lchild.data);
            if(nd == parent.lchild) System.out.println("Success");

            while(parent != null && parent.lchild != currNode){

            parent = currNode.parent;
            currNode = parent ;
            System.out.println("Entered the loop");

          }

     temp = parent ;


     }
     
  
     System.out.println("The data is "+temp.data);
    
  }

  public TNode findleftmost(TNode nd){

     if(nd.lchild  == null) return nd ;

     else return findleftmost(nd.lchild);

  }
  
  public ArrayList<LinkedList<TNode>> createLinkedList(TNode node){
	  
	  ArrayList<LinkedList<TNode>> lists = new ArrayList<LinkedList<TNode>>();
	  createLinkedList(node,lists,0);
	  return lists ;
	  
  }
  
  public void inOrder(TNode node){

    if(node != null){
    	inOrder(node.lchild);
    	System.out.println(node.data);
    	inOrder(node.rchild);
    }
  }


  public TNode  createBinTree(TNode node,int data,TNode parent){

   if(node == null){

         TNode temp = new TNode(data);
         temp.lchild = null ;
         temp.rchild = null ;
         temp.parent = parent ;
         node = temp ;
         return node;
   }else{

            if(node.data > data) node.lchild = createBinTree(node.lchild,data,node);
            else node.rchild = createBinTree(node.rchild,data,node);
            return node ;
   }

      
  }


  public static void main_test(String[] args){

    
    int[] arr = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 } ;

    SortedBinTree tbin = new SortedBinTree();
    tbin.root = tbin.createBinTree(0,arr.length - 1,arr);
    //tbin.inOrder(tbin.root);

    System.out.println("The Preorder traversal");
    tbin.preOrder(tbin.root);
    
    System.out.println("The postOrder traversal");
    //tbin.postOrder(tbin.root);
    System.out.println("The Level order traversal");
   // tbin.createList(tbin.root,0);
    
    
//    ArrayList<LinkedList<TNode>> lt = tbin.createLinkedList(tbin.root);
//    System.out.println("Teste the code");
//    int i = 0;
//    for(LinkedList<TNode> la : lt){
//    	System.out.println("Level:  " +i++);
//    	 for(TNode tn : la){
//    		   System.out.println(tn.data);
//    	 }
//    	
//    }
    
   // tbin.createListIteratively(tbin.root);
    //tbin.checkBalanced(tbin.root);
    if(tbin.isBST(tbin.root,null,null)) System.out.println("The tree is balanced");
  }


 public void createTree(int data){

    this.root = createBinTree(this.root,data,null);

 }

  public static void main(String[] args){


     SortedBinTree trr = new SortedBinTree();

     trr.createTree(11);
     trr.createTree(4);
     trr.createTree(23);
     trr.createTree(18);
     trr.createTree(33);
     trr.createTree(20);
     trr.createTree(21);
     trr.createTree(34);
    

     trr.preOrder(trr.root);
     //System.out.println("Inorder Traversal");
     //trr.inOrder(trr.root);

     if(trr.isBST(trr.root,null,null)) System.out.println("The tree is BST");
 
     SortedBinTree trr1 = new SortedBinTree();
     trr1.createTree(10);
     trr1.createTree(25);

     SortedBinTree trr2 = new SortedBinTree();
     trr2.createTree(20);
     trr2.createTree(30);

     trr2.root.lchild = trr1.root;
     trr1.root.parent = trr2.root ;
     System.out.println("Inorder Traversal");
     trr2.preOrder(trr2.root);
     
     if(trr2.isBST(trr2.root,null,null)) System.out.println("The tree is BST");
     else System.out.println("The tree is not BST");

     trr2.checkBalanced(trr2.root);

     trr.checkBalanced(trr.root);

     trr.findNextInorder(trr.root.rchild);

     trr.findNextInorder(trr.root.lchild);
  
  }

}
