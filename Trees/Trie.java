class TrNode{

   public char val;
   public int words;
   public int prefixes;
   public TrNode[] next ;

   public TrNode(){

       words = 0;
       prefixes = 0;
       next = new TrNode[26];
   }
  
}

class Trie{

   public TrNode root;

   public Trie(){

   root = new TrNode();

   }

  public void addWord(TrNode node,char[] word,int i){

         if(i == word.length) {  if(node.prefixes > 0){ System.out.println("prefix exist"+new String(word)); } node.words += 1; } 

         else{
                 if(node.words > 0) System.out.println("prefix exist"+new String(word));
                 node.prefixes += 1;
               
                 if(node.next[word[i] - 'a'] == null){
                 	 node.next[word[i]-'a'] = new TrNode();
                 }
               
                 addWord(node.next[word[i]-'a'],word,i+1);

         }
  } 


  public static int countWords(TrNode node){

   int ret = 0 ;  
   for(TrNode n : node.next)
            if(n != null){ ret = ret +  n.words + countWords(n) ; } 

  return ret; 
  
  }

  
  public static int countWordsWithPrefix(TrNode node,char[] prefix,int i){

     
      if(i == prefix.length) return node.prefixes ;

       int ret = 0;
      	      
              if(node.next[prefix[i] - 'a'] != null)
      	       ret = countWordsWithPrefix(node.next[prefix[i] - 'a'],prefix,i+1) ;
      	      else
      	      ret = 0;	
     

     return ret;

  } 


 public static int countWordsMissingLetter(TrNode node,char[] word,int i,int missL){

      if(i == word.length) return node.words;
  
      int count = 0 ;
      if(node.next[word[i] - 'a'] == null &&  missL == 0) return 0;
      
      else if(node.next[word[i] - 'a'] == null){
                 return  countWordsMissingLetter(node,word,i+1,missL - 1);
              	 
      }else{
 					count   = countWordsMissingLetter(node,word,i+1,missL-1);
                    count  += countWordsMissingLetter(node.next[word[i] - 'a'],word,i+1,missL);
                return count ;
      }
      
 }

  public static void main(String[] args){


      Trie tr = new Trie();

      //tr.add(tr.root);
      
     //  shel     schel  sbhel svhel 

      //String[] s = {"shells","shell","shore","sea","ses","trick","trie","tree","acid","abort","bride","mighty","maven","shcll","scell","hcell"};

     String[] s = {   "aacghgh","aabghgh","aab","aac"};
    
      for(String inp : s) 
           tr.addWord(tr.root,inp.toCharArray(),0);
      
      //tr.getNodes(tr.root);

      System.out.println("Total Words : " + countWords(tr.root));

      System.out.println("Total words with prefix aab:" + countWordsWithPrefix(tr.root,"aab".toCharArray(),0));

      System.out.println("Total words with missing onne letter :" + countWordsMissingLetter(tr.root,"sears".toCharArray(),0,2));
 }

}