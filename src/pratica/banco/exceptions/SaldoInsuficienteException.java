package pratica.banco.exceptions;

public class SaldoInsuficienteException extends Exception {

    private String numero;
    private double saldo;
    private static final String MSG_SALDO_INSUFICIENTE = "SALDO INSUFICIENTE PARA REALIZAR OPERAÇÃO DE DÉBITO OU DE TRANSFERÊNCIA!";

    public SaldoInsuficienteException(String numero, double saldo) {
        super(MSG_SALDO_INSUFICIENTE);
        this.numero = numero;
        this.saldo = saldo;
    }

}
