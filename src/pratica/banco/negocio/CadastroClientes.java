package pratica.banco.negocio;

import java.util.ArrayList;
import pratica.banco.exceptions.ArvoreVaziaException;
import pratica.banco.exceptions.ClienteExistenteException;
import pratica.banco.exceptions.ClienteInexistenteException;

public class CadastroClientes {

    private final IRepositorioClientes clientes;

    public CadastroClientes(IRepositorioClientes clientes) {
        this.clientes = clientes;
    }

    public void insert(Cliente cliente) throws ClienteExistenteException {
        clientes.insert(cliente);
    }

    public Cliente search(String cpf) throws ClienteInexistenteException, ArvoreVaziaException {
        return clientes.search(cpf);
    }

    public Cliente update(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException {
        return clientes.update(cliente);
    }

    public Cliente remove(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException {
        return clientes.remove(cliente);
    }

    public Cliente removeRoot() throws ClienteInexistenteException {
        return clientes.removeRoot();
    }

    public ArrayList<Cliente> inorderIterate() throws ArvoreVaziaException {
        return clientes.inorderIterate();
    }
}
