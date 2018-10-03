package pratica.banco.exceptions;

public class ContaExistenteException extends Exception {

    private static final String MSG_CONTA_EXISTENTE = "CONTA JÁ CADASTRADA!";

    public ContaExistenteException() {
        super(MSG_CONTA_EXISTENTE);
    }

}
