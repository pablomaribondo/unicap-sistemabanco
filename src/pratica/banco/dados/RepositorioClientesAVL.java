package pratica.banco.dados;

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
        Cliente cliente = exists(cpf);
        if (cliente != null) {
            return cliente;
        } else {
            throw new ClienteInexistenteException(cpf);
        }
    }

    public void update(Cliente cliente) throws ClienteInexistenteException {
        if (exists(cliente.getCpf()) != null) {
            clientes.remove(cliente);
            clientes.insert(cliente);
        } else {
            throw new ClienteInexistenteException(cliente.getCpf());
        }
    }

    public Cliente remove(Cliente cliente) throws ClienteInexistenteException {
        if (exists(cliente.getCpf()) != null) {
            clientes.remove(cliente);
            return cliente;
        } else {
            throw new ClienteInexistenteException(cliente.getCpf());
        }
    }
    
    public Cliente removeRoot() throws ClienteInexistenteException {
        if (clientes.getRoot() != null) {
            Cliente root = clientes.getRoot().getInfo();
            clientes.remove(root);
            return root;
        } else {
            throw new ClienteInexistenteException("Empty tree!");
        }
    }

    public Cliente exists(String cpf) {
        Cliente cliente = (Cliente) clientes.search(cpf, clientes.getRoot()).getInfo();
        return cliente != null ? cliente : null;
    }

}
