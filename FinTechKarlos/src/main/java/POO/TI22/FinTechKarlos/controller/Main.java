package POO.TI22.FinTechKarlos.controller;

import java.util.Scanner;

import POO.TI22.FinTechKarlos.model.ContaBancaria;
import POO.TI22.FinTechKarlos.model.User;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContaBancaria conta1 = new ContaBancaria("Alice", 1000, 1000);
        ContaBancaria conta2 = new ContaBancaria("Bob", 5000, 1000);

        User user = new User("Contas 01");

        user.adicionarConta(conta1);
        user.adicionarConta(conta2);

        user.listarContas();

        scanner.close();
    }
}