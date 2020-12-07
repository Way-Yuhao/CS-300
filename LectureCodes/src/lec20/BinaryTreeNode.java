package lec20;

public class BinaryTreeNode <T extends Comparable<T>>{
    public T data;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;
    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public BinaryTreeNode(T data) {this(data, null, null);}
    public boolean contains (T findData) {
        if (data.equals(findData)) return true;
        if (data.compareTo(findData) > 0) {
            if (left != null && left.contains(findData)) return true;
        }else {
            if (right != null && right.contains(findData)) return true;
        }
        return false;
    }
    
    public String toString() {
    	String str = "";
    	str += String.valueOf(this.data) + " ";
    	if (this.left != null) {
    		str += this.left.toString();
    	}
    	if (this.right != null) {
    		str += this.right.toString();
    	}
    	return str;//FIXME
    }
    public static void main(String[] args) {
        BinaryTreeNode<String> root = new BinaryTreeNode<>("D",
            new BinaryTreeNode<>("B", new BinaryTreeNode<>("A"), new BinaryTreeNode<>("C")),
            new BinaryTreeNode<>("F", new BinaryTreeNode<>("E"), new BinaryTreeNode<>("G")));
        System.out.println(root.right.left.data);
        //replacing "B" and the pointer to C with "X" and the pointer to C
        System.out.println(root.left.left.data);
        System.out.println(root.contains("A"));
        System.out.println(root.toString());
    }
    
}
