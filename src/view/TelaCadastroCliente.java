package view;

import main2.Banco3;
import main2.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroCliente extends JFrame {
    private JTextField txtNome, txtCPF, txtTelefone, txtDeposito;
    private JPasswordField txtSenha;
    private JButton btnCadastrar, btnVoltar;
    private TelaPrincipal telaPrincipal;
    private Banco3 banco;
    
    public TelaCadastroCliente(TelaPrincipal principal, Banco3 banco) {
        this.telaPrincipal = principal;
        this.banco = banco;
        
        setTitle("Cadastro de Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        
        JLabel lblTitulo = new JLabel("Cadastro de Cliente", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        
        txtNome = new JTextField(20);
        txtCPF = new JTextField(20);
        txtTelefone = new JTextField(20);
        txtDeposito = new JTextField(20);
        txtSenha = new JPasswordField(20);
        
        btnCadastrar = new JButton("Cadastrar");
        btnVoltar = new JButton("Voltar");
        
        setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel();
        panelTop.add(lblTitulo);
        
        JPanel panelCenter = new JPanel(new GridLayout(6, 2, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panelCenter.add(new JLabel("Nome:"));
        panelCenter.add(txtNome);
        panelCenter.add(new JLabel("CPF (000.000.000-00):"));
        panelCenter.add(txtCPF);
        panelCenter.add(new JLabel("Telefone ((00) 00000-0000):"));
        panelCenter.add(txtTelefone);
        panelCenter.add(new JLabel("Depósito Inicial R$:"));
        panelCenter.add(txtDeposito);
        panelCenter.add(new JLabel("Senha:"));
        panelCenter.add(txtSenha);
        
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnCadastrar);
        panelBotoes.add(btnVoltar);
        
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });
        
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                voltar();
            }
        });
    }
    
    private void cadastrarCliente() {
        try {
            String nome = txtNome.getText();
            String cpf = txtCPF.getText();
            String telefone = txtTelefone.getText();
            String senha = new String(txtSenha.getPassword());
            
            if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!Cliente.validarCPF(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF inválido! Use formato: 000.000.000-00", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!Cliente.validarTelefone(telefone)) {
                JOptionPane.showMessageDialog(this, "Telefone inválido! Use formato: (00) 00000-0000", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double deposito = 0;
            try {
                deposito = Double.parseDouble(txtDeposito.getText());
                if (deposito < 0) {
                    JOptionPane.showMessageDialog(this, "Depósito não pode ser negativo!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                deposito = 0;
            }
            
            Cliente cliente = new Cliente(nome, cpf, telefone, deposito, senha);
            banco.cadastrarConta(cliente);
            
            JOptionPane.showMessageDialog(this, 
                "Cliente cadastrado com sucesso!\nID da conta: " + cliente.getId(), 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            voltar();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void voltar() {
        this.dispose();
        telaPrincipal.voltarParaPrincipal();
    }
}