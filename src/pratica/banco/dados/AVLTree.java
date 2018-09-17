package pratica.banco.dados;

public class AVLTree<Object extends Comparable<? super Object>> {

    private AvlNode<Object> root;

    public AVLTree() {
        root = null;
    }

    public boolean insert(Object key) {
        if (root == null) {
            root = new AvlNode(key, null);
            return true;
        }

        AvlNode<Object> node = root;
        while (true) {
            if (node.getInfo().compareTo(key) == 0) {
                return false;
            }

            AvlNode<Object> parent = node;

            boolean goLeft = node.getInfo().compareTo(key) > 0;
            node = goLeft ? node.getLeft() : node.getRight();

            if (node == null) {
                if (goLeft) {
                    parent.setLeft(new AvlNode(key, parent));
                } else {
                    parent.setRight(new AvlNode(key, parent));
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }

    private void remove(AvlNode<Object> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            if (node.getParent() == null) {
                root = null;
            } else {
                AvlNode<Object> parent = node.getParent();
                if (parent.getLeft() == node) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                rebalance(parent);
            }
            return;
        }

        if (node.getLeft() != null) {
            AvlNode<Object> child = node.getLeft();
            while (child.getRight() != null) {
                child = child.getRight();
            }
            node.setInfo(child.getInfo());
            remove(child);
        } else {
            AvlNode<Object> child = node.getRight();
            while (child.getLeft() != null) {
                child = child.getLeft();
            }
            node.setInfo(child.getInfo());
            remove(child);
        }
    }

    public void remove(Object delInfo) {
        if (root == null) {
            return;
        }

        AvlNode<Object> child = root;
        while (child != null) {
            AvlNode<Object> node = child;
            child = node.getInfo().compareTo(delInfo) <= 0 ? node.getRight() : node.getLeft();
            if (node.getInfo().compareTo(delInfo) == 0) {
                remove(node);
                return;
            }
        }
    }

    private void rebalance(AvlNode<Object> node) {
        setBalance(node);

        if (node.getBalance() < -1) {
            if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())) {
                node = rotateRight(node);
            } else {
                node = rotateLeftThenRight(node);
            }

        } else if (node.getBalance() > 1) {
            if (height(node.getRight().getRight()) >= height(node.getRight().getLeft())) {
                node = rotateLeft(node);
            } else {
                node = rotateRightThenLeft(node);
            }
        }

        if (node.getParent() != null) {
            rebalance(node.getParent());
        } else {
            root = node;
        }
    }

    private AvlNode<Object> rotateLeft(AvlNode<Object> a) {

        AvlNode<Object> b = a.getRight();
        b.setParent(a.getParent());

        a.setRight(b.getLeft());

        if (a.getRight() != null) {
            a.getRight().setParent(a);
        }

        b.setLeft(a);
        a.setParent(b);

        if (b.getParent() != null) {
            if (b.getParent().getRight() == a) {
                b.getParent().setRight(b);
            } else {
                b.getParent().setLeft(b);
            }
        }

        setBalance(a, b);

        return b;
    }

    private AvlNode<Object> rotateRight(AvlNode<Object> a) {

        AvlNode<Object> b = a.getLeft();
        b.setParent(a.getParent());

        a.setLeft(b.getRight());

        if (a.getLeft() != null) {
            a.getLeft().setParent(a);
        }

        b.setRight(a);
        a.setParent(b);

        if (b.getParent() != null) {
            if (b.getParent().getRight() == a) {
                b.getParent().setRight(b);
            } else {
                b.getParent().setLeft(b);
            }
        }

        setBalance(a, b);

        return b;
    }

    private AvlNode<Object> rotateLeftThenRight(AvlNode<Object> node) {
        node.setLeft(rotateLeft(node.getLeft()));
        return rotateRight(node);
    }

    private AvlNode<Object> rotateRightThenLeft(AvlNode<Object> node) {
        node.setRight(rotateRight(node.getRight()));
        return rotateLeft(node);
    }

    private int height(AvlNode<Object> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    private void reheight(AvlNode<Object> node) {
        if (node != null) {
            node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        }
    }

    private void setBalance(AvlNode... nodes) {
        for (AvlNode<Object> node : nodes) {
            reheight(node);
            node.setBalance(height(node.getRight()) - height(node.getLeft()));
        }
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

    private AvlNode findMin(AvlNode node) {
        if (node == null) {
            return node;
        }

        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private AvlNode findMax(AvlNode node) {
        if (node == null) {
            return node;
        }

        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    public AvlNode search(Comparable c, AvlNode node) {
        while (node != null) {
            if (c.compareTo(node.getInfo()) < 0) {
                node = node.getLeft();
            } else if (c.compareTo(node.getInfo()) > 0) {
                node = node.getRight();
            } else {
                return node;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void printTree(AvlNode<Object> node, int level) {
        if (node == null) {
            return;
        }

        printTree(node.getRight(), level + 1);
        for (int i = 0; i < level; ++i) {
            System.out.print("\t\t\t");
        }
        System.out.println("(" + node.getInfo() + ", balance:" + node.getBalance() + ")");
        printTree(node.getLeft(), level + 1);
    }

    public AvlNode<Object> getRoot() {
        return root;
    }

}
