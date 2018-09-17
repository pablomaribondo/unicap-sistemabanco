package pratica.banco.exceptions;

public class ClienteExistenteException extends Exception {

    private String cpf;
    private static final String MSG_CLI_JA_EXISTENTE = "CLIENTE JÁ CADASTRADO!";

    public ClienteExistenteException(String cpf) {
        super(MSG_CLI_JA_EXISTENTE);
        this.cpf = cpf;
    }

}
