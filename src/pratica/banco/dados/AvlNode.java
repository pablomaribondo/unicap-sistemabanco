package pratica.banco.dados;

public class AvlNode {

    private int key;
    private int balance;
    private int height;
    private AvlNode left;
    private AvlNode right;
    private AvlNode parent;

    AvlNode(int key, AvlNode parent) {
        this.key = key;
        this.parent = parent;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AvlNode getLeft() {
        return left;
    }

    public void setLeft(AvlNode left) {
        this.left = left;
    }

    public AvlNode getRight() {
        return right;
    }

    public void setRight(AvlNode right) {
        this.right = right;
    }

    public AvlNode getParent() {
        return parent;
    }

    public void setParent(AvlNode parent) {
        this.parent = parent;
    }
    
    
    
}
