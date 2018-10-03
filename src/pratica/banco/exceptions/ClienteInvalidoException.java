package pratica.banco.exceptions;

public class ClienteInvalidoException extends Exception {

    private static final String MSG_CLIENTE_INVALIDO = "CLIENTE INVÁLIDO!";

    public ClienteInvalidoException() {
        super(MSG_CLIENTE_INVALIDO);
    }

}
