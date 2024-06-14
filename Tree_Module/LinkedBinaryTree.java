package Tree_Module;
/*
 * References:
 * 1. LinkedList dari kode praktikum
 * 2. https://www.geeksforgeeks.org/linked-complete-binary-tree-its-creation/
 * 3. https://www.javatpoint.com/program-to-implement-binary-tree-using-the-linked-list
 */

/**
 * class untuk linked binary tree
 * dimana tree menyimpan node data berdasarkan parent, left, right node yang diberikan
 */
public class LinkedBinaryTree<T> {
	private TreeNode<T> root; 

	/**
	 * constructor untuk inisialisasi object LinkedBinaryTree<T>
	 */
	public LinkedBinaryTree() {
	   root = null;
	}
	
	/**
	 * constructor untuk inisialisasi value/element dari node tersebut
	 * @param element
	 */
	public LinkedBinaryTree (T element) {
	   root = new TreeNode<T> (element);
	}
	

	/**
	 * constructor untuk inisialisasi hubungan/linked untuk node tersebut 
	 * berdasarkan parameter;
	 * @param element isi dari node tersebut
	 * @param left child dari node tersebut
	 * @param right child dari node tersebut
	 */
	public LinkedBinaryTree(T element, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right){
		root = new TreeNode<T>(element);
		root.setLeftChild(left.root);
		root.setRightChild(right.root);
	}

	/**
	 * fungsi untuk menghubungan node tersebut dengan parent nodenya
	 * @param parent dari nodenya
	 */
	public void setParent(LinkedBinaryTree<T> parent){
		root.setParent(parent.root);
	}

	/**
	 * fungsi untuk mengembalikan value/element dari node tersebut
	 * @return T element/value dari node tersebut
	 */
	public T getElement() {
		//jika rootnya null maka return message tree is null
		if (root == null){
			System.out.println("Tree is null");
		}
			return root.getElement();
	}
	
	/**
	 * fungsi boolean untuk mengetahui apakah root null atau tida
	 * @return boolean
	 */
	public boolean isEmpty() {
		if(root == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * fungsi untuk mengembalikan jumlah children yang 
	 * dimiliki oleh node tersebut
	 * @return integer jumlah children
	 */
	public int size(){
		int result = 0;
		if(root != null){
			result = 1 + root.numChildren();
		}
			return result;
	}

	/**
	 * fungsi untuk mengembalikan node left child
	 * @return LinkedBinary<T> node left child
	 */
	public LinkedBinaryTree<T> getLeft(){
		LinkedBinaryTree<T> leftChild = new LinkedBinaryTree<T>();
		leftChild.root = root.getLeftChild();
		return leftChild;
	}

	/**
	 * fungsi untuk mengembalikan node right child
	 * @return LinkedBinary<T> node right child
	 */
	public LinkedBinaryTree<T> getRight(){
		LinkedBinaryTree<T> rightNode = new LinkedBinaryTree<T>();
		rightNode.root = root.getRightChild();
		return rightNode;
	}

	/**
	 * fungsi untuk mengembalikan node parent
	 * @return LinkedBinary<T> node parent
	 */
	public LinkedBinaryTree<T> getParent(){
		LinkedBinaryTree<T> parentNode = new LinkedBinaryTree<T>();
		parentNode.root = root.getParent();
		return parentNode;
	}

	/**
	 * fungsi untuk mengembalikan urutan level order dari LinkedBinaryTree tersebut
	 * @return string berisi semua node element dengan urutan level order
	 */
	public String levelOrder(){
		//menggunakan linkedlist untuk memasukan urutan node dengan tipe data sebagai TreeNode itu sendiri
		LinkedList<TreeNode<T>> nodes = new LinkedList<TreeNode<T>>();

		//inisialiasi string yang akan menyimpan urutan level ordernya
		String result = "";
		
		//linked list dari root
		nodes.add(root);
		
		//selama ukuran node bukan nol, maka akan terus di loop
		while(nodes.size() != 0) {
			
			//node current dihapus dari list
			TreeNode<T> current = nodes.remove();
			if(current != null) {
				//memasukan element dari node sekarang kedalam string
				result += current.getElement() + "\n";

				//linked list ditambah dengan left child dan right child dari node tersebut
				//sehingga result akan ditambah dengan urutan parent -> left -> right balik lagi ke parent (dari left) dan seterusnya
				nodes.add(current.getLeftChild());
				nodes.add(current.getRightChild());
			}
    	}
		return result;
  	}
}