package view;

import main2.Banco3;
import main2.Gerente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroGerente extends JFrame {
    private JTextField txtNome, txtCPF, txtTelefone, txtMatricula, txtSalario;
    private JPasswordField txtSenha;
    private JButton btnCadastrar, btnVoltar;
    private TelaPrincipal telaPrincipal;
    private Banco3 banco;
    
    public TelaCadastroGerente(TelaPrincipal principal, Banco3 banco) {
        this.telaPrincipal = principal;
        this.banco = banco;
        
        setTitle("Cadastro de Gerente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        
        // Criar componentes
        JLabel lblTitulo = new JLabel("Cadastro de Gerente", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        
        txtNome = new JTextField(20);
        txtCPF = new JTextField(20);
        txtTelefone = new JTextField(20);
        txtMatricula = new JTextField(20);
        txtSalario = new JTextField(20);
        txtSenha = new JPasswordField(20);
        
        btnCadastrar = new JButton("Cadastrar");
        btnVoltar = new JButton("Voltar");
        
        // Layout
        setLayout(new BorderLayout());
        
        JPanel panelTop = new JPanel();
        panelTop.add(lblTitulo);
        
        JPanel panelCenter = new JPanel(new GridLayout(7, 2, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panelCenter.add(new JLabel("Nome:"));
        panelCenter.add(txtNome);
        panelCenter.add(new JLabel("CPF (000.000.000-00):"));
        panelCenter.add(txtCPF);
        panelCenter.add(new JLabel("Telefone ((00) 00000-0000):"));
        panelCenter.add(txtTelefone);
        panelCenter.add(new JLabel("Matrícula:"));
        panelCenter.add(txtMatricula);
        panelCenter.add(new JLabel("Salário R$:"));
        panelCenter.add(txtSalario);
        panelCenter.add(new JLabel("Senha:"));
        panelCenter.add(txtSenha);
        
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnCadastrar);
        panelBotoes.add(btnVoltar);
        
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
        // Eventos
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarGerente();
            }
        });
        
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                voltar();
            }
        });
    }
    
    private void cadastrarGerente() {
        try {
            String nome = txtNome.getText();
            String cpf = txtCPF.getText();
            String telefone = txtTelefone.getText();
            String matricula = txtMatricula.getText();
            String senha = new String(txtSenha.getPassword());
            
            if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || matricula.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar formato
            if (!Gerente.validarCPF(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF inválido! Use formato: 000.000.000-00", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!Gerente.validarTelefone(telefone)) {
                JOptionPane.showMessageDialog(this, "Telefone inválido! Use formato: (00) 00000-0000", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double salario = 0;
            try {
                salario = Double.parseDouble(txtSalario.getText());
                if (salario < 0) {
                    JOptionPane.showMessageDialog(this, "Salário não pode ser negativo!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Salário inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Gerente gerente = new Gerente(nome, cpf, telefone, matricula, salario, senha);
            banco.cadastrarConta(gerente);
            
            JOptionPane.showMessageDialog(this, 
                "Gerente cadastrado com sucesso!\nID da conta: " + gerente.getId(), 
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