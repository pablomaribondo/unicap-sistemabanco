package pratica.banco.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import pratica.banco.dados.CadastroEmArquivo;
import pratica.banco.exceptions.ClienteExistenteException;
import pratica.banco.exceptions.ClienteInexistenteException;
import pratica.banco.exceptions.ClienteInvalidoException;
import pratica.banco.exceptions.ContaExistenteException;
import pratica.banco.exceptions.ContaInexistenteException;
import pratica.banco.negocio.Cliente;
import pratica.banco.negocio.ContaAbstrata;
import pratica.banco.negocio.ContaParaSalvar;
import pratica.banco.negocio.Fachada;
import pratica.banco.negocio.TipoConta;

public class PraticaBanco {

    public static void menuPrincipal() {
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
                        menuContas();
                        break;
                    case 2:
                        menuClientes();
                        break;
                    case 0:
                        System.out.println("\nVOCE SAIU DO SISTEMA");
                        break;
                    default:
                        System.out.println("OPCAO INVALIDA, TENTE NOVAMENTE!");
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } while (option != 0 && option != 1 && option != 2);

    }

    public static void menuClientes() {
        Fachada fachada = Fachada.obterInstancia();
        int option = 100;
        String cpf, nome, telefone, email;
        Cliente cliente;
        do {
            try {
                System.out.println("------------------------\n\tMENU CLIENTES\n------------------------\n");
                System.out.println("1. CADASTRAR NOVO CLIENTE\n2. PROCURAR CLIENTE\n"
                        + "3. ATUALIZAR CLIENTE\n4. REMOVER CLIENTE\n"
                        + "5. EXIBIR TODOS OS CLIENTES\n9.VOLTAR PARA MENU PRINCIPAL"
                        + "\n0. SAIR DO SISTEMA");
                System.out.print("\nDIGITE AQUI SUA OPCAO: ");
                BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                option = Integer.parseInt(read.readLine());

                switch (option) {
                    case 1:
                        System.out.print("\nDIGITE O CPF DO CLIENTE: ");
                        cpf = read.readLine();
                        try {
                            cliente = fachada.procurarCliente(cpf);
                        } catch (ClienteInexistenteException ex) {
                            System.err.println(ex.getMessage());
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
                        try {
                            cliente = fachada.procurarCliente(cpf);
                            System.out.println(cliente);
                        } catch (ClienteInexistenteException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("\nDIGITE O CPF DO CLIENTE: ");
                        cpf = read.readLine();
                        try {
                            cliente = fachada.procurarCliente(cpf);
                            System.out.println(cliente);
                        } catch (ClienteInexistenteException ex) {
                            System.err.println(ex.getMessage());
                        }
                        System.out.print("\nALTERE O NOME DO CLIENTE: ");
                        nome = read.readLine();
                        System.out.print("ALTERE O EMAIL DO CLIENTE: ");
                        email = read.readLine();
                        System.out.print("ALTERE O TELEFONE DO CLIENTE: ");
                        telefone = read.readLine();
                        try {
                            fachada.atualizarCliente(new Cliente(cpf, nome, email, telefone));
                            System.out.println("\nCLIENTE ATUALIZADO!\n");
                        } catch (ClienteInexistenteException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("\nDIGITE O CPF DO CLIENTE: ");
                        cpf = read.readLine();
                        try {
                            cliente = fachada.procurarCliente(cpf);
                            fachada.removerCliente(cliente);
                            System.out.println("\nCLIENTE REMOVIDO!\n");
                        } catch (ClienteInexistenteException ex) {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("");
                        contas.displayAll();
                        break;
                    case 9:
                        System.out.println("\nVOLTAR PARA MENU PRINCIPAL");
                        break;
                    case 0:
                        System.out.println("\nVOCE SAIU DO SISTEMA");
                        break;
                    default:
                        System.out.println("OPCAO INVALIDA, TENTE NOVAMENTE!");
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } while (option != 0);
    }

    public static void menuContas() {
        System.out.println("------------------------\n\tMENU CONTAS\n------------------------\n");
    }

    public static void main(String[] args) {
        Fachada fachada = Fachada.obterInstancia();
//
//        CadastroEmArquivo<ContaParaSalvar> cadastroEmArquivoContas = new CadastroEmArquivo();
//        String nomeArquivoContas = "contas.dat";
//        File arquivoContas = new File(nomeArquivoContas);
//
//        CadastroEmArquivo<Cliente> cadastroEmArquivoClientes = new CadastroEmArquivo();
//        String nomeArquivoClientes = "clientes.dat";
//        File arquivoClientes = new File(nomeArquivoClientes);
//
//        int option;
//        String cpf, nome, telefone, email, numero;
//        double saldo;
//        TipoConta tipo;
//        ContaAbstrata conta;
//        ContaParaSalvar contaparasalvar;
//        Cliente cliente;
//
//        if (arquivoClientes.exists()) {
//            cadastroEmArquivoClientes.opentoRead(nomeArquivoClientes);
//            try {
//                do {
//                    cliente = cadastroEmArquivoClientes.readObjectFromFile();
//                    if (cliente != null) {
//                        fachada.cadastrarCliente(cliente);
//                    }
//                } while (cliente != null);
//                cadastroEmArquivoContas.closeAfterRead();
//            } catch (ClienteExistenteException ex) {
//                System.err.println(ex.getMessage());
//            }
//        }
//
//        if (arquivoContas.exists()) {
//            cadastroEmArquivoContas.opentoRead(nomeArquivoContas);
//            try {
//                do {
//                    contaparasalvar = cadastroEmArquivoContas.readObjectFromFile();
//                    if (contaparasalvar != null) {
//                        cliente = fachada.procurarCliente(contaparasalvar.getCpfCliente());
//                        tipo = contaparasalvar.getTipo();
//                        conta = tipo.instanciar(contaparasalvar.getNumero(), contaparasalvar.getSaldo(), cliente);
//                        fachada.cadastrarConta(conta);
//                    }
//                } while (contaparasalvar != null);
//                cadastroEmArquivoContas.closeAfterRead();
//            } catch (ContaExistenteException | ClienteInexistenteException | ClienteInvalidoException ex) {
//                System.err.println(ex.getMessage());
//            }
//        }

        menuPrincipal();

//        do {
//            System.out.println("--------------------\n\tMENU\n--------------------\n");
//            System.out.println("1. CADASTRAR NOVO FUNCIONÁRIO\n2. PROCURAR FUNCIONÁRIO\n"
//                    + "3. ATUALIZAR FUNCIONÁRIO\n4. REMOVER FUNCIONÁRIO\n"
//                    + "5. EXIBIR TODOS OS FUNCIONÁRIOS\n0. SAIR DO SISTEMA");
//            System.out.print("\nDIGITE AQUI SUA OPCAO: ");
//            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
//            option = Integer.parseInt(read.readLine());
//
//            switch (option) {
//                case 1:
//                    System.out.print("\nDIGITE A MATRÍCULA DO FUNCIONÁRIO: ");
//                    matricula = read.readLine();
//                    conta = contas.search(matricula);
//                    if (conta != null) {
//                        System.out.println("\nFUNCIONÁRIO JÁ CADASTRADO\n\n");
//                        break;
//                    }
//                    System.out.print("DIGITE O NOME DO FUNCIONÁRIO: ");
//                    nome = read.readLine();
//                    System.out.print("DIGITE O ENDEREÇO DO FUNCIONÁRIO: ");
//                    endereco = read.readLine();
//                    System.out.print("DIGITE O TELEFONE DO FUNCIONÁRIO: ");
//                    telefone = read.readLine();
//                    System.out.print("DIGITE O EMAIL DO FUNCIONÁRIO: ");
//                    email = read.readLine();
//                    System.out.print("DIGITE A FUNÇÃO DO FUNCIONÁRIO: ");
//                    funcao = read.readLine();
//                    System.out.print("DIGITE O SALÁRIO DO FUNCIONÁRIO: ");
//                    salario = Double.parseDouble(read.readLine());
//                    try {
//                        contas.insert(new Funcionario(matricula, nome, new Contato(endereco, telefone, email), funcao, salario));
//                        System.out.println("\nFUNCIONÁRIO CADASTRADO!\n");
//                    } catch (Exception exception) {
//                        System.out.println("\n" + exception.getMessage() + "\n");
//                    }
//                    break;
//                case 2:
//                    System.out.print("\nDIGITE A MATRÍCULA DO FUNCIONÁRIO: ");
//                    matricula = read.readLine();
//                    try {
//                        conta = contas.search(matricula);
//                        if (conta != null) {
//                            System.out.println("\n" + conta);
//                        } else {
//                            System.out.println("\nFUNCIONÁRIO NÃO EXISTE!\n");
//                        }
//                    } catch (Exception exception) {
//                        System.out.println("\n" + exception.getMessage() + "\n");
//                    }
//                    break;
//                case 3:
//                    System.out.print("\nDIGITE A MATRÍCULA DO FUNCIONÁRIO: ");
//                    matricula = read.readLine();
//                    conta = contas.search(matricula);
//                    if (conta == null) {
//                        System.out.println("\nFUNCIONÁRIO NÃO EXISTE!\n");
//                        break;
//                    } else {
//                        System.out.println(conta);
//                    }
//                    System.out.print("\nALTERE O NOME DO FUNCIONÁRIO: ");
//                    nome = read.readLine();
//                    System.out.print("ALTERE O ENDEREÇO DO FUNCIONÁRIO: ");
//                    endereco = read.readLine();
//                    System.out.print("ALTERE O TELEFONE DO FUNCIONÁRIO: ");
//                    telefone = read.readLine();
//                    System.out.print("ALTERE O EMAIL DO FUNCIONÁRIO: ");
//                    email = read.readLine();
//                    System.out.print("ALTERE A FUNÇÃO DO FUNCIONÁRIO: ");
//                    funcao = read.readLine();
//                    System.out.print("ALTERE O SALÁRIO DO FUNCIONÁRIO: ");
//                    salario = Double.parseDouble(read.readLine());
//                    try {
//                        contas.update(new Funcionario(matricula, nome, new Contato(endereco, telefone, email), funcao, salario));
//                        System.out.println("\nFUNCIONÁRIO ATUALIZADO!\n");
//                    } catch (Exception exception) {
//                        System.out.println("\n" + exception.getMessage() + "\n");
//                    }
//                    break;
//                case 4:
//                    System.out.print("\nDIGITE A MATRÍCULA DO FUNCIONÁRIO: ");
//                    matricula = read.readLine();
//                    try {
//                        conta = contas.search(matricula);
//                        if (conta != null) {
//                            contas.remove(matricula);
//                            System.out.println("\nFUNCIONÁRIO REMOVIDO!\n");
//                        } else {
//                            System.out.println("\nFUNCIONÁRIO NÃO EXISTE!\n");
//                        }
//                    } catch (Exception e) {
//                        System.out.println("\n" + e.getMessage() + "\n");
//                    }
//                    break;
//                case 5:
//                    System.out.println("");
//                    contas.displayAll();
//                    break;
//                case 0:
//                    System.out.println("\nVOCE SAIU DO SISTEMA");
//                    break;
//                default:
//                    System.out.println("OPCAO INVALIDA, TENTE NOVAMENTE!");
//            }
//
//        } while (option != 0);
//        cadastroEmArquivoContas.openToWrite(nomeArquivoContas);
//        try {
//            do {
//                conta = fachada.removerContaRaiz();
//                if (conta != null) {
//                    contaparasalvar = new ContaParaSalvar(conta.getNumero(), conta.getSaldo(), conta.getCliente().getCpf(), conta.getTipo());
//                    cadastroEmArquivoContas.writeObjectOnFile(contaparasalvar);
//                }
//            } while (conta != null);
//        } catch (ContaInexistenteException ex) {
//            System.err.println(ex.getMessage());
//        }
//        cadastroEmArquivoContas.closeAfterWrite();
//
//        cadastroEmArquivoContas.openToWrite(nomeArquivoClientes);
//        try {
//            do {
//                cliente = fachada.removerClienteRaiz();
//                if (cliente != null) {
//                    cadastroEmArquivoClientes.writeObjectOnFile(cliente);
//                }
//            } while (cliente != null);
//        } catch (ClienteInexistenteException ex) {
//            System.err.println(ex.getMessage());
//        }
//        cadastroEmArquivoContas.closeAfterWrite();
    }
}
