package pratica.banco.dados;

public class AvlNode<Object extends Comparable<? super Object>> {

    private Object info;
    private int balance;
    private AvlNode left;
    private AvlNode right;

    AvlNode(Object info) {
        this.info = info;
        this.balance = 0;
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

}
