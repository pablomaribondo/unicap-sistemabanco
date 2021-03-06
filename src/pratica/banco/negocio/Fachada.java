package pratica.banco.negocio;

import java.util.ArrayList;
import pratica.banco.dados.RepositorioClientesAVL;
import pratica.banco.dados.RepositorioContasAVL;
import pratica.banco.exceptions.ArvoreVaziaException;
import pratica.banco.exceptions.ClienteContaCadastradaException;
import pratica.banco.exceptions.ClienteExistenteException;
import pratica.banco.exceptions.ClienteInexistenteException;
import pratica.banco.exceptions.ClienteInvalidoException;
import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;
import pratica.banco.exceptions.SaldoInsuficienteException;

public class Fachada {

    private static Fachada instancia;
    private CadastroContas contas;
    private CadastroClientes clientes;

    private Fachada() {
        initCadastros();
    }

    private void initCadastros() {

        IRepositorioContas repoContas = new RepositorioContasAVL();
        contas = new CadastroContas(repoContas);

        IRepositorioClientes repoClientes = new RepositorioClientesAVL();
        clientes = new CadastroClientes(repoClientes);
    }

    public static Fachada obterInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public void cadastrarCliente(Cliente cliente) throws ClienteExistenteException {
        clientes.insert(cliente);
    }

    public Cliente procurarCliente(String cpf) throws ClienteInexistenteException, ArvoreVaziaException {
        return clientes.search(cpf);
    }

    public Cliente atualizarCliente(Cliente cliente) throws ClienteInexistenteException, ArvoreVaziaException {
        return clientes.update(cliente);
    }

    public Cliente removerCliente(Cliente cliente) throws ClienteInexistenteException, ClienteContaCadastradaException, ArvoreVaziaException {
        ArrayList<ContaAbstrata> accounts = contas.inorderIterate();
        if (accounts == null) {
            return clientes.remove(cliente);
        } else {
            for (ContaAbstrata account : accounts) {
                if (cliente.getCpf().equals(account.getCliente().getCpf())) {
                    throw new ClienteContaCadastradaException();
                }
            }
        }
        return clientes.remove(cliente);
    }

    public Cliente removerClienteRaiz() throws ClienteInexistenteException {
        return clientes.removeRoot();
    }

    public void exibirClientes() throws ArvoreVaziaException {
        ArrayList<Cliente> listaClientes = clientes.inorderIterate();
        listaClientes.forEach((cliente) -> {
            System.out.println(cliente);
        });
    }

    public boolean validarCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }
        int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        Integer digito1 = validarCPF(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = validarCPF(cpf.substring(0, 9) + digito1, pesoCPF);

        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    private int validarCPF(String cpf, int[] peso) {
        int soma = 0, digito;
        for (int i = cpf.length() - 1; i >= 0; i--) {
            digito = Integer.parseInt(cpf.substring(i, i + 1));
            soma += digito * peso[peso.length - cpf.length() + i];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public void cadastrarConta(ContaAbstrata conta) throws ContaExistenteException, ClienteInexistenteException, ClienteInvalidoException, ArvoreVaziaException {
        Cliente cliente = conta.getCliente();
        if (cliente != null) {
            clientes.search(cliente.getCpf());
            contas.insert(conta);
        } else {
            throw new ClienteInvalidoException();
        }
    }

    public ContaAbstrata procurarConta(String numero) throws ContaInexistenteException, ArvoreVaziaException {
        return contas.search(numero);
    }

    public void atualizarConta(ContaAbstrata conta) throws ContaInexistenteException, ArvoreVaziaException {
        contas.update(conta);
    }

    public void removerConta(ContaAbstrata conta) throws ContaInexistenteException, ArvoreVaziaException {
        contas.remove(conta);
    }

    public ContaAbstrata removerContaRaiz() throws ContaInexistenteException {
        return contas.removeRoot();
    }

    public void exibirContas() throws ArvoreVaziaException {
        ArrayList<ContaAbstrata> listaContas = contas.inorderIterate();
        listaContas.forEach((conta) -> {
            System.out.println(conta);
        });
    }

    public void exibirContasDoCliente(String cpf) throws ArvoreVaziaException {
        ArrayList<ContaAbstrata> listaContas = contas.inorderIterate();
        ArrayList<ContaAbstrata> contasDoCliente = new ArrayList<>();
        listaContas.stream().filter((conta) -> (cpf.equals(conta.getCliente().getCpf()))).forEachOrdered((conta) -> {
            contasDoCliente.add(conta);
        });
        contasDoCliente.forEach((contaCliente) -> {
            System.out.println(contaCliente);
        });
    }

    public void creditar(String numero, double valor) throws ContaInexistenteException, ArvoreVaziaException {
        contas.creditar(numero, valor);
    }

    public void debitar(String numero, double valor) throws ContaInexistenteException, SaldoInsuficienteException, ArvoreVaziaException {
        contas.debitar(numero, valor);
    }

    public void transferir(String numeroOrigem, String numeroDestino, double valor) throws ContaInexistenteException, SaldoInsuficienteException, ArvoreVaziaException {
        contas.transferir(numeroOrigem, numeroDestino, valor);
    }

}
