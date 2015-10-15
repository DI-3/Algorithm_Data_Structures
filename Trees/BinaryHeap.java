public class BinaryHeap{

    int[] heap ;
    int cap = 0 ;
    int size = 1;

    public BinaryHeap(int cap){
   
      this.cap = cap;
      heap = new int[cap];

    }
   
   public int parent(int index){
   
       return index/2 ;  

   } 

   public int leftchild(int index){
   
      return ( 2 * index) ;

   }

   public int rightchild(int index){
   
     return ( 2 * index + 1);

   }

   public int extractMin(){

         int ret = heap[1];

         heap[1] = heap[size-1];
         size--;
         heapify(1);

         return ret;

   }

   public void heapify(int i){

      int left = leftchild(i);
      int right = rightchild(i);

      
      if((left < size && heap[i] > heap[left] ) || ( right < size && heap[i] > heap[right] )){

            int index = heap[left] > heap[right] ? right : left ;
            swap(index,i);
            heapify(index);

      }

      
   }

  void swap(int i, int j){
    int temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }

   public void insert(int data){

     size++;
     int i = size - 1;

     heap[i] = data;

      while(i != 0 && heap[i] < heap[parent(i)]){
         
            swap(i,parent(i));
            i = parent(i);
           
     }
   
   }


  public static void main(String[] args){

      BinaryHeap hp = new BinaryHeap(12);
      hp.insert(3); //1
      hp.insert(2); //2
      hp.insert(1); //3
      hp.insert(15); //4
      hp.insert(5);  //5
      hp.insert(4);  //6
      hp.insert(22);
      hp.insert(8);
      hp.insert(11);
      hp.insert(7);
      hp.insert(9);

      for(int a :hp.heap) System.out.println(" " + a);

        System.out.println();

      for(int i = 0 ; i < 12 ; i++)
        System.out.print(" "+hp.extractMin());

      System.out.println();
        System.out.println();


        

  }

   
}