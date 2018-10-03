package pratica.banco.dados;

import java.util.ArrayList;
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

    @Override
    public void insert(Cliente cliente) throws ClienteExistenteException {
        if (exists(cliente.getCpf()) == null) {
            clientes.insert(cliente);
        } else {
            throw new ClienteExistenteException();
        }
    }

    @Override
    public Cliente search(String cpf) throws ClienteInexistenteException, ArvoreVaziaException {
        if (!clientes.isEmpty()) {
            AvlNode cliente = exists(cpf);
            if (cliente != null) {
                return (Cliente) cliente.getInfo();
            } else {
                throw new ClienteInexistenteException();
            }
        } else {
            throw new ArvoreVaziaException();
        }
    }

    @Override
    public Cliente update(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException {
        if (!clientes.isEmpty()) {
            AvlNode clienteCadastrado = exists(cliente.getCpf());
            if (clienteCadastrado != null) {
                clienteCadastrado = clientes.update(clienteCadastrado, cliente);
                return (Cliente) clienteCadastrado.getInfo();
            } else {
                throw new ClienteInexistenteException();
            }
        } else {
            throw new ArvoreVaziaException();
        }
    }

    @Override
    public Cliente remove(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException {
        if (!clientes.isEmpty()) {
            if (exists(cliente.getCpf()) != null) {
                clientes.remove(cliente);
                return cliente;
            } else {
                throw new ClienteInexistenteException();
            }
        } else {
            throw new ArvoreVaziaException();
        }
    }

    @Override
    public Cliente removeRoot() throws ClienteInexistenteException, ArvoreVaziaException {
        if (!clientes.isEmpty()) {
            Cliente cliente = clientes.getRoot().getInfo();
            clientes.remove(cliente);
            return cliente;
        } else {
            throw new ArvoreVaziaException();
        }
    }

    @Override
    public ArrayList<Cliente> inorderIterate() throws ArvoreVaziaException {
        ArrayList<Cliente> list = new ArrayList<>();
        clientes.inorderIterate(clientes.getRoot(), list);
        if (list != null) {
            return list;
        } else {
            throw new ArvoreVaziaException();
        }

    }

    private AvlNode exists(String cpf) {
        AvlNode cliente = clientes.search(clientes.getRoot(), new Cliente(cpf));
        return cliente != null ? cliente : null;
    }

}
