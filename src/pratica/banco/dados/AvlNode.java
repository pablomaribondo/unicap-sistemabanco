package pratica.banco.dados;

public class AvlNode<Object> {

    private Object info;
    private int balance;
    private int height;
    private AvlNode left;
    private AvlNode right;
    private AvlNode parent;

    AvlNode(Object info, AvlNode parent) {
        this.info = info;
        this.parent = parent;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
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
