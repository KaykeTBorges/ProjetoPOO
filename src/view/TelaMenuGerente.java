package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main2.Banco3;
import main2.Contas;
import main2.Gerente;

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
        
        JLabel lblTitulo = new JLabel("Menu do Gerente", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        
        btnListarContas = new JButton("Listar Contas");
        btnRemoverConta = new JButton("Remover Conta");
        btnVerificarConta = new JButton("Verificar Conta");
        btnExtrato = new JButton("Ver Extrato");
        btnListarClientes = new JButton("Listar Clientes");
        btnVoltar = new JButton("Voltar");
        
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
                verExtratoReal();
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
        try {
            String listaContas = banco.listarTodasContas();
            mostrarTextoGrande("Lista de Contas", listaContas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar contas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void listarClientes() {
        try {
            String listaClientes = banco.listarTodosClientes();
            mostrarTextoGrande("Lista de Clientes", listaClientes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removerConta() {
        String id = JOptionPane.showInputDialog(this, "ID da conta a remover:");
        if (id != null && !id.isEmpty()) {
            String senha = JOptionPane.showInputDialog(this, "Senha da conta:");
            if (senha != null && !senha.isEmpty()) {
                boolean sucesso = banco.removerConta(id, senha);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "✅ Conta removida com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Falha ao remover conta!\nVerifique ID e senha.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void verificarConta() {
        String id = JOptionPane.showInputDialog(this, "ID da conta:");
        if (id != null && !id.isEmpty()) {
            boolean existe = banco.contaExiste(id);
            if (existe) {
                Contas conta = banco.buscarUsuario(id);
                String info = "✅ Conta encontrada!\n\n" +
                             "ID: " + conta.getId() + "\n" +
                             "Nome: " + conta.getNome() + "\n" +
                             "Saldo: R$ " + String.format("%.2f", conta.getSaldo()) + "\n" +
                             "Tipo: " + (banco.buscarCliente(id) != null ? "CLIENTE" : "GERENTE");
                JOptionPane.showMessageDialog(this, info, "Conta Encontrada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void verExtratoReal() {
        String id = JOptionPane.showInputDialog(this, "ID da conta para ver extrato:");
        if (id != null && !id.isEmpty()) {
            Contas conta = banco.buscarUsuario(id);
            if (conta != null) {
                String extratoCompleto = conta.exibirExtratoReal();
                mostrarTextoGrande("Extrato - " + conta.getId(), extratoCompleto);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void mostrarTextoGrande(String titulo, String texto) {
        JTextArea textArea = new JTextArea(texto);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void voltar() {
        this.dispose();
        telaAnterior.setVisible(true);
    }
}