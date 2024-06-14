package Tree_Module;
public class BinaryTreeNode<T>{

   private T element;

   private BinaryTreeNode<T> parent, leftChild, rightChild;

   public BinaryTreeNode(T element) {
       this.element = element;
       this.parent = null;
       this.leftChild = null;
       this.rightChild = null;
   }

   public T getElement() {
       return element;
   }

   public void setElement(T element) {
       this.element = element;
   }

   public BinaryTreeNode<T> getLeftChild() {
      return leftChild;
   }

   public BinaryTreeNode<T> getRightChild() {
      return rightChild;
   }

   public BinaryTreeNode<T> getParent(){
      return parent;
   }

   public void setRightChild(BinaryTreeNode<T> node) {
      this.rightChild = node;
   }

   public void setLeftChild(BinaryTreeNode<T> node){
      this.leftChild = node;
   }

   public void setParent(BinaryTreeNode<T> node){
      this.parent = node;
   }

   public int numChildren(){

    int children = 0;

    if (leftChild != null) {
      children = 1 + leftChild.numChildren();
    }
    if (rightChild != null) {
      children = children + 1 + rightChild.numChildren();
    }

    return children;
  }

}