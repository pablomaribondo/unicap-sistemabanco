package pratica.banco.exceptions;

public class CpfInvalidoException extends Exception {

    private static final String MSG_CPF_INVALIDO = "CPF INVÁLIDO!";

    public CpfInvalidoException() {
        super(MSG_CPF_INVALIDO);
    }

}
