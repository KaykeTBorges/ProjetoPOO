package view;

import main2.Banco3;
import main2.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLoginCliente extends JFrame {
    private JTextField txtID;
    private JPasswordField txtSenha;
    private JButton btnEntrar, btnVoltar;
    private TelaPrincipal telaPrincipal;
    private Banco3 banco;
    
    public TelaLoginCliente(TelaPrincipal principal, Banco3 banco) {
        this.telaPrincipal = principal;
        this.banco = banco;
        
        setTitle("Login - Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        
        // Criar componentes
        JLabel lblTitulo = new JLabel("Login Cliente", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        
        txtID = new JTextField(15);
        txtSenha = new JPasswordField(15);
        
        btnEntrar = new JButton("Entrar");
        btnVoltar = new JButton("Voltar");
        
        // Layout
        setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel();
        panelTop.add(lblTitulo);
        
        JPanel panelCenter = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panelCenter.add(new JLabel("ID:"));
        panelCenter.add(txtID);
        panelCenter.add(new JLabel("Senha:"));
        panelCenter.add(txtSenha);
        
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnEntrar);
        panelBotoes.add(btnVoltar);
        
        panelCenter.add(new JLabel(""));
        panelCenter.add(panelBotoes);
        
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        
        // Eventos
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
        
        Cliente cliente = banco.buscarCliente(id);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Abrir menu do cliente
            TelaMenuCliente telaMenu = new TelaMenuCliente(this, banco, cliente);
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