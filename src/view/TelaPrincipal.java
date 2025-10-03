package view;

import main2.Banco3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {
    private JButton btnCadastrarCliente;
    private JButton btnLoginCliente;
    private JButton btnLoginGerente;
    private JButton btnCadastrarGerente;
    private Banco3 banco;
    
    public TelaPrincipal() {
        banco = new Banco3();
        
        setTitle("Sistema Bancário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        
        // Criar componentes
        JLabel lblTitulo = new JLabel("Sistema Bancário", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        
        btnCadastrarCliente = new JButton("Cadastrar Cliente");
        btnLoginCliente = new JButton("Entrar como Cliente");
        btnLoginGerente = new JButton("Entrar como Gerente");
        btnCadastrarGerente = new JButton("Cadastrar Gerente");
        
        // Layout
        setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel();
        panelTop.add(lblTitulo);
        
        JPanel panelCenter = new JPanel(new GridLayout(5, 1, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        panelCenter.add(new JLabel("Escolha uma opção:", JLabel.CENTER));
        panelCenter.add(btnCadastrarCliente);
        panelCenter.add(btnLoginCliente);
        panelCenter.add(btnLoginGerente);
        panelCenter.add(btnCadastrarGerente);
        
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        
        // Eventos
        btnCadastrarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastroCliente();
            }
        });
        
        btnLoginCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaLoginCliente();
            }
        });
        
        btnLoginGerente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaLoginGerente();
            }
        });
        
        btnCadastrarGerente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastroGerente();
            }
        });
    }
    
    private void abrirTelaCadastroCliente() {
        TelaCadastroCliente tela = new TelaCadastroCliente(this, banco);
        tela.setVisible(true);
        this.setVisible(false);
    }
    
    private void abrirTelaLoginCliente() {
        TelaLoginCliente tela = new TelaLoginCliente(this, banco);
        tela.setVisible(true);
        this.setVisible(false);
    }
    
    private void abrirTelaLoginGerente() {
        TelaLoginGerente tela = new TelaLoginGerente(this, banco);
        tela.setVisible(true);
        this.setVisible(false);
    }
    
    private void abrirTelaCadastroGerente() {
        TelaCadastroGerente tela = new TelaCadastroGerente(this, banco);
        tela.setVisible(true);
        this.setVisible(false);
    }
    
    public void voltarParaPrincipal() {
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
}