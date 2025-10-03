package main2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Contas {
    private static Set<String> idsExistentes = new HashSet<>();
    private String nome;
    private double saldo;
    private String ID;
    private String senha;
    private List<Transacao> historico; // 🔹 Histórico de transações

    public Contas(String nome, double saldo, String senha) {
        this.nome = nome;
        this.saldo = saldo;
        this.senha = senha;
        this.ID = gerarIdUnico();
        this.historico = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome.toLowerCase(); }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getId() { return ID; }

    // Histórico
    public void registrarTransacao(String tipo, double valor, String destino) {
        historico.add(new Transacao(tipo, valor, this.ID, destino));
    }

    public void exibirExtrato() {
        System.out.println("\n=== EXTRATO DA CONTA " + ID + " ===");
        if (historico.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            for (Transacao t : historico) {
                System.out.println(t);
            }
        }
        System.out.println("===============================");
    }

    // Geração de ID único
    private String gerarIdUnico() {
        Random random = new Random();
        String novoId;
        do {
            novoId = String.format("%06d", random.nextInt(999999));
        } while (idsExistentes.contains(novoId));
        idsExistentes.add(novoId);
        return novoId;
    }

    // Operações bancárias
    public void Depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor do depósito deve ser positivo");
            return;
        }
        this.saldo += valor;
        registrarTransacao("Depósito", valor, null); // 🔹 adiciona no histórico
        System.out.println("Depósito realizado com sucesso!");
    }

    public boolean Sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor do saque deve ser positivo");
            return false;
        }
        if (valor > saldo) {
            System.out.println("Saldo insuficiente");
            return false;
        }
        saldo -= valor;
        registrarTransacao("Saque", valor, null); // 🔹 adiciona no histórico
        System.out.println("Saque realizado com sucesso!");
        return true;
    }

    public void Status() {
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }

    public boolean Transferencia(double valor, Contas contaDestino) {
        if (valor <= 0) {
            System.out.println("Valor da transferência deve ser positivo");
            return false;
        }
        if (valor > saldo) {
            System.out.println("Saldo insuficiente");
            return false;
        }
        if (contaDestino == null) {
            System.out.println("Conta destino não encontrada");
            return false;
        }
        if (contaDestino == this) {
            System.out.println("Não é possível transferir para a mesma conta");
            return false;
        }

        saldo -= valor;
        contaDestino.saldo += valor;
        registrarTransacao("Transferência enviada", valor, contaDestino.getId());
        contaDestino.registrarTransacao("Transferência recebida", valor, this.getId());
        System.out.println("Transferência realizada com sucesso!");
        return true;
    }

    @Override
    public String toString() {
        return String.format("Conta [ID=%s, Nome=%s, Saldo=R$%.2f]", ID, nome, saldo);
    }
}
