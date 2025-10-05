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
    private List<Transacao> historico;

    public Contas(String nome, double saldo, String senha) {
        this.nome = nome;
        this.saldo = saldo;
        this.senha = senha;
        this.ID = gerarIdUnico();
        this.historico = new ArrayList<>();
        
        // Registrar depósito inicial se houver
        if (saldo > 0) {
            registrarTransacao("DEPOSITO_INICIAL", saldo, null);
        }
    }

    public String getNome() { return nome; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getId() { return ID; }
    public List<Transacao> getHistorico() { return historico; }

    private String gerarIdUnico() {
        Random random = new Random();
        String novoId;
        do {
            novoId = String.format("%06d", random.nextInt(999999));
        } while (idsExistentes.contains(novoId));
        idsExistentes.add(novoId);
        return novoId;
    }

    public void registrarTransacao(String tipo, double valor, String destino) {
        historico.add(new Transacao(tipo, valor, this.ID, destino));
    }

    public String exibirExtratoReal() {
        StringBuilder extrato = new StringBuilder();
        extrato.append("=== EXTRATO DA CONTA ").append(ID).append(" ===\n");
        extrato.append("Nome: ").append(nome).append("\n");
        extrato.append("Saldo Atual: R$ ").append(String.format("%.2f", saldo)).append("\n\n");
        
        if (historico.isEmpty()) {
            extrato.append("Nenhuma movimentação registrada.\n");
        } else {
            extrato.append("📊 HISTÓRICO DE TRANSAÇÕES:\n");
            for (Transacao t : historico) {
                extrato.append("• ").append(t.toFormattedString()).append("\n");
            }
        }
        
        extrato.append("\n").append("=".repeat(50));
        return extrato.toString();
    }

    public void Depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor do depósito deve ser positivo");
            return;
        }
        this.saldo += valor;
        registrarTransacao("DEPOSITO", valor, null);
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
        registrarTransacao("SAQUE", valor, null);
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
        registrarTransacao("TRANSFERENCIA_ENVIADA", valor, contaDestino.getId());
        contaDestino.registrarTransacao("TRANSFERENCIA_RECEBIDA", valor, this.getId());
        System.out.println("Transferência realizada com sucesso!");
        return true;
    }

    @Override
    public String toString() {
        return String.format("Conta [ID=%s, Nome=%s, Saldo=R$%.2f]", ID, nome, saldo);
    }
}