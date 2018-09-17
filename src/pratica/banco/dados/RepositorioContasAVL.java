package pratica.banco.dados;

public class RepositorioContasAVL {

    private AvlNode root;

    public boolean insert(int key) {
        if (root == null) {
            root = new AvlNode(key, null);
            return true;
        }

        AvlNode node = root;
        while (true) {
            if (node.getKey() == key) {
                return false;
            }

            AvlNode parent = node;

            boolean goLeft = node.getKey() > key;
            node = goLeft ? node.getLeft() : node.getRight();

            if (node == null) {
                if (goLeft) {
                    parent.setLeft(new AvlNode(key, parent))    ;
                } else {
                    parent.setRight(new AvlNode(key, parent));
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }

    private void delete(AvlNode node) {
        if (node.getLeft() == null && node.getRight() == null) {
            if (node.getParent() == null) {
                root = null;
            } else {
                AvlNode parent = node.getParent();
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
            AvlNode child = node.getLeft();
            while (child.getRight() != null) {
                child = child.getRight();
            }
            node.setKey(child.getKey());
            delete(child);
        } else {
            AvlNode child = node.getRight();
            while (child.getLeft() != null) {
                child = child.getLeft();
            }
            node.setKey(child.getKey());
            delete(child);
        }
    }

    public void delete(int delKey) {
        if (root == null) {
            return;
        }

        AvlNode child = root;
        while (child != null) {
            AvlNode node = child;
            child = delKey >= node.getKey() ? node.getRight() : node.getLeft();
            if (delKey == node.getKey()) {
                delete(node);
                return;
            }
        }
    }

    private void rebalance(AvlNode node) {
        setBalance(node);

        if (node.getBalance() == -2) {
            if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())) {
                node = rotateRight(node);
            } else {
                node = rotateLeftThenRight(node);
            }

        } else if (node.getBalance() == 2) {
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

    private AvlNode rotateLeft(AvlNode a) {

        AvlNode b = a.getRight();
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

    private AvlNode rotateRight(AvlNode a) {

        AvlNode b = a.getLeft();
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

    private AvlNode rotateLeftThenRight(AvlNode node) {
        node.setLeft(rotateLeft(node.getLeft()));
        return rotateRight(node);
    }

    private AvlNode rotateRightThenLeft(AvlNode node) {
        node.setRight(rotateRight(node.getRight()));
        return rotateLeft(node);
    }

    private int height(AvlNode node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    private void setBalance(AvlNode... nodes) {
        for (AvlNode node : nodes) {
            reheight(node);
            node.setBalance(height(node.getRight()) - height(node.getLeft()));
        }
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(AvlNode node) {
        if (node != null) {
            printBalance(node.getLeft());
            System.out.printf("%s ", node.getBalance());
            printBalance(node.getRight());
        }
    }

        private void reheight(AvlNode node) {
        if (node != null) {
            node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        }
    }

    public void dump(AvlNode node, int level) {
        if (node == null) {
            return;
        }

        dump(node.getRight(), level + 1);
        for (int i = 0; i < level; ++i) {
            System.out.print("\t\t\t");
        }
        System.out.println("(key: " + node.getKey() + ", balance:" + node.getBalance() + ")");
        dump(node.getLeft(), level + 1);
    }

    public static void main(String[] args) {
        RepositorioContasAVL tree = new RepositorioContasAVL();

        System.out.println("Inserting values 1 to 10");
        for (int i = 1; i < 10; i++) {
            tree.insert(i);
            tree.dump(tree.root, 0);
            System.out.println("====================");
        }

        System.out.print("Printing balance: ");
        tree.printBalance();
    }
}
