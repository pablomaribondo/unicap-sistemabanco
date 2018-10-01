package pratica.banco.gui;

import pratica.banco.dados.AVLTree;
import pratica.banco.negocio.Cliente;

public class PraticaBanco {

    public static void main(String[] args) {
        AVLTree<Cliente> tree = new AVLTree<>();
        Cliente teste;
        for (int i = 0; i < 15; i++) {
            if (i < 9) {
                teste = new Cliente("0" + Integer.toString(i + 1));
            } else {
                teste = new Cliente(Integer.toString(i + 1));
            }
            
            tree.insert((Cliente) teste);
        }
        
       
    }
}
