package pratica.banco.negocio;

import pratica.banco.exceptions.SaldoInsuficienteException;

public abstract class ContaAbstrata implements Comparable<ContaAbstrata> {

    private String numero;
    private double saldo;
    private Cliente cliente;
    private TipoConta tipo;

    public ContaAbstrata(String numero, double saldo, Cliente cliente, TipoConta tipo) {
        this.numero = numero;
        this.saldo = saldo;
        this.cliente = cliente;
        this.tipo = tipo;
    }

    public ContaAbstrata(String numero, Cliente cliente) {
        this(numero, 0, cliente, null);
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }

    public void creditar(double valor) {
        this.saldo += valor;
    }

    public abstract void debitar(double valor) throws SaldoInsuficienteException;

    public void transferir(ContaAbstrata conta, double valor) throws SaldoInsuficienteException {
        this.debitar(valor);
        conta.creditar(valor);
    }

    @Override
    public int compareTo(ContaAbstrata conta) {
        return numero.compareTo(conta.getNumero());
    }

    @Override
    public String toString() {
        return "Número: " + numero + ", Tipo:" + tipo + ", Saldo: " + saldo + ", Cliente: [" + cliente + "]";
    }

}
