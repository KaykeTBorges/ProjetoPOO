package main2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Banco3 {
    private HashMap<String, Contas> contasDePessoas;
    private List<Cliente> clientes;
    private List<Gerente> gerentes;

    public Banco3() {
        this.contasDePessoas = new HashMap<>();
        this.clientes = new ArrayList<>();
        this.gerentes = new ArrayList<>();
    }

    public void cadastrarConta(Cliente cliente) {
        Contas conta = cliente.getConta();
        this.contasDePessoas.put(conta.getId(), conta);
        this.clientes.add(cliente);
    }

    public void cadastrarConta(Gerente gerente) {
        Contas conta = gerente.getConta();
        this.contasDePessoas.put(conta.getId(), conta);
        this.gerentes.add(gerente);
    }

    public void listarClientes() {
        System.out.println("=== CLIENTES CADASTRADOS ===");
        for (Cliente c : clientes) {
            c.exibirInfo();
            System.out.println("--------------------");
        }
    }

    public Contas buscarUsuario(String idUsuario) {
        return contasDePessoas.get(idUsuario);
    }

    public boolean contaExiste(String id) {
        return contasDePessoas.containsKey(id);
    }

    public boolean removerConta(String id, String senha) {
        Contas conta = buscarUsuario(id);
        if (conta != null && conta.getSenha().equals(senha)) {
            contasDePessoas.remove(id);
            clientes.removeIf(c -> c.getConta().getId().equals(id));
            gerentes.removeIf(g -> g.getConta().getId().equals(id));
            return true;
        }
        return false;
    }

    public void listarContas() {
        System.out.println("=== CONTAS CADASTRADAS ===");
        for (String id : contasDePessoas.keySet()) {
            Contas conta = contasDePessoas.get(id);
            String tipo = isGerente(id) ? "GERENTE" : "CLIENTE";
            System.out.println("ID: " + id + " | Nome: " + conta.getNome() + " | Tipo: " + tipo + " | Saldo: R$ " + conta.getSaldo());
        }
        System.out.println("==========================");
    }

    // Método auxiliar para verificar se uma conta é de gerente
    private boolean isGerente(String id) {
        for (Gerente gerente : gerentes) {
            if (gerente.getConta().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void listarGerentes() {
        System.out.println("=== GERENTES CADASTRADOS ===");
        for (Gerente gerente : gerentes) {
            gerente.exibirInfo();
            System.out.println("--------------------");
        }
    }

    public Cliente buscarCliente(String id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    public Gerente buscarGerente(String id) {
        for (Gerente gerente : gerentes) {
            if (gerente.getId().equals(id)) {
                return gerente;
            }
        }
        return null;
    }

    public String listarTodasContas() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CONTAS CADASTRADAS ===\n\n");
        
        if (clientes.isEmpty() && gerentes.isEmpty()) {
            sb.append("Nenhuma conta cadastrada.\n");
            return sb.toString();
        }
        
        if (!clientes.isEmpty()) {
            sb.append("👤 CLIENTES:\n");
            for (Cliente cliente : clientes) {
                sb.append("• ID: ").append(cliente.getId())
                  .append(" | Nome: ").append(cliente.getConta().getNome())
                  .append(" | Saldo: R$ ").append(String.format("%.2f", cliente.getConta().getSaldo()))
                  .append("\n");
            }
            sb.append("\n");
        }
        
        if (!gerentes.isEmpty()) {
            sb.append("👔 GERENTES:\n");
            for (Gerente gerente : gerentes) {
                sb.append("• ID: ").append(gerente.getId())
                  .append(" | Nome: ").append(gerente.getConta().getNome())
                  .append(" | Saldo: R$ ").append(String.format("%.2f", gerente.getConta().getSaldo()))
                  .append("\n");
            }
        }
        
        sb.append("\n📊 TOTAL: ").append(clientes.size() + gerentes.size()).append(" contas");
        return sb.toString();
    }
    
    public String listarTodosClientes() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CLIENTES CADASTRADOS ===\n\n");
        
        if (clientes.isEmpty()) {
            sb.append("Nenhum cliente cadastrado.\n");
            return sb.toString();
        }
        
        for (Cliente cliente : clientes) {
            sb.append("ID: ").append(cliente.getId()).append("\n");
            sb.append("Nome: ").append(cliente.getConta().getNome()).append("\n");
            sb.append("CPF: ").append(cliente.getCpf()).append("\n");
            sb.append("Telefone: ").append(cliente.getTelefone()).append("\n");
            sb.append("Saldo: R$ ").append(String.format("%.2f", cliente.getConta().getSaldo())).append("\n");
            sb.append("--------------------\n");
        }
        
        sb.append("Total: ").append(clientes.size()).append(" clientes");
        return sb.toString();
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }
  
    public List<Gerente> getGerentes() {
        return new ArrayList<>(gerentes);
    }

    public boolean temContas() {
        return !clientes.isEmpty() || !gerentes.isEmpty();
    }

    public int getTotalContas() {
        return clientes.size() + gerentes.size();
    }

    public int getTotalClientes() {
        return clientes.size();
    }

    public int getTotalGerentes() {
        return gerentes.size();
    }
}