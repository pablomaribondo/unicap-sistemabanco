package pratica.banco.negocio;

import pratica.banco.exceptions.SaldoInsuficienteException;

public class ContaEspecial extends ContaAbstrata {

    public double credito = 2000;

    public ContaEspecial(String numero, Cliente cliente) {
        super(numero, cliente);
    }

    public ContaEspecial(String numero, double saldo, Cliente cliente) {
        super(numero, saldo, cliente);
    }

    public void debitar(double valor) throws SaldoInsuficienteException {

    }

}
