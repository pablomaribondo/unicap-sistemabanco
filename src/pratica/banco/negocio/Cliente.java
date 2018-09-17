package pratica.banco.negocio;

public class Cliente implements Comparable<Cliente> {

    private String cpf;

    public Cliente(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public int compareTo(Cliente cliente) {
        return cpf.compareTo(cliente.getCpf());
    }

    @Override
    public String toString() {
        return "CPF:" + cpf;
    }

}
