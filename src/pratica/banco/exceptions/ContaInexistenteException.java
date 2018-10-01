package pratica.banco.exceptions;

public class ContaInexistenteException extends Exception {

    public String numero;
    private static final String MSG_CTA_INEXISTENTE = "CONTA NÃO CADASTRADA!";

    public ContaInexistenteException(String numero) {
        super(MSG_CTA_INEXISTENTE);
        this.numero = numero;
    }

}
