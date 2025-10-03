package main2;

import java.util.Scanner;

public class App {

    static Scanner entrada = new Scanner(System.in);
    static Banco3 banco = new Banco3();

    public static void main(String[] args) {
        int abertura = 1;

        while (abertura != 0) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("           SISTEMA BANCÁRIO");
            System.out.println("=".repeat(50));
            System.out.println("1 - Criar conta cliente");
            System.out.println("2 - Acessar conta cliente");
            System.out.println("3 - Entrar como Gerente");
            System.out.println("4 - Cadastrar Gerente");
            System.out.println("0 - Sair");
            System.out.println("=".repeat(50));

            int opcao = 0;
            try {
                System.out.print("Escolha uma opção: ");
                opcao = entrada.nextInt();
                entrada.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Opção inválida!");
                entrada.nextLine();
                continue;
            }

            switch (opcao) {
                case 1 -> criarContaCliente();
                case 2 -> operarContaCliente();
                case 3 -> acessarGerente();
                case 4 -> cadastrarGerente();
                case 0 -> {
                    System.out.println("👋 Sistema encerrado. Até logo!");
                    abertura = 0;
                }
                default -> System.out.println("❌ Opção inválida!");
            }
        }
        entrada.close();
    }

    // =========================================================
    // Cliente
    // =========================================================
    private static void criarContaCliente() {
        System.out.println("\n--- CRIAR NOVA CONTA CLIENTE ---");
        
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        
        // Validação de CPF
        String cpf;
        while (true) {
            System.out.print("CPF (formato: 000.000.000-00): ");
            cpf = entrada.nextLine();
            if (Cliente.validarCPF(cpf)) {
                break;
            } else {
                System.out.println("❌ CPF inválido! Use o formato: 000.000.000-00");
            }
        }
        
        // Validação de Telefone
        String telefone;
        while (true) {
            System.out.print("Telefone (formato: (00) 00000-0000): ");
            telefone = entrada.nextLine();
            if (Cliente.validarTelefone(telefone)) {
                break;
            } else {
                System.out.println("❌ Telefone inválido! Use o formato: (00) 00000-0000");
            }
        }

        double depositoInicial = 0;
        try {
            System.out.print("Depósito inicial: R$ ");
            depositoInicial = entrada.nextDouble();
            entrada.nextLine();
            if (depositoInicial < 0) {
                System.out.println("❌ Depósito negativo não permitido. Definido como R$ 0,00.");
                depositoInicial = 0;
            }
        } catch (Exception e) {
            System.out.println("⚠ Valor inválido. Depósito definido como R$ 0,00.");
            entrada.nextLine();
        }

        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        Cliente cliente = new Cliente(nome, cpf, telefone, depositoInicial, senha);
        banco.cadastrarConta(cliente);

        System.out.println("\n✅ Conta criada com sucesso!");
        System.out.println("📋 ID: " + cliente.getId());
    }

    private static void operarContaCliente() {
        System.out.println("\n--- LOGIN CLIENTE ---");
        System.out.print("ID: ");
        String id = entrada.nextLine();

        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        Cliente cliente = banco.buscarCliente(id);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            System.out.println("✅ Login realizado com sucesso!");
            menuOperacoesCliente(cliente);
        } else {
            System.out.println("❌ Conta não encontrada ou senha incorreta.");
        }
    }

    private static void menuOperacoesCliente(Cliente cliente) {
        boolean acesso = true;
        while (acesso) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("       MENU CLIENTE");
            System.out.println("=".repeat(40));
            System.out.println("1 - Sacar");
            System.out.println("2 - Depositar");
            System.out.println("3 - Ver Saldo");
            System.out.println("4 - Transferência");
            System.out.println("5 - Ver Extrato");
            System.out.println("6 - Meus Dados");
            System.out.println("7 - Voltar");
            System.out.println("=".repeat(40));

            int escolha = 7;
            try {
                System.out.print("Escolha uma opção: ");
                escolha = entrada.nextInt();
                entrada.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Opção inválida!");
                entrada.nextLine();
                continue;
            }

            switch (escolha) {
                case 1 -> {
                    try {
                        System.out.print("Valor do saque: R$ ");
                        double valor = entrada.nextDouble();
                        entrada.nextLine();
                        cliente.getConta().Sacar(valor);
                    } catch (Exception e) {
                        System.out.println("⚠ Valor inválido!");
                        entrada.nextLine();
                    }
                }
                case 2 -> {
                    try {
                        System.out.print("Valor do depósito: R$ ");
                        double valor = entrada.nextDouble();
                        entrada.nextLine();
                        cliente.getConta().Depositar(valor);
                    } catch (Exception e) {
                        System.out.println("⚠ Valor inválido!");
                        entrada.nextLine();
                    }
                }
                case 3 -> cliente.getConta().Status();
                case 4 -> {
                    System.out.print("ID da conta destino: ");
                    String idDestino = entrada.nextLine();
                    Contas contaDestino = banco.buscarUsuario(idDestino);
                    if (contaDestino != null) {
                        try {
                            System.out.print("Valor da transferência: R$ ");
                            double valor = entrada.nextDouble();
                            entrada.nextLine();
                            boolean sucesso = cliente.getConta().Transferencia(valor, contaDestino);
                            if (!sucesso) {
                                System.out.println("❌ Transferência falhou!");
                            }
                        } catch (Exception e) {
                            System.out.println("⚠ Valor inválido!");
                            entrada.nextLine();
                        }
                    } else {
                        System.out.println("❌ Conta destino não encontrada.");
                    }
                }
                case 5 -> cliente.getConta().exibirExtrato();
                case 6 -> cliente.exibirInfo();
                case 7 -> {
                    acesso = false;
                    System.out.println("👋 Retornando ao menu principal...");
                }
                default -> System.out.println("❌ Opção inválida.");
            }
        }
    }

    // =========================================================
    // Cadastro de Gerente
    // =========================================================
    private static void cadastrarGerente() {
        System.out.println("\n--- CADASTRAR GERENTE ---");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        
        // Validação de CPF para gerente
        String cpf;
        while (true) {
            System.out.print("CPF (formato: 000.000.000-00): ");
            cpf = entrada.nextLine();
            if (Gerente.validarCPF(cpf)) {
                break;
            } else {
                System.out.println("❌ CPF inválido! Use o formato: 000.000.000-00");
            }
        }
        
        // Validação de Telefone para gerente
        String telefone;
        while (true) {
            System.out.print("Telefone (formato: (00) 00000-0000): ");
            telefone = entrada.nextLine();
            if (Gerente.validarTelefone(telefone)) {
                break;
            } else {
                System.out.println("❌ Telefone inválido! Use o formato: (00) 00000-0000");
            }
        }
        
        System.out.print("Matrícula: ");
        String matricula = entrada.nextLine();
        
        double salario = 0;
        try {
            System.out.print("Salário: R$ ");
            salario = entrada.nextDouble();
            entrada.nextLine();
        } catch (Exception e) {
            System.out.println("⚠ Valor inválido. Salário definido como R$ 0,00.");
            entrada.nextLine();
        }
        
        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        Gerente gerente = new Gerente(nome, cpf, telefone, matricula, salario, senha);
        banco.cadastrarConta(gerente);

        System.out.println("\n✅ Gerente cadastrado com sucesso!");
        System.out.println("📋 ID: " + gerente.getId());
    }

    // =========================================================
    // Acesso Gerente
    // =========================================================
    private static void acessarGerente() {
        System.out.println("\n--- LOGIN GERENTE ---");
        System.out.print("ID: ");
        String id = entrada.nextLine();

        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        Gerente gerente = banco.buscarGerente(id);

        if (gerente != null && gerente.getSenha().equals(senha)) {
            System.out.println("✅ Login de gerente realizado!");
            menuGerente(gerente);
        } else {
            System.out.println("❌ Conta não encontrada ou não é gerente.");
        }
    }

    private static void menuGerente(Gerente gerente) {
        boolean acesso = true;
        while (acesso) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("        MENU GERENTE");
            System.out.println("=".repeat(40));
            System.out.println("1 - Listar todas as contas");
            System.out.println("2 - Remover conta");
            System.out.println("3 - Verificar conta");
            System.out.println("4 - Ver Extrato da Conta");
            System.out.println("5 - Listar Gerentes");
            System.out.println("6 - Listar Clientes");
            System.out.println("7 - Meus Dados");
            System.out.println("8 - Voltar");
            System.out.println("=".repeat(40));

            int escolha = 8;
            try {
                System.out.print("Escolha uma opção: ");
                escolha = entrada.nextInt();
                entrada.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Opção inválida!");
                entrada.nextLine();
                continue;
            }

            switch (escolha) {
                case 1 -> banco.listarContas();
                case 2 -> removerConta();
                case 3 -> verificarConta();
                case 4 -> verExtratoConta();
                case 5 -> banco.listarGerentes();
                case 6 -> banco.listarClientes();
                case 7 -> gerente.exibirInfo();
                case 8 -> {
                    acesso = false;
                    System.out.println("👋 Retornando ao menu principal...");
                }
                default -> System.out.println("❌ Opção inválida.");
            }
        }
    }

    // =========================================================
    // Funções do gerente
    // =========================================================
    private static void removerConta() {
        System.out.print("ID da conta a remover: ");
        String id = entrada.nextLine();
        System.out.print("Senha da conta: ");
        String senha = entrada.nextLine();

        if (banco.removerConta(id, senha)) {
            System.out.println("✅ Conta removida com sucesso!");
        } else {
            System.out.println("❌ Falha ao remover conta.");
        }
    }

    private static void verificarConta() {
        System.out.print("ID da conta: ");
        String id = entrada.nextLine();
        if (banco.contaExiste(id)) {
            System.out.println("✅ Conta encontrada!");
        } else {
            System.out.println("❌ Conta não encontrada.");
        }
    }

    private static void verExtratoConta() {
        System.out.print("ID da conta para ver extrato: ");
        String id = entrada.nextLine();
        Contas conta = banco.buscarUsuario(id);
        if (conta != null) {
            conta.exibirExtrato();
        } else {
            System.out.println("❌ Conta não encontrada.");
        }
    }
}