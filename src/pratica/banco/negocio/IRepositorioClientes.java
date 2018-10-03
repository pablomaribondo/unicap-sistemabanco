package pratica.banco.negocio;

import pratica.banco.dados.AvlNode;
import pratica.banco.exceptions.ArvoreVaziaException;
import pratica.banco.exceptions.ClienteExistenteException;
import pratica.banco.exceptions.ClienteInexistenteException;

public interface IRepositorioClientes {

    void insert(Cliente cliente) throws ClienteExistenteException;

    Cliente search(String cpf) throws ClienteInexistenteException;

    void update(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException;

    Cliente remove(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException;

    Cliente removeRoot() throws ClienteInexistenteException, ArvoreVaziaException;

    AvlNode exists(String cpf);
    
    void display();

}
