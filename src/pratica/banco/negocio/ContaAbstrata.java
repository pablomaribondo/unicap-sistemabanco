package pratica.banco.negocio;

import pratica.banco.exceptions.SaldoInsuficienteException;

public abstract class ContaAbstrata implements Comparable<ContaAbstrata> {

    private String numero;
    private double saldo;
    private Cliente cliente;
    private TipoConta tipo;

    public ContaAbstrata(String numero, Cliente cliente) {
        this(numero, 0, cliente, null);
    }

    public ContaAbstrata(String numero, double saldo, Cliente cliente, TipoConta tipo) {
        setNumero(numero);
        setSaldo(saldo);
        setCliente(cliente);
        setTipo(tipo);
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

    public void creditar(double valor) {
        this.saldo += valor;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }

    public abstract void debitar(double valor) throws SaldoInsuficienteException;

    public void transferir(ContaAbstrata conta, double valor) throws SaldoInsuficienteException {
        this.debitar(valor);
        conta.creditar(valor);
    }

    public boolean equals(ContaAbstrata conta) {
        return this.numero.equals(conta.getNumero());
    }

    @Override
    public int compareTo(ContaAbstrata conta) {
        return numero.compareTo(conta.getNumero());
    }

    @Override
    public String toString() {
        return "Número: " + numero + ", tipo:" + tipo + ", saldo: " + saldo + ", cliente: " + cliente;
    }

}
