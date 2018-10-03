package pratica.banco.exceptions;

public class ClienteContaCadastradaException extends Exception {

    private static final String MSG_CLIENTE_CONTA_CADASTRADA = "CLIENTE NÃO PODE SER REMOVIDO, POIS POSSUI CONTA CADASTRADA!";

    public ClienteContaCadastradaException() {
        super(MSG_CLIENTE_CONTA_CADASTRADA);
    }

}
