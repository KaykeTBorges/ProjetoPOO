package main2;

// Classe abstrata base para qualquer pessoa no banco
public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String telefone;

    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }

    // Método que cada subclasse deve implementar
    public abstract void exibirInfo();
}
