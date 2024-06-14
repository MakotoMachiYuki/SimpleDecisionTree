package Tree_Module;

/**
 * Class TreeNode adalah untuk penempatan node yang ada di  tree 
 * yang memiliki left, right child dan node parent
 */
public class TreeNode<T>{

   private T element;

   private TreeNode<T> parent, leftChild, rightChild;

   /**
    * Constructor untuk membuat node baru dengan data yang diberikan
    * @param element data element yang menjadi node baru
    */
   public TreeNode(T element) {
       this.element = element;

       //parent, leftChild, rightChild null karena node masih sebuah node tanpa adanya 
       //hubungan/linked ke node lain
       this.parent = null;
       this.leftChild = null;
       this.rightChild = null;
   }

   /**
    * returns value/element/isi dari node yang diberikan
    * @return value/element/isi yang ada di node ini
    */
   public T getElement() {
       return element;
   }


   /**
    * menempatkan value/element/isi kedalam node ini
    * @param element value/element/isi yang akan masukan kedalam node
    */
   public void setElement(T element) {
       this.element = element;
   }

   /**
    * return left child dari node ini
    * @return left child dari node ini
    */
   public TreeNode<T> getLeftChild() {
      return leftChild;
   }

   /**
    * return right child dari node ini
    * @return right child dari node ini
    */
   public TreeNode<T> getRightChild() {
      return rightChild;
   }

   /**
    * return parent dari node ini
    * @return parent dari node ini
    */
   public TreeNode<T> getParent(){
      return parent;
   }

   /**
    * menempatkan right child untuk node ini
    * @param node yang dimasukan sebagai right child untuk node ini
    */
   public void setRightChild(TreeNode<T> node) {
      this.rightChild = node;
   }

   /**
    * menempatkan left child untuk node ini
    * @param node yang dimasukan sebagai left child untuk node ini 
    */
   public void setLeftChild(TreeNode<T> node){
      this.leftChild = node;
   }

   /**
    * menempatkan node parent untuk node ini
    * @param node yang dimasukan sebagai node parent untuk node ini
    */
   public void setParent(TreeNode<T> node){
      this.parent = node;
   }


   /**
    * Menghitung jumlah children yang ada di dalam sebuah node ini 
    * semua node dari left children + right children 
    * bukan dari root, tetapi dari node/parent itu sendiri
    * @return integer total children yang tersedia
    */
   public int numChildren(){

    int children = 0;

    //jika left child tidak null, maka memanggil fungsi sendiri (rekursif)
    //dan akan berhenti ketika leftchild null juga setiap pemanggilan fungsi 
    //increment children bertambah 1
    if (leftChild != null) {
      children = 1 + leftChild.numChildren();
    }

    //sama dengan left child, tetapi kali ini right child
    //sehingga fungsi ini menghitung semua node left dan right children hingga habis 
    if (rightChild != null) {
      children = children + 1 + rightChild.numChildren();
    }

    return children;
  }

}