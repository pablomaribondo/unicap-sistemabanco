package pratica.banco.negocio;

import java.util.ArrayList;
import pratica.banco.exceptions.ArvoreVaziaException;
import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;

public interface IRepositorioContas {

    void insert(ContaAbstrata conta) throws ContaExistenteException;

    ContaAbstrata search(String numero) throws ContaInexistenteException, ArvoreVaziaException;

    ContaAbstrata update(ContaAbstrata conta) throws ContaInexistenteException, ArvoreVaziaException;

    ContaAbstrata remove(ContaAbstrata conta) throws ContaInexistenteException, ArvoreVaziaException;

    ContaAbstrata removeRoot() throws ContaInexistenteException, ArvoreVaziaException;

    ArrayList<ContaAbstrata> inorderIterate() throws ArvoreVaziaException;

}
