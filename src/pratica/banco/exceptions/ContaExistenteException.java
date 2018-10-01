package pratica.banco.exceptions;

public class ContaExistenteException extends Exception {

    public String numero;
    private static final String MSG_CTA_JA_EXISTENTE = "CONTA JÁ CADASTRADA!";

    public ContaExistenteException(String numero) {
        super(MSG_CTA_JA_EXISTENTE);
        this.numero = numero;
    }

}
