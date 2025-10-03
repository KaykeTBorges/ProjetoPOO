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

    // Método para buscar gerente pelo ID
    public Gerente buscarGerente(String id) {
        for (Gerente gerente : gerentes) {
            if (gerente.getConta().getId().equals(id)) {
                return gerente;
            }
        }
        return null;
    }

    // Método para buscar cliente pelo ID
    public Cliente buscarCliente(String id) {
        for (Cliente cliente : clientes) {
            if (cliente.getConta().getId().equals(id)) {
                return cliente;
            }
        }
        return null;
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

    // Método para listar apenas gerentes
    public void listarGerentes() {
        System.out.println("=== GERENTES CADASTRADOS ===");
        for (Gerente gerente : gerentes) {
            gerente.exibirInfo();
            System.out.println("--------------------");
        }
    }
}