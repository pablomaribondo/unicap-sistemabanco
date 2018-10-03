package pratica.banco.exceptions;

public class ClienteInexistenteException extends Exception {

    private static final String MSG_CLIENTE_INEXISTENTE = "CLIENTE NÃO CADASTRADO!";

    public ClienteInexistenteException() {
        super(MSG_CLIENTE_INEXISTENTE);
    }

}
