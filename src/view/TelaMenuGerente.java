package view;

import main2.Banco3;
import main2.Gerente;
import main2.Contas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenuGerente extends JFrame {
    private JButton btnListarContas, btnRemoverConta, btnVerificarConta, btnExtrato, btnListarClientes, btnVoltar;
    private TelaLoginGerente telaAnterior;
    private Banco3 banco;
    private Gerente gerente;
    
    public TelaMenuGerente(TelaLoginGerente anterior, Banco3 banco, Gerente gerente) {
        this.telaAnterior = anterior;
        this.banco = banco;
        this.gerente = gerente;
        
        setTitle("Menu Gerente - " + gerente.getConta().getNome());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        // Criar componentes
        JLabel lblTitulo = new JLabel("Menu do Gerente", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        
        btnListarContas = new JButton("Listar Contas");
        btnRemoverConta = new JButton("Remover Conta");
        btnVerificarConta = new JButton("Verificar Conta");
        btnExtrato = new JButton("Ver Extrato");
        btnListarClientes = new JButton("Listar Clientes");
        btnVoltar = new JButton("Voltar");
        
        // Layout
        setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel();
        panelTop.add(lblTitulo);
        
        JPanel panelCenter = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panelCenter.add(btnListarContas);
        panelCenter.add(btnRemoverConta);
        panelCenter.add(btnVerificarConta);
        panelCenter.add(btnExtrato);
        panelCenter.add(btnListarClientes);
        panelCenter.add(btnVoltar);
        
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        
        // Eventos
        btnListarContas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarContas();
            }
        });
        
        btnRemoverConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerConta();
            }
        });
        
        btnVerificarConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificarConta();
            }
        });
        
        btnExtrato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verExtrato();
            }
        });
        
        btnListarClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });
        
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                voltar();
            }
        });
    }
    
    private void listarContas() {
        StringBuilder lista = new StringBuilder();
        lista.append("=== CONTAS CADASTRADAS ===\n");
        
        // Aqui você implementaria a lógica para listar contas
        // Por enquanto, vou mostrar uma mensagem simples
        lista.append("Funcionalidade em desenvolvimento\n");
        lista.append("Aqui listaria todas as contas do banco");
        
        JOptionPane.showMessageDialog(this, lista.toString(), "Lista de Contas", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void removerConta() {
        String id = JOptionPane.showInputDialog(this, "ID da conta a remover:");
        if (id != null && !id.isEmpty()) {
            String senha = JOptionPane.showInputDialog(this, "Senha da conta:");
            if (senha != null) {
                boolean sucesso = banco.removerConta(id, senha);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Conta removida com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao remover conta!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void verificarConta() {
        String id = JOptionPane.showInputDialog(this, "ID da conta:");
        if (id != null && !id.isEmpty()) {
            boolean existe = banco.contaExiste(id);
            if (existe) {
                JOptionPane.showMessageDialog(this, "Conta encontrada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void verExtrato() {
        String id = JOptionPane.showInputDialog(this, "ID da conta para ver extrato:");
        if (id != null && !id.isEmpty()) {
            Contas conta = banco.buscarUsuario(id);
            if (conta != null) {
                StringBuilder extrato = new StringBuilder();
                extrato.append("=== EXTRATO ===\n");
                extrato.append("Conta: ").append(conta.getId()).append("\n");
                extrato.append("Nome: ").append(conta.getNome()).append("\n");
                extrato.append("Saldo: R$ ").append(String.format("%.2f", conta.getSaldo())).append("\n");
                
                JOptionPane.showMessageDialog(this, extrato.toString(), "Extrato", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void listarClientes() {
        StringBuilder lista = new StringBuilder();
        lista.append("=== CLIENTES CADASTRADOS ===\n");
        
        // Aqui você implementaria a lógica para listar clientes
        lista.append("Funcionalidade em desenvolvimento\n");
        lista.append("Aqui listaria todos os clientes do banco");
        
        JOptionPane.showMessageDialog(this, lista.toString(), "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void voltar() {
        this.dispose();
        telaAnterior.setVisible(true);
    }
}