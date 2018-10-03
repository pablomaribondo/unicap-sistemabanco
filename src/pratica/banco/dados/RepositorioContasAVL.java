package pratica.banco.dados;

import java.util.ArrayList;
import pratica.banco.exceptions.ArvoreVaziaException;
import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;
import pratica.banco.negocio.ContaAbstrata;
import pratica.banco.negocio.ContaSimples;
import pratica.banco.negocio.IRepositorioContas;

public class RepositorioContasAVL implements IRepositorioContas {

    private AVLTree<ContaAbstrata> contas;

    public RepositorioContasAVL() {
        contas = new AVLTree<>();
    }

    @Override
    public void insert(ContaAbstrata conta) throws ContaExistenteException {
        if (exists(conta.getNumero()) == null) {
            contas.insert(conta);
        } else {
            throw new ContaExistenteException(conta.getNumero());
        }
    }

    @Override
    public ContaAbstrata search(String numero) throws ContaInexistenteException, ArvoreVaziaException {
        if (!contas.isEmpty()) {
            AvlNode conta = exists(numero);
            if (conta != null) {
                return (ContaAbstrata) conta.getInfo();
            } else {
                throw new ContaInexistenteException(numero);
            }
        } else {
            throw new ArvoreVaziaException();
        }
    }

    @Override
    public ContaAbstrata update(ContaAbstrata conta) throws ContaInexistenteException, ArvoreVaziaException {
        if (!contas.isEmpty()) {
            AvlNode contaCadastrada = exists(conta.getNumero());
            if (contaCadastrada != null) {
                contaCadastrada = contas.update(contaCadastrada, conta);
                return (ContaAbstrata) contaCadastrada.getInfo();
            } else {
                throw new ContaInexistenteException(conta.getNumero());
            }
        } else {
            throw new ArvoreVaziaException();
        }
    }

    @Override
    public ContaAbstrata remove(ContaAbstrata conta) throws ContaInexistenteException, ArvoreVaziaException {
        if (!contas.isEmpty()) {
            if (exists(conta.getNumero()) != null) {
                contas.remove(conta);
                return conta;
            } else {
                throw new ContaInexistenteException(conta.getNumero());
            }
        } else {
            throw new ArvoreVaziaException();
        }
    }

    @Override
    public ContaAbstrata removeRoot() throws ContaInexistenteException, ArvoreVaziaException {
        if (!contas.isEmpty()) {
            ContaAbstrata conta = contas.getRoot().getInfo();
            contas.remove(conta);
            return conta;
        } else {
            throw new ArvoreVaziaException();
        }
    }

    @Override
    public ArrayList<ContaAbstrata> inorderIterate() throws ArvoreVaziaException {
        ArrayList<ContaAbstrata> list = new ArrayList<>();
        contas.inorderIterate(contas.getRoot(), list);
        if (list != null) {
            return list;
        } else {
            throw new ArvoreVaziaException();
        }
    }

    private AvlNode exists(String numero) {
        AvlNode conta = contas.search(contas.getRoot(), (ContaAbstrata) (new ContaSimples(numero, null)));
        return conta != null ? conta : null;
    }
}
