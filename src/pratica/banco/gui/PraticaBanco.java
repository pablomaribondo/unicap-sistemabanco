package pratica.banco.gui;

import pratica.banco.dados.AVLTree;
import pratica.banco.negocio.Cliente;

public class PraticaBanco {

    public static void main(String[] args) {
        AVLTree<Cliente> tree = new AVLTree();
        for (int i = 1; i < 10; i++) {
            tree.insert(new Cliente(Integer.toString(i)));
//            tree.printTree(tree.root, 0);
//            System.out.println("_________________________________");
        }
        tree.printTree(tree.getRoot(), 0);
//        System.out.println(tree.search(new Cliente(Integer.toString(5)), tree.getRoot()).getInfo());
    }
}
