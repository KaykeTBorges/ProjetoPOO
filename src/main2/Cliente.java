package main2;

import java.util.regex.Pattern;

public class Cliente extends Pessoa {
    private Contas conta;
    
    // Regex para validações
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$");

    // Construtor completo
    public Cliente(String nome, String cpf, String telefone, double depositoInicial, String senha) {
        super(nome, cpf, telefone);
        this.conta = new Contas(nome, depositoInicial, senha);
    }

    public Contas getConta() { return conta; }
    public String getSenha() { return conta.getSenha(); }
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
        System.out.println("=== CLIENTE ===");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Conta: " + conta.getId() + " | Saldo: R$ " + conta.getSaldo());
    }
}