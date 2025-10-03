package main2;

import java.util.regex.Pattern;

public class Gerente extends Funcionario {
    private Contas conta;
    
    // Regex para validações
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$");

    public Gerente(String nome, String cpf, String telefone, String matricula, double salario, String senha) {
        super(nome, cpf, telefone, matricula, salario);
        this.conta = new Contas(nome, 0, senha);
    }

    public String getSenha() { return conta.getSenha(); }
    public Contas getConta() { return conta; }
    public String getId() { return conta.getId(); }

    /**
     * Valida CPF no formato: 000.000.000-00
     */
    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;
        return CPF_PATTERN.matcher(cpf).matches();
    }
    
    /**
     * Valida telefone no formato: (00) 00000-0000
     */
    public static boolean validarTelefone(String telefone) {
        if (telefone == null) return false;
        return TELEFONE_PATTERN.matcher(telefone).matches();
    }

    @Override
    public void exibirInfo() {
        System.out.println("=== GERENTE ===");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário: R$ " + salario);
        System.out.println("ID da Conta: " + conta.getId());
    }

    @Override
    public void realizarAtividade() {
        System.out.println("O gerente está aprovando contas e gerenciando clientes...");
    }

    public void listarClientes(Banco3 banco) {
        System.out.println("=== LISTA DE CLIENTES (via gerente) ===");
        banco.listarContas();
    }
}