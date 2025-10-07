package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main2.Banco3;
import main2.Gerente;

public class TelaLoginGerente extends JFrame {
    private JTextField txtID;
    private JPasswordField txtSenha;
    private JButton btnEntrar, btnVoltar;
    private TelaPrincipal telaPrincipal;
    private Banco3 banco;
    
    public TelaLoginGerente(TelaPrincipal principal, Banco3 banco) {
        this.telaPrincipal = principal;
        this.banco = banco;
        
        setTitle("Login - Gerente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);
        setLocationRelativeTo(null);
        
        JLabel lblTitulo = new JLabel("Login Gerente", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        
        txtID = new JTextField(15);
        txtSenha = new JPasswordField(15);
        
        btnEntrar = new JButton("Entrar");
        btnVoltar = new JButton("Voltar");
        
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelTop = new JPanel();
        panelTop.add(lblTitulo);
        
        JPanel panelCenter = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        panelCenter.add(new JLabel("ID:"));
        panelCenter.add(txtID);
        panelCenter.add(new JLabel("Senha:"));
        panelCenter.add(txtSenha);
        panelCenter.add(new JLabel(""));
        panelCenter.add(new JLabel(""));
        
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotoes.add(btnEntrar);
        panelBotoes.add(btnVoltar);
        
        panelCenter.add(new JLabel(""));
        panelCenter.add(panelBotoes);
        
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fazerLogin();
            }
        });
        
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                voltar();
            }
        });
    }
    
    private void fazerLogin() {
        String id = txtID.getText();
        String senha = new String(txtSenha.getPassword());
        
        if (id.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha ID e senha!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Gerente gerente = banco.buscarGerente(id);
        if (gerente != null && gerente.getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(this, "Login de gerente realizado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Abrir menu do gerente
            TelaMenuGerente telaMenu = new TelaMenuGerente(this, banco, gerente);
            telaMenu.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "ID ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void voltar() {
        this.dispose();
        telaPrincipal.voltarParaPrincipal();
    }
}