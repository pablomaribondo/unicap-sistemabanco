package pratica.banco.dados;

import java.util.ArrayList;
import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;
import pratica.banco.negocio.ContaAbstrata;
import pratica.banco.negocio.IRepositorioContas;

public class RepositorioContasAVL implements IRepositorioContas {

    private AVLTree<ContaAbstrata> contas;

    public RepositorioContasAVL() {
        contas = new AVLTree<>();
    }

    public void insert(ContaAbstrata conta) throws ContaExistenteException {
        if (exists(conta.getNumero()) == null) {
            contas.insert(conta);
        } else {
            throw new ContaExistenteException(conta.getNumero());
        }
    }

    public ContaAbstrata search(String numero) throws ContaInexistenteException {
        ContaAbstrata conta = exists(numero);
        if (conta != null) {
            return conta;
        } else {
            throw new ContaInexistenteException(numero);
        }
    }

    public ArrayList<ContaAbstrata> inorderIterate(ArrayList<ContaAbstrata> list) {
        return contas.inorderIterate(contas.getRoot(), list);
    }

    public void update(ContaAbstrata conta) throws ContaInexistenteException {
        if (exists(conta.getNumero()) != null) {
            contas.remove(conta);
            contas.insert(conta);
        } else {
            throw new ContaInexistenteException(conta.getNumero());
        }
    }

    public ContaAbstrata remove(ContaAbstrata conta) throws ContaInexistenteException {
        if (exists(conta.getNumero()) != null) {
            contas.remove(conta);
            return conta;
        } else {
            throw new ContaInexistenteException(conta.getNumero());
        }
    }

    public ContaAbstrata removeRoot() throws ContaInexistenteException {
        if (contas.getRoot() != null) {
            ContaAbstrata root = contas.getRoot().getInfo();
            contas.remove(root);
            return root;
        } else {
            throw new ContaInexistenteException("Empty tree!");
        }
    }

    public ContaAbstrata exists(String numero) {
        ContaAbstrata conta = (ContaAbstrata) contas.search(numero, contas.getRoot()).getInfo();
        return conta != null ? conta : null;
    }

}
