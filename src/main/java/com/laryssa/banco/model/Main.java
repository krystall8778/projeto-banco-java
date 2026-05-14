package com.laryssa.banco.model;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== SIMULAÇÃO BANCÁRIA ===");


        BigDecimal limiteEspecial = new BigDecimal("1000.00");
        BigDecimal taxaMensal = new BigDecimal("25.00");
        BigDecimal taxaRendimento = new BigDecimal("5.00");

        ContaCorrente contaCorrente = criarContaCorrente();
        ContaPoupanca contaPoupanca = criarContaPoupanca();

        realizarOperacoesContaCorrente(contaCorrente);
        realizarOperacoesContaPoupanca(contaPoupanca);

        exibirResultados(contaCorrente, contaPoupanca);
    }

    private static ContaCorrente criarContaCorrente() {
        ContaCorrente conta = new ContaCorrente(
                "001",
                "Laryssa",
                new BigDecimal("1000.00"),
                new BigDecimal("25.00")
        );
        conta.abrirConta();
        return conta;
    }

    private static ContaPoupanca criarContaPoupanca() {
        ContaPoupanca conta = new ContaPoupanca(
                "002",
                "Tifanny",
                new BigDecimal("5.00")
        );
        conta.abrirConta();
        return conta;
    }

    private static void realizarOperacoesContaCorrente(ContaCorrente conta) {
        conta.depositar(new BigDecimal("500"));
        conta.sacar(new BigDecimal("200"));
    }

    private static void realizarOperacoesContaPoupanca(ContaPoupanca conta) {
        conta.depositar(new BigDecimal("1000"));
        conta.aplicarRendimento();
    }

    private static void exibirResultados(ContaCorrente cc, ContaPoupanca cp) {
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Saldo Conta Corrente: " + cc.getSaldo());
        System.out.println("Saldo Conta Poupança: " + cp.getSaldo());
    }
}