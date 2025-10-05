package main2;

public abstract class Funcionario extends Pessoa {
    protected String matricula;
    protected double salario;

    public Funcionario(String nome, String cpf, String telefone, String matricula, double salario) {
        super(nome, cpf, telefone);
        this.matricula = matricula;
        this.salario = salario;
    }

    public String getMatricula() { return matricula; }
    public double getSalario() { return salario; }

    public abstract void realizarAtividade();
}