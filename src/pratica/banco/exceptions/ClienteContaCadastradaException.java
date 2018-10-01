package pratica.banco.exceptions;

public class ClienteContaCadastradaException extends Exception {

    private String cpf;
        private static final String MSG_CLI_CONTA_CADASTRADA = "CLIENTE NÃO PODE SER REMOVIDO, POIS POSSUI CONTA CADASTRADA!";

    public ClienteContaCadastradaException(String cpf) {
        super(MSG_CLI_CONTA_CADASTRADA);
        this.cpf = cpf;
    }

}
