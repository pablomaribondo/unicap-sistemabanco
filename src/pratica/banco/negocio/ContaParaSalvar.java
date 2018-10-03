package pratica.banco.negocio;

import java.io.Serializable;

public class ContaParaSalvar implements Serializable {

    private String numero;
    private double saldo;
    private String cpfCliente;
    private String tipo;

    public ContaParaSalvar(String numero, double saldo, String cpfCliente, String tipo) {
        this.numero = numero;
        this.saldo = saldo;
        this.cpfCliente = cpfCliente;
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getTipo() {
        return tipo;
    }
    
    

}
