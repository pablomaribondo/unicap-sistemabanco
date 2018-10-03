package pratica.banco.exceptions;

public class ContaInexistenteException extends Exception {

    private static final String MSG_CONTA_INEXISTENTE = "CONTA NÃO CADASTRADA!";

    public ContaInexistenteException() {
        super(MSG_CONTA_INEXISTENTE);
    }

}
