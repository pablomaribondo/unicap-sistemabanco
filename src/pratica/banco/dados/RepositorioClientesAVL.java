package pratica.banco.dados;

import pratica.banco.exceptions.ArvoreVaziaException;
import pratica.banco.exceptions.ClienteExistenteException;
import pratica.banco.exceptions.ClienteInexistenteException;
import pratica.banco.negocio.Cliente;
import pratica.banco.negocio.IRepositorioClientes;

public class RepositorioClientesAVL implements IRepositorioClientes {

    private AVLTree<Cliente> clientes;

    public RepositorioClientesAVL() {
        clientes = new AVLTree<>();
    }

    public void insert(Cliente cliente) throws ClienteExistenteException {
        if (exists(cliente.getCpf()) == null) {
            clientes.insert(cliente);
        } else {
            throw new ClienteExistenteException(cliente.getCpf());
        }
    }

    public Cliente search(String cpf) throws ClienteInexistenteException {
        AvlNode cliente = exists(cpf);
        if (cliente != null) {
            return (Cliente) cliente.getInfo();
        } else {
            throw new ClienteInexistenteException(cpf);
        }
    }

    public void update(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException {
        if (!clientes.isEmpty()) {
            AvlNode clienteCadastrado = exists(cliente.getCpf());
            if (clienteCadastrado != null) {
                clientes.update(clienteCadastrado, cliente);
            } else {
                throw new ClienteInexistenteException(cliente.getCpf());
            }
        } else {
            throw new ArvoreVaziaException();
        }
    }

    public Cliente remove(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException {
        if (!clientes.isEmpty()) {
            if (exists(cliente.getCpf()) != null) {
                clientes.remove(cliente);
                return cliente;
            } else {
                throw new ClienteInexistenteException(cliente.getCpf());
            }
        } else {
            throw new ArvoreVaziaException();
        }
    }

    public Cliente removeRoot() throws ClienteInexistenteException, ArvoreVaziaException {
        if (!clientes.isEmpty()) {
            Cliente root = clientes.getRoot().getInfo();
            clientes.remove(root);
            return root;
        } else {
            throw new ArvoreVaziaException();
        }
    }

    public AvlNode exists(String cpf) {
        AvlNode cliente = clientes.search(clientes.getRoot(), new Cliente(cpf));
        return cliente != null ? cliente : null;
    }

    public void display() {
        clientes.display(clientes.getRoot(), 1);
    }

}
