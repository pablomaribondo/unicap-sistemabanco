package pratica.banco.exceptions;

public class ClienteInvalidoException extends Exception {

    private static final String MSG_CLI_INVALIDO = "CLIENTE INVÁLIDO!";

    public ClienteInvalidoException() {
        super(MSG_CLI_INVALIDO);
    }

}
