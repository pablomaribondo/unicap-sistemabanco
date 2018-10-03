package pratica.banco.negocio;

import java.util.ArrayList;
import pratica.banco.exceptions.ArvoreVaziaException;
import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;
import pratica.banco.exceptions.SaldoInsuficienteException;

public class CadastroContas {

    private IRepositorioContas contas;

    public CadastroContas(IRepositorioContas contas) {
        this.contas = contas;
    }

    public void insert(ContaAbstrata conta) throws ContaExistenteException {
        contas.insert(conta);
    }

    public ContaAbstrata search(String numero) throws ContaInexistenteException, ArvoreVaziaException {
        return contas.search(numero);
    }

    public void update(ContaAbstrata conta) throws ContaInexistenteException, ArvoreVaziaException {
        contas.update(conta);
    }

    public ContaAbstrata remove(ContaAbstrata conta) throws ContaInexistenteException, ArvoreVaziaException {
        return contas.remove(conta);
    }

    public ContaAbstrata removeRoot() throws ContaInexistenteException, ArvoreVaziaException {
        return contas.removeRoot();
    }

    public ArrayList<ContaAbstrata> inorderIterate() throws ArvoreVaziaException {
        return contas.inorderIterate();
    }

    public void creditar(String numero, double valor) throws ContaInexistenteException, ArvoreVaziaException {
        ContaAbstrata conta = contas.search(numero);
        conta.creditar(valor);
    }

    public void debitar(String numero, double valor) throws ContaInexistenteException, SaldoInsuficienteException, ArvoreVaziaException {
        ContaAbstrata conta = contas.search(numero);
        conta.debitar(valor);
    }

    public void transferir(String numeroOrigem, String numeroDestino, double valor)
            throws ContaInexistenteException, SaldoInsuficienteException, ArvoreVaziaException {
        ContaAbstrata origem = contas.search(numeroOrigem);
        ContaAbstrata destino = contas.search(numeroDestino);
        origem.transferir(destino, valor);
    }

}
