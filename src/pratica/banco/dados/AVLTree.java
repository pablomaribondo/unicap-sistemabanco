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

    private AvlNode insert(AvlNode root, Object key) {
        if (root == null) {
            root = new AvlNode<>(key);
            return root;
        } else if (root.getInfo().compareTo(key) > 0) {
            root.setLeft(insert(root.getLeft(), key));
            root = balance(root);
        } else if (root.getInfo().compareTo(key) <= 0) {
            root.setRight(insert(root.getRight(), key));
            root = balance(root);
        }
        return root;
    }

    public AvlNode remove(Object key) {
        return remove(this.root, key);
    }

    private AvlNode remove(AvlNode parent, Object key) {
        AvlNode node, aux, child;
        if (parent != null) {
            if (parent.getInfo().compareTo(key) > 0) {
                parent.setLeft(remove(parent.getLeft(), key));
            } else if (parent.getInfo().compareTo(key) < 0) {
                parent.setRight(remove(parent.getRight(), key));
            } else {
                node = parent;
                if (node.getRight() == null) {
                    parent = node.getLeft();
                } else if (node.getLeft() == null) {
                    parent = node.getRight();
                } else {
                    child = node.getLeft();
                    aux = node;
                    if (child.getRight() != null) {
                        while (child.getRight() != null) {
                            aux = child;
                            child = child.getRight();
                        }
                        parent.setInfo(child.getInfo());
                        node = child;
                        aux.setRight(child.getLeft());

                        aux = parent.getLeft();
                        child = child.getLeft();
                        if (aux != null) {
                            while ((aux != child) && (aux != null)) {
                                aux = balance(aux);
                                aux = aux.getRight();
                            }
                        }
                    } else {
                        parent.setInfo(child.getInfo());
                        parent.setLeft(child.getLeft());
                        node = child;
                    }
                }
            }
        }
        if (parent != null) {
            parent = balance(parent);
        }
        return parent;
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

    private int balanceFactor(AvlNode node) {
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        int balancingFactor = rightHeight - leftHeight;
        node.setBalance(balancingFactor);
        return balancingFactor;
    }

    private AvlNode singleRightRotation(AvlNode a) {
        AvlNode b;
        b = a.getLeft();
        a.setLeft(b.getRight());
        b.setRight(a);
        balanceFactor(a);
        balanceFactor(b);
        return b;
    }

    private AvlNode singleLeftRotation(AvlNode a) {
        AvlNode b;
        b = a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
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
            System.out.print(ptr.getInfo() + ":" + ptr.getBalance());
            display(ptr.getLeft(), level + 1);
        }
    }

    public void inorder(AvlNode tree) {
        if (tree == null) {
            return;
        }
        inorder(tree.getLeft());
        System.out.print(tree.getInfo() + " ");
        inorder(tree.getRight());
    }

    public void preorder(AvlNode tree) {
        if (tree == null) {
            return;
        }
        System.out.print(tree.getInfo() + " ");
        preorder(tree.getLeft());
        preorder(tree.getRight());

    }

    public void postorder(AvlNode tree) {
        if (tree == null) {
            return;
        }
        postorder(tree.getLeft());
        postorder(tree.getRight());
        System.out.print(tree.getInfo() + " ");
    }

    public AvlNode findMin() {
        AvlNode<Object> node = root;
        if (isEmpty()) {
            return node;
        }

        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public AvlNode findMax() {
        AvlNode<Object> node = root;
        if (isEmpty()) {
            return node;
        }

        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private AvlNode<Object> findParent(Object object) {
        return findParent(object, root, null);
    }

    private AvlNode<Object> findParent(Object object, AvlNode<Object> node, AvlNode<Object> parent) {
        if (node == null) {
            return null;
        } else if (node.getInfo().compareTo(object) != 0) {
            parent = findParent(object, node.getLeft(), node);
            if (parent == null) {
                parent = findParent(object, node.getRight(), node);
            }
        }
        return parent;
    }

    public AvlNode<Object> getRoot() {
        return root;
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(AvlNode<Object> node) {
        if (node != null) {
            printBalance(node.getLeft());
            System.out.printf("%s ", node.getBalance());
            printBalance(node.getRight());
        }
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
