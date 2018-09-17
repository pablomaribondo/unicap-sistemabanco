package pratica.banco.exceptions;

public class ClienteInexistenteException extends Exception {

    private String cpf;
    private static final String MSG_CLI_INEXISTENTE = "CLIENTE NÃO CADASTRADO!";

    public ClienteInexistenteException(String cpf) {
        super(MSG_CLI_INEXISTENTE);
        this.cpf = cpf;
    }

}
