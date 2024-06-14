package Tree_Module;
/*
 * References:
 * 1. LinkedList dari kode praktikum
 * 2. https://www.geeksforgeeks.org/linked-complete-binary-tree-its-creation/
 * 3. https://www.javatpoint.com/program-to-implement-binary-tree-using-the-linked-list
 */

public class LinkedBinaryTree<T> {
	private BinaryTreeNode<T> root; 

	public LinkedBinaryTree() {
	   root = null;
	}
 
	public LinkedBinaryTree (T element) {
	   root = new BinaryTreeNode<T> (element);
	}
	
	public LinkedBinaryTree(T element, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right){
		root = new BinaryTreeNode<T>(element);
		root.setLeftChild(left.root);
		root.setRightChild(right.root);
	}

	public void setParent(LinkedBinaryTree<T> parent){
		root.setParent(parent.root);
	}

	public T getRoot() {
		if (root == null){
			System.out.println("Tree is null");
		}
			return root.getElement();
	}
 
	public boolean isEmpty() {
		return (root == null);
	}
 
	public int size(){
		int result = 0;
		if(root != null){
			result = 1 + root.numChildren();
		}
			return result;
	}

	public LinkedBinaryTree<T> getLeft(){
		LinkedBinaryTree<T> subtree = new LinkedBinaryTree<T>();
		subtree.root = root.getLeftChild();
		return subtree;
	}

	public LinkedBinaryTree<T> getRight(){
		LinkedBinaryTree<T> subtree = new LinkedBinaryTree<T>();
		subtree.root = root.getRightChild();
		return subtree;
	}

	public LinkedBinaryTree<T> getParent(){
		LinkedBinaryTree<T> subtree = new LinkedBinaryTree<T>();
		subtree.root = root.getParent();
		return subtree;
	}

	public String levelOrder(){
		LinkedList<BinaryTreeNode<T>> nodes = new LinkedList<BinaryTreeNode<T>>();
		String result = "";
		nodes.add(root);
		while(nodes.size() != 0) {
			BinaryTreeNode<T> current = nodes.remove();
			if(current != null) {
				result += current.getElement() + "\n";
				nodes.add(current.getLeftChild());
				nodes.add(current.getRightChild());
			}
    	}
		return result;
  	}
}