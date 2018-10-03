package pratica.banco.negocio;

import pratica.banco.exceptions.SaldoInsuficienteException;

public class ContaEspecial extends ContaAbstrata {

    public ContaEspecial(String numero, Cliente cliente) {
        super(numero, cliente);
    }

    public ContaEspecial(String numero, double saldo, Cliente cliente, TipoConta tipo) {
        super(numero, saldo, cliente, tipo);
    }

    public void debitar(double valor) throws SaldoInsuficienteException {
        double saldo = getSaldo();
        if (valor <= (saldo + 2000)) {
            setSaldo(saldo - valor);
        } else {
            throw new SaldoInsuficienteException();
        }
    }

}
