package pratica.banco.negocio;

import pratica.banco.dados.RepositorioClientesAVL;
import pratica.banco.dados.RepositorioContasAVL;
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

    public Cliente removerCliente(Cliente cliente) throws ClienteInexistenteException {
        return clientes.remove(cliente);
    }
    
    public Cliente removerClienteRaiz() throws ClienteInexistenteException {
        return clientes.removeRoot();
    }

    public void atualizarCliente(Cliente cliente) throws ClienteInexistenteException {
        clientes.update(cliente);
    }

    public Cliente procurarCliente(String cpf) throws ClienteInexistenteException {
        return clientes.search(cpf);
    }

    public void cadastrarConta(ContaAbstrata conta) throws ContaExistenteException, ClienteInexistenteException, ClienteInvalidoException {
        Cliente cliente = conta.getCliente();
        if (cliente != null) {
            clientes.search(cliente.getCpf());
            contas.insert(conta);
        } else {
            throw new ClienteInvalidoException();
        }
    }

    public void removerConta(ContaAbstrata conta) throws ContaInexistenteException {
        contas.remove(conta);
    }
    
    public ContaAbstrata removerContaRaiz() throws ContaInexistenteException {
        return contas.removeRoot();
    }
    
    public void atualizarConta(ContaAbstrata conta) throws ContaInexistenteException {
        contas.update(conta);
    }

    public ContaAbstrata procurarConta(String numero) throws ContaInexistenteException {
        return contas.search(numero);
    }

    public void creditar(String numero, double valor) throws ContaInexistenteException {
        contas.creditar(numero, valor);
    }

    public void debitar(String numero, double valor) throws ContaInexistenteException, SaldoInsuficienteException {
        contas.debitar(numero, valor);
    }

    public void transferir(String numeroOrigem, String numeroDestino, double valor) throws ContaInexistenteException, SaldoInsuficienteException {
        contas.transferir(numeroOrigem, numeroDestino, valor);
    }
}