package pratica.banco.negocio;

import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;

public interface IRepositorioContas {

    void insert(ContaAbstrata conta) throws ContaExistenteException;

    ContaAbstrata search(String numero) throws ContaInexistenteException;

    void update(ContaAbstrata conta) throws ContaInexistenteException;

    void remove(ContaAbstrata conta) throws ContaInexistenteException;

    ContaAbstrata exists(String numero);

}
