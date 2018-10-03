package pratica.banco.negocio;

import pratica.banco.exceptions.ArvoreVaziaException;
import pratica.banco.exceptions.ClienteExistenteException;
import pratica.banco.exceptions.ClienteInexistenteException;

public class CadastroClientes {

    private IRepositorioClientes clientes;

    public CadastroClientes(IRepositorioClientes clientes) {
        this.clientes = clientes;
    }

    public void insert(Cliente cliente) throws ClienteExistenteException {
        String cpf = cliente.getCpf();
        if (clientes.exists(cpf) == null) {
            clientes.insert(cliente);
        } else {
            throw new ClienteExistenteException(cpf);
        }
    }

    public Cliente search(String cpf) throws ClienteInexistenteException {
        return clientes.search(cpf);
    }

    public void update(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException {
        clientes.update(cliente);
    }

    public Cliente remove(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException {
        return clientes.remove(cliente);
    }
    
    public Cliente removeRoot() throws ClienteInexistenteException, ArvoreVaziaException {
        return clientes.removeRoot();
    }
    
    public void display() {
        clientes.display();
    }
}
