package pratica.banco.exceptions;

public class ClienteExistenteException extends Exception {

    private static final String MSG_CLIENTE_EXISTENTE = "CLIENTE JÁ CADASTRADO!";

    public ClienteExistenteException() {
        super(MSG_CLIENTE_EXISTENTE);
    }

}
