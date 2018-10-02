package pratica.banco.dados;

import java.util.ArrayList;

public class AVLTree<Object extends Comparable<? super Object>> {

    private AvlNode<Object> root;

    public AVLTree() {
        root = null;
    }

    public AvlNode insert(Object key) {
        return root = isEmpty() ? new AvlNode<Object>(key) : insert(this.root, key);
    }

    private AvlNode insert(AvlNode node, Object key) {
        if (node == null) {
            node = new AvlNode<>(key);
            return node;
        } else if (node.getInfo().compareTo(key) > 0) {
            node.setLeft(insert(node.getLeft(), key));
            node = balance(node);
        } else if (node.getInfo().compareTo(key) <= 0) {
            node.setRight(insert(node.getRight(), key));
            node = balance(node);
        }
        return node;
    }

    public AvlNode remove(Object key) {
        return remove(root, key);
    }

    private AvlNode remove(AvlNode node, Comparable key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.getInfo()) < 0) {
            node.setLeft(remove(node.getLeft(), key));
            return balance(node);
        } else if (key.compareTo(node.getInfo()) > 0) {
            node.setRight(remove(node.getRight(), key));
            return balance(node);
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                if (node.equals(root)) {
                    root = null;
                }
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
        }

        AvlNode leftBiggest = node.getLeft();
        while (leftBiggest.getRight() != null) {
            leftBiggest = leftBiggest.getRight();
        }
        node.setInfo(leftBiggest.getInfo());
        node.setLeft(remove(node.getLeft(), leftBiggest.getInfo()));
        node = balance(node);
        return node;

    }

    public AvlNode search(Comparable comparable, AvlNode node) {
        while (node != null) {
            if (comparable.compareTo(node.getInfo()) < 0) {
                node = node.getLeft();
            } else if (comparable.compareTo(node.getInfo()) > 0) {
                node = node.getRight();
            } else {
                return node;
            }
        }
        return null;
    }

    private AvlNode balance(AvlNode node) {
        int balancingFactor = balanceFactor(node);
        if (balancingFactor == -2) {
            if (node.getLeft() != null && node.getLeft().getBalance() == 1) {
                node = doubleRightRotation(node);
            } else {
                node = singleRightRotation(node);
            }
        } else if (balancingFactor == 2) {
            if (node.getRight() != null && node.getRight().getBalance() == -1) {
                node = doubleLeftRotation(node);
            } else {
                node = singleLeftRotation(node);
            }
        }
        return node;
    }

    private int balanceFactor(AvlNode node) {
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        int balancingFactor = rightHeight - leftHeight;
        node.setBalance(balancingFactor);
        return balancingFactor;
    }

    private int height(AvlNode node) {
        int height = 0;
        if (node != null) {
            int leftHeight = height(node.getLeft());
            int rightHeight = height(node.getRight());
            int maxHeight = Math.max(leftHeight, rightHeight);
            height = maxHeight + 1;
        }
        return height;
    }

    private AvlNode singleRightRotation(AvlNode a) {
        AvlNode b;
        b = a.getLeft();
        a.setLeft(b.getRight());
        b.setRight(a);
        if (a.equals(root)) {
            root = b;
        }
        balanceFactor(a);
        balanceFactor(b);
        return b;
    }

    private AvlNode singleLeftRotation(AvlNode a) {
        AvlNode b;
        b = a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
        if (a.equals(root)) {
            root = b;
        }
        balanceFactor(a);
        balanceFactor(b);
        return b;
    }

    private AvlNode doubleRightRotation(AvlNode a) {
        AvlNode c;
        c = a.getLeft();
        a.setLeft(singleLeftRotation(c));
        return singleRightRotation(a);
    }

    private AvlNode doubleLeftRotation(AvlNode a) {
        AvlNode c;
        c = a.getRight();
        a.setRight(singleRightRotation(c));
        return singleLeftRotation(a);
    }

    public void display(AvlNode ptr, int level) {
        int i;
        if (ptr != null) {
            display(ptr.getRight(), level + 1);
            System.out.println();
            if (ptr == root) {
                System.out.print("Root:");
            }
            for (i = 0; i < level && ptr != root; i++) {
                System.out.print("\t");
            }
            System.out.print(ptr.getBalance() + ":" + ptr.getInfo());
            display(ptr.getLeft(), level + 1);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public AvlNode<Object> getRoot() {
        return root;
    }

    public ArrayList<Object> inorderIterate(AvlNode<Object> root, ArrayList<Object> list) {
        if (root == null) {
            return null;
        } else {
            inorderIterate(root.getLeft(), list);
            list.add(root.getInfo());
            inorderIterate(root.getRight(), list);
        }
        return list;
    }

}
