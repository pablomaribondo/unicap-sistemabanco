package pratica.banco.exceptions;

public class SaldoInsuficienteException extends Exception {

    private static final String MSG_SALDO_INSUFICIENTE = "SALDO INSUFICIENTE PARA REALIZAR OPERAÇÃO DE DÉBITO OU DE TRANSFERÊNCIA!";

    public SaldoInsuficienteException() {
        super(MSG_SALDO_INSUFICIENTE);
    }

}
