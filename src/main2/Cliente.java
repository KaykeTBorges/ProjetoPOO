package main2;

import java.util.regex.Pattern;

public class Cliente extends Pessoa {
    private Contas conta;
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$");

    public Cliente(String nome, String cpf, String telefone, double depositoInicial, String senha) {
        super(nome, cpf, telefone);
        this.conta = new Contas(nome, depositoInicial, senha);
    }

    public Contas getConta() { return conta; }
    public String getSenha() { return conta.getSenha(); }
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
        System.out.println("=== CLIENTE ===");
        System.out.println("Nome: " + getNome());        
        System.out.println("CPF: " + getCpf());          
        System.out.println("Telefone: " + getTelefone());
        System.out.println("Conta: " + conta.getId() + " | Saldo: R$ " + conta.getSaldo());
    }
}