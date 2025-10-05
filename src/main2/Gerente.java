package main2;

import java.util.regex.Pattern;

public class Gerente extends Funcionario {
    private String senha;
    private Contas conta;
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$");

    public Gerente(String nome, String cpf, String telefone, String matricula, double salario, String senha) {
        super(nome, cpf, telefone, matricula, salario);
        this.senha = senha;
        this.conta = new Contas(nome, 0, senha);
    }

    public String getSenha() { return senha; }
    public Contas getConta() { return conta; }
    public String getId() { return conta.getId(); }

    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;
        return CPF_PATTERN.matcher(cpf).matches();
    }
    
    public static boolean validarTelefone(String telefone) {
        if (telefone == null) return false;
        return TELEFONE_PATTERN.matcher(telefone).matches();
    }

    @Override
    public void exibirInfo() {
        System.out.println("=== GERENTE ===");
        System.out.println("Nome: " + getNome());        
        System.out.println("CPF: " + getCpf());          
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Matrícula: " + getMatricula()); 
        System.out.println("Salário: R$ " + getSalario());  
        System.out.println("ID da Conta: " + conta.getId());
    }

    @Override
    public void realizarAtividade() {
        System.out.println("O gerente está aprovando contas e gerenciando clientes...");
    }
}