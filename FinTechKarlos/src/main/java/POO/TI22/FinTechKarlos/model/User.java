// User.java (com o método listarContas())
package POO.TI22.FinTechKarlos.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nome;
    private List<ContaBancaria> contaBancaria;

    public User(String nome) {
        this.nome = nome;
        this.contaBancaria = new ArrayList<>();
    }

    public void adicionarConta(ContaBancaria conta) {
        this.contaBancaria.add(conta);
    }

    public List<ContaBancaria> getContas() {
        return contaBancaria;
    }

    public void listarContas() {
        System.out.println("Lista: " + this.nome);
        for (ContaBancaria conta : contaBancaria) {
            conta.exibirConta();
        }
    }
}