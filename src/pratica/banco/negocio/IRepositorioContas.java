package pratica.banco.negocio;

import java.util.ArrayList;
import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;

public interface IRepositorioContas {

    void insert(ContaAbstrata conta) throws ContaExistenteException;

    ContaAbstrata search(String numero) throws ContaInexistenteException;

    void update(ContaAbstrata conta) throws ContaInexistenteException;

    ContaAbstrata remove(ContaAbstrata conta) throws ContaInexistenteException;

    ContaAbstrata removeRoot() throws ContaInexistenteException;

    public ArrayList<ContaAbstrata> inorderIterate(ArrayList<ContaAbstrata> list);

    ContaAbstrata exists(String numero);

}
