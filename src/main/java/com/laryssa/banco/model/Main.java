package com.laryssa.banco.model;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== SIMULAÇÃO BANCÁRIA ===");

        ContaCorrente contaCorrente = criarContaCorrente();
        ContaPoupanca contaPoupanca = criarContaPoupanca();

        realizarOperacoesContaCorrente(contaCorrente);
        realizarOperacoesContaPoupanca(contaPoupanca);

        exibirResultados(contaCorrente, contaPoupanca);
    }

    private static ContaCorrente criarContaCorrente() {
        BigDecimal limiteEspecial = new BigDecimal("1000.00");
        BigDecimal taxaMensal = new BigDecimal("25.00");

        ContaCorrente contaCorrente = new ContaCorrente(
                "laryssa",
                limiteEspecial,
                taxaMensal
        );

        return contaCorrente;
    }

    private static ContaPoupanca criarContaPoupanca() {
        BigDecimal taxaRendimento = new BigDecimal("5.00");

        ContaPoupanca contaPoupanca = new ContaPoupanca(
                "Tifanny",
                taxaRendimento
        );
        return contaPoupanca;
    }

    private static void realizarOperacoesContaCorrente(ContaCorrente contaCorrente) {
        contaCorrente.depositar(new BigDecimal("500"));
        contaCorrente.sacar(new BigDecimal("200"));
    }

    private static void realizarOperacoesContaPoupanca(ContaPoupanca contaPoupanca) {
        contaPoupanca.depositar(new BigDecimal("1000"));
        contaPoupanca.aplicarRendimento();
    }

    private static void exibirResultados(ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) {
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Saldo Conta Corrente: " + contaCorrente.getSaldo());
        System.out.println("Saldo Conta Poupança: " + contaPoupanca.getSaldo());
    }
}