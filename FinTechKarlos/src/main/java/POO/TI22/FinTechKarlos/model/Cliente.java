package POO.TI22.FinTechKarlos.model;

public class Cliente {
    private String nome;
    private int idade;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;

    public Cliente(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        //retorne todos os atributos usando o get deles ex: "\nNome: " + getNome()
        return "\nNome: " + getNome() + "\nIdade: " + getIdade() + "\nCPF: " + getCpf() + "\nEmail: " + getEmail() + "\nTelefone: " + getTelefone() + "\nEndereço: " + getEndereco();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}