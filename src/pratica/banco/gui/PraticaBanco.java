package pratica.banco.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import pratica.banco.dados.CadastroEmArquivo;
import pratica.banco.exceptions.ArvoreVaziaException;
import pratica.banco.exceptions.ClienteContaCadastradaException;
import pratica.banco.exceptions.ClienteExistenteException;
import pratica.banco.exceptions.ClienteInexistenteException;
import pratica.banco.exceptions.ClienteInvalidoException;
import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;
import pratica.banco.exceptions.CpfInvalidoException;
import pratica.banco.exceptions.SaldoInsuficienteException;
import pratica.banco.negocio.Cliente;
import pratica.banco.negocio.ContaAbstrata;
import pratica.banco.negocio.ContaParaSalvar;
import pratica.banco.negocio.Fachada;
import pratica.banco.negocio.TipoConta;

public class PraticaBanco {

    public static int menuPrincipal() {
        int option = 100;
        do {
            try {
                System.out.println("--------------------\n\tMENU\n--------------------\n");
                System.out.println("1. MENU CONTAS\n2. MENU CLIENTES\n0. SAIR DO SISTEMA");
                System.out.print("\nDIGITE AQUI SUA OPCAO: ");
                BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                option = Integer.parseInt(read.readLine());
                switch (option) {
                    case 1:
                        option = menuContas();
                        break;
                    case 2:
                        option = menuClientes();
                        break;
                    case 0:
                        System.out.println("\nVOCE SAIU DO SISTEMA");
                        return 0;
                    default:
                        System.out.println("OPCAO INVALIDA, TENTE NOVAMENTE!");
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } while (option != 0 && option != 1 && option != 2);
        return 0;
    }

    public static int menuClientes() {
        Fachada fachada = Fachada.obterInstancia();
        int option = 100;
        String cpf, nome, telefone, email;
        Cliente cliente;
        do {
            try {
                System.out.println("----------------------------\n\tMENU CLIENTES\n----------------------------\n");
                System.out.println("1. CADASTRAR NOVO CLIENTE\n2. PROCURAR CLIENTE\n"
                        + "3. ATUALIZAR CLIENTE\n4. REMOVER CLIENTE\n"
                        + "5. EXIBIR TODAS AS CONTAS DO CLIENTE\n9. VOLTAR PARA MENU PRINCIPAL"
                        + "\n0. SAIR DO SISTEMA");
                System.out.print("\nDIGITE AQUI SUA OPCAO: ");
                BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                option = Integer.parseInt(read.readLine());

                switch (option) {
                    case 1:
                        System.out.print("\nDIGITE O CPF DO CLIENTE: ");
                        cpf = read.readLine();
                        if (!fachada.validarCPF(cpf)) {
                            throw new CpfInvalidoException();
                        }
                        try {
                            fachada.procurarCliente(cpf);
                        } catch (ClienteInexistenteException ex) {
                            System.err.println(ex.getMessage());
                        } catch (ArvoreVaziaException ex) {

                        }
                        System.out.print("DIGITE O NOME DO CLIENTE: ");
                        nome = read.readLine();
                        System.out.print("DIGITE O EMAIL DO CLIENTE: ");
                        email = read.readLine();
                        System.out.print("DIGITE O TELEFONE DO CLIENTE: ");
                        telefone = read.readLine();
                        try {
                            fachada.cadastrarCliente(new Cliente(cpf, nome, email, telefone));
                            System.out.println("\nCLIENTE CADASTRADO!\n");
                        } catch (ClienteExistenteException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("\nDIGITE O CPF DO CLIENTE: ");
                        cpf = read.readLine();
                        if (!fachada.validarCPF(cpf)) {
                            throw new CpfInvalidoException();
                        }
                        try {
                            cliente = fachada.procurarCliente(cpf);
                            System.out.println("\n" + cliente + "\n");
                        } catch (ClienteInexistenteException | ArvoreVaziaException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("\nDIGITE O CPF DO CLIENTE: ");
                        cpf = read.readLine();
                        if (!fachada.validarCPF(cpf)) {
                            throw new CpfInvalidoException();
                        }
                        try {
                            cliente = fachada.procurarCliente(cpf);
                            System.out.println("\n" + cliente);
                        } catch (ClienteInexistenteException | ArvoreVaziaException ex) {
                            System.err.println(ex.getMessage());
                        }
                        System.out.print("\nALTERE O NOME DO CLIENTE: ");
                        nome = read.readLine();
                        System.out.print("ALTERE O EMAIL DO CLIENTE: ");
                        email = read.readLine();
                        System.out.print("ALTERE O TELEFONE DO CLIENTE: ");
                        telefone = read.readLine();
                        try {
                            cliente = fachada.atualizarCliente(new Cliente(cpf, nome, email, telefone));
                            System.out.println("\n" + cliente);
                            System.out.println("\nCLIENTE ATUALIZADO!\n");
                        } catch (ClienteInexistenteException | ArvoreVaziaException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("\nDIGITE O CPF DO CLIENTE: ");
                        cpf = read.readLine();
                        if (!fachada.validarCPF(cpf)) {
                            throw new CpfInvalidoException();
                        }
                        try {
                            cliente = fachada.procurarCliente(cpf);
                            fachada.removerCliente(cliente);
                            System.out.println("\nCLIENTE REMOVIDO!\n");
                        } catch (ClienteInexistenteException | ClienteContaCadastradaException | ArvoreVaziaException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 5:
                        System.out.print("\nDIGITE O CPF DO CLIENTE: ");
                        cpf = read.readLine();
                        if (!fachada.validarCPF(cpf)) {
                            throw new CpfInvalidoException();
                        }
                        try {
                            cliente = fachada.procurarCliente(cpf);
                            System.out.println("\n" + cliente + "\n");
                            fachada.exibirContasDoCliente(cpf);
                        } catch (ClienteInexistenteException | ArvoreVaziaException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 9:
                        System.out.println("\nVOLTAR PARA MENU PRINCIPAL");
                        option = menuPrincipal();
                        break;
                    case 0:
                        System.out.println("\nVOCE SAIU DO SISTEMA");
                        return 0;
                    default:
                        System.out.println("OPCAO INVALIDA, TENTE NOVAMENTE!");
                }
            } catch (IOException | CpfInvalidoException ex) {
                System.err.println(ex.getMessage());
            }
        } while (option != 0);
        return 0;
    }

    public static int menuContas() {
        Fachada fachada = Fachada.obterInstancia();
        int option = 100, tipoconta;
        double valor;
        String cpf, numero, numeroDestino;
        ContaAbstrata conta;
        Cliente cliente;

        do {
            try {
                System.out.println("------------------------\n\tMENU CONTAS\n------------------------\n");
                System.out.println("1. CADASTRAR NOVA CONTA\n2. PROCURAR CONTA\n"
                        + "3. REMOVER CONTA\n4. DEBITAR\n5. CREDITAR\n6. TRANSFERIR\n9. VOLTAR PARA MENU PRINCIPAL\n0. SAIR DO SISTEMA");
                System.out.print("\nDIGITE AQUI SUA OPCAO: ");
                BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                option = Integer.parseInt(read.readLine());

                switch (option) {
                    case 1:
                        System.out.print("\nDIGITE O NUMERO DA CONTA: ");
                        numero = read.readLine();
                        try {
                            fachada.procurarConta(numero);
                        } catch (ContaInexistenteException ex) {
                            System.err.println(ex.getMessage());
                        } catch (ArvoreVaziaException ex) {

                        }
                        System.out.print("DIGITE O CPF DO CLIENTE: ");
                        cpf = read.readLine();
                        if (!fachada.validarCPF(cpf)) {
                            throw new CpfInvalidoException();
                        }
                        try {
                            cliente = fachada.procurarCliente(cpf);
                            System.out.println("\nDIGITE O TIPO DE CONTA:\n1.CONTA SIMPLES\n2.CONTA ESPECIAL\n");
                            tipoconta = Integer.parseInt(read.readLine());
                            conta = tipoconta == 1 ? TipoConta.SIMPLES.instanciar(numero, 0, cliente) : TipoConta.ESPECIAL.instanciar(numero, 0, cliente);
                            fachada.cadastrarConta(conta);
                            System.out.println("\nCONTA CADASTRADA!\n");
                        } catch (ClienteInexistenteException | ClienteInvalidoException ex) {
                            System.err.println(ex.getMessage());
                        } catch (ContaExistenteException | ArvoreVaziaException ex) {
                            
                        }
                        break;
                    case 2:
                        System.out.print("\nDIGITE O NUMERO DA CONTA: ");
                        numero = read.readLine();
                        try {
                            conta = fachada.procurarConta(numero);
                            System.out.println("\n" + conta + "\n");
                        } catch (ContaInexistenteException | ArvoreVaziaException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("\nDIGITE O NUMERO DA CONTA: ");
                        numero = read.readLine();
                        try {
                            conta = fachada.procurarConta(numero);
                            fachada.removerConta(conta);
                            System.out.println("\nCLIENTE REMOVIDO!\n");
                        } catch (ContaInexistenteException | ArvoreVaziaException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("\nDIGITE O NUMERO DA CONTA: ");
                        numero = read.readLine();
                        try {
                            conta = fachada.procurarConta(numero);
                            System.out.println("\n" + conta);
                            System.out.print("\nDIGITE O VALOR: ");
                            valor = Double.parseDouble(read.readLine());
                            fachada.debitar(numero, valor);
                        } catch (ContaInexistenteException | SaldoInsuficienteException | ArvoreVaziaException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 5:
                        System.out.print("\nDIGITE O NUMERO DA CONTA: ");
                        numero = read.readLine();
                        try {
                            conta = fachada.procurarConta(numero);
                            System.out.println("\n" + conta);
                            System.out.print("\nDIGITE O VALOR: ");
                            valor = Double.parseDouble(read.readLine());
                            fachada.creditar(numero, valor);
                        } catch (ContaInexistenteException | ArvoreVaziaException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 6:
                        System.out.print("\nDIGITE O NUMERO DA CONTA ORIGEM: ");
                        numero = read.readLine();
                        try {
                            fachada.procurarConta(numero);
                            System.out.print("\nDIGITE O NUMERO DA CONTA DESTINO: ");
                            numeroDestino = read.readLine();
                            fachada.procurarConta(numeroDestino);
                            System.out.print("\nDIGITE O VALOR: ");
                            valor = Double.parseDouble(read.readLine());
                            fachada.transferir(numero, numeroDestino, valor);
                        } catch (ContaInexistenteException | SaldoInsuficienteException | ArvoreVaziaException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 9:
                        System.out.println("\nVOLTAR PARA MENU PRINCIPAL");
                        option = menuPrincipal();
                        break;
                    case 0:
                        System.out.println("\nVOCE SAIU DO SISTEMA");
                        return 0;
                    default:
                        System.out.println("OPCAO INVALIDA, TENTE NOVAMENTE!");
                }
            } catch (IOException | CpfInvalidoException ex) {
                System.err.println(ex.getMessage());
            }
        } while (option != 0);
        return 0;
    }

    public static void main(String[] args) {
        Fachada fachada = Fachada.obterInstancia();

        CadastroEmArquivo<ContaParaSalvar> cadastroEmArquivoContas = new CadastroEmArquivo();
        String nomeArquivoContas = "contas.dat";
        File arquivoContas = new File(nomeArquivoContas);

        CadastroEmArquivo<Cliente> cadastroEmArquivoClientes = new CadastroEmArquivo();
        String nomeArquivoClientes = "clientes.dat";
        File arquivoClientes = new File(nomeArquivoClientes);

        String tipo;
        ContaAbstrata conta;
        ContaParaSalvar contaparasalvar;
        Cliente cliente;

        if (arquivoClientes.exists()) {
            cadastroEmArquivoClientes.opentoRead(nomeArquivoClientes);
            try {
                do {
                    cliente = cadastroEmArquivoClientes.readObjectFromFile();
                    if (cliente != null) {
                        fachada.cadastrarCliente(cliente);
                    }
                } while (cliente != null);
                cadastroEmArquivoContas.closeAfterRead();
            } catch (ClienteExistenteException ex) {
                System.err.println(ex.getMessage());
            }
        }

        if (arquivoContas.exists()) {
            cadastroEmArquivoContas.opentoRead(nomeArquivoContas);
            try {
                do {
                    contaparasalvar = cadastroEmArquivoContas.readObjectFromFile();
                    if (contaparasalvar != null) {
                        cliente = fachada.procurarCliente(contaparasalvar.getCpfCliente());
                        tipo = contaparasalvar.getTipo();
                        conta = tipo.equalsIgnoreCase("SIMPLES") ? TipoConta.SIMPLES.instanciar(contaparasalvar.getNumero(), contaparasalvar.getSaldo(), cliente) : TipoConta.ESPECIAL.instanciar(contaparasalvar.getNumero(), contaparasalvar.getSaldo(), cliente);
                        fachada.cadastrarConta(conta);
                    }
                } while (contaparasalvar != null);
                cadastroEmArquivoContas.closeAfterRead();
            } catch (ContaExistenteException | ClienteInexistenteException | ClienteInvalidoException ex) {
                System.err.println(ex.getMessage());
            } catch (ArvoreVaziaException ex) {
                
            }
        }

        menuPrincipal();
        
        cadastroEmArquivoContas.openToWrite(nomeArquivoContas);
        try {
            do {
                conta = fachada.removerContaRaiz();
                if (conta != null) {
                    contaparasalvar = new ContaParaSalvar(conta.getNumero(), conta.getSaldo(), conta.getCliente().getCpf(), conta.getTipo().valor());
                    cadastroEmArquivoContas.writeObjectOnFile(contaparasalvar);
                }
            } while (conta != null);
        } catch (ContaInexistenteException ex) {
            System.err.println(ex.getMessage());
        } 
        cadastroEmArquivoContas.closeAfterWrite();

        cadastroEmArquivoClientes.openToWrite(nomeArquivoClientes);
        try {
            do {
                cliente = fachada.removerClienteRaiz();
                if (cliente != null) {
                    cadastroEmArquivoClientes.writeObjectOnFile(cliente);
                }
            } while (cliente != null);
        } catch (ClienteInexistenteException ex) {
            System.err.println(ex.getMessage());
        }
        cadastroEmArquivoClientes.closeAfterWrite();
    }
}
