package view;

import main2.Banco3;
import main2.Cliente;
import main2.Contas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenuCliente extends JFrame {
    private JButton btnSacar, btnDepositar, btnSaldo, btnTransferir, btnExtrato, btnVoltar;
    private TelaLoginCliente telaAnterior;
    private Banco3 banco;
    private Cliente cliente;
    
    public TelaMenuCliente(TelaLoginCliente anterior, Banco3 banco, Cliente cliente) {
        this.telaAnterior = anterior;
        this.banco = banco;
        this.cliente = cliente;
        
        setTitle("Menu Cliente - " + cliente.getConta().getNome());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        // Criar componentes
        JLabel lblTitulo = new JLabel("Menu do Cliente", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        
        btnSacar = new JButton("Sacar");
        btnDepositar = new JButton("Depositar");
        btnSaldo = new JButton("Ver Saldo");
        btnTransferir = new JButton("Transferir");
        btnExtrato = new JButton("Ver Extrato");
        btnVoltar = new JButton("Voltar");
        
        // Layout
        setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel();
        panelTop.add(lblTitulo);
        
        JPanel panelCenter = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panelCenter.add(btnSacar);
        panelCenter.add(btnDepositar);
        panelCenter.add(btnSaldo);
        panelCenter.add(btnTransferir);
        panelCenter.add(btnExtrato);
        panelCenter.add(btnVoltar);
        
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        
        // Eventos
        btnSacar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sacar();
            }
        });
        
        btnDepositar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                depositar();
            }
        });
        
        btnSaldo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verSaldo();
            }
        });
        
        btnTransferir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transferir();
            }
        });
        
        btnExtrato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verExtrato();
            }
        });
        
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                voltar();
            }
        });
    }
    
    private void sacar() {
        String valorStr = JOptionPane.showInputDialog(this, "Valor do saque R$:");
        if (valorStr != null) {
            try {
                double valor = Double.parseDouble(valorStr);
                cliente.getConta().Sacar(valor);
                JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void depositar() {
        String valorStr = JOptionPane.showInputDialog(this, "Valor do depósito R$:");
        if (valorStr != null) {
            try {
                double valor = Double.parseDouble(valorStr);
                cliente.getConta().Depositar(valor);
                JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void verSaldo() {
        JOptionPane.showMessageDialog(this, 
            "Saldo atual: R$ " + String.format("%.2f", cliente.getConta().getSaldo()),
            "Saldo", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void transferir() {
        String idDestino = JOptionPane.showInputDialog(this, "ID da conta destino:");
        if (idDestino != null && !idDestino.isEmpty()) {
            Contas contaDestino = banco.buscarUsuario(idDestino);
            if (contaDestino != null) {
                String valorStr = JOptionPane.showInputDialog(this, "Valor da transferência R$:");
                if (valorStr != null) {
                    try {
                        double valor = Double.parseDouble(valorStr);
                        boolean sucesso = cliente.getConta().Transferencia(valor, contaDestino);
                        if (sucesso) {
                            JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Transferência falhou!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Conta destino não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void verExtrato() {
        // Aqui você pode criar uma tela específica para extrato
        StringBuilder extrato = new StringBuilder();
        extrato.append("=== EXTRATO ===\n");
        extrato.append("Conta: ").append(cliente.getId()).append("\n");
        extrato.append("Nome: ").append(cliente.getConta().getNome()).append("\n");
        extrato.append("Saldo: R$ ").append(String.format("%.2f", cliente.getConta().getSaldo())).append("\n");
        
        JOptionPane.showMessageDialog(this, extrato.toString(), "Extrato", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void voltar() {
        this.dispose();
        telaAnterior.setVisible(true);
    }
}