package pratica.banco.negocio;

import java.util.ArrayList;
import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;
import pratica.banco.exceptions.SaldoInsuficienteException;

public class CadastroContas {

    private IRepositorioContas contas;

    public CadastroContas(IRepositorioContas contas) {
        this.contas = contas;
    }

    public void insert(ContaAbstrata conta) throws ContaExistenteException {
        if (contas.exists(conta.getNumero()) != null) {
            contas.insert(conta);
        } else {
            throw new ContaExistenteException(conta.getNumero());
        }
    }

    public ContaAbstrata search(String numero) throws ContaInexistenteException {
        return contas.search(numero);
    }

    public void update(ContaAbstrata conta) throws ContaInexistenteException {
        contas.update(conta);
    }

    public ContaAbstrata remove(ContaAbstrata conta) throws ContaInexistenteException {
        return contas.remove(conta);
    }

    public ContaAbstrata removeRoot() throws ContaInexistenteException {
        return contas.removeRoot();
    }

    public ArrayList<ContaAbstrata> inorderIterate(ArrayList<ContaAbstrata> list) {
        return contas.inorderIterate(list);
    }

    public void creditar(String numero, double valor) throws ContaInexistenteException {
        ContaAbstrata conta = contas.search(numero);
        conta.creditar(valor);
    }

    public void debitar(String numero, double valor) throws ContaInexistenteException, SaldoInsuficienteException {
        ContaAbstrata conta = contas.search(numero);
        conta.debitar(valor);
    }

    public void transferir(String numeroOrigem, String numeroDestino, double valor)
            throws ContaInexistenteException, SaldoInsuficienteException {
        ContaAbstrata origem = contas.search(numeroOrigem);
        ContaAbstrata destino = contas.search(numeroDestino);
        origem.transferir(destino, valor);
    }

}
