package pratica.banco.negocio;

import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;
import pratica.banco.exceptions.SaldoInsuficienteException;

public class CadastroContas {

	private IRepositorioContas contas;

	public CadastroContas(IRepositorioContas contas) {
		// Dependência
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

	public void remove(ContaAbstrata conta) throws ContaInexistenteException {
		contas.remove(conta);
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
