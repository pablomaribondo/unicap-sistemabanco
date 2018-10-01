package pratica.banco.dados;

import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;
import pratica.banco.negocio.ContaAbstrata;
import pratica.banco.negocio.IRepositorioContas;

public class RepositorioContasAVL implements IRepositorioContas {

    private AVLTree<ContaAbstrata> contas;

    public RepositorioContasAVL() {
        contas = new AVLTree<ContaAbstrata>();
    }

    public void insert(ContaAbstrata cliente) throws ContaExistenteException {
        if (exists(cliente.getNumero()) == null) {
            contas.insert(cliente);
        } else {
            throw new ContaExistenteException(cliente.getNumero());
        }
    }

    public ContaAbstrata search(String numero) throws ContaInexistenteException {
        ContaAbstrata cliente = exists(numero);
        if (cliente != null) {
            return cliente;
        } else {
            throw new ContaInexistenteException(numero);
        }
    }

    public void update(ContaAbstrata cliente) throws ContaInexistenteException {
        if (exists(cliente.getNumero()) != null) {
            contas.remove(cliente);
            contas.insert(cliente);
        } else {
            throw new ContaInexistenteException(cliente.getNumero());
        }
    }

    public void remove(ContaAbstrata cliente) throws ContaInexistenteException {
        if (exists(cliente.getNumero()) != null) {
            contas.remove(cliente);
        } else {
            throw new ContaInexistenteException(cliente.getNumero());
        }
    }

    public ContaAbstrata exists(String numero) {
        ContaAbstrata cliente = (ContaAbstrata) contas.search(numero, contas.getRoot()).getInfo();
        return cliente != null ? cliente : null;
    }

}
