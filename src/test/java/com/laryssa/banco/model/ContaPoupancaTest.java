package com.laryssa.banco.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ContaPoupancaTest {

    ContaPoupanca conta;

    @BeforeEach
    public void setUp() {
        conta = new ContaPoupanca("123", "Tifanny", BigDecimal.valueOf(5));
        conta.abrirConta();

        conta.depositar(BigDecimal.valueOf(1000));

    }

    @Test
    public void deveDepositarCorretamente() {
        conta.depositar(BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(2000), conta.getSaldo());
    }

    @Test
    public void devePermitirSaqueSeHouverSaldo() {
        conta.sacar(BigDecimal.valueOf(999));
        assertEquals(BigDecimal.valueOf(1), conta.getSaldo());

    }

    @Test
    public void naoDevePermitirSaqueMaiorQSaldo() {
        assertThrows(IllegalArgumentException.class, () -> {
            conta.sacar(BigDecimal.valueOf(3090));
        });
    }

    @Test
    public void deveAplicarTaxaDeRendimentoCorretamente() {
        conta.aplicarRendimento();
        assertEquals(new BigDecimal("1050.00"), conta.getSaldo());
    }

    @Test
    public void naoPermitidoDepositarValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            conta.depositar(BigDecimal.valueOf(-1000));
        });
    }

    @Test
    public void naoPermitirSaqueComValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            conta.sacar(BigDecimal.valueOf(-10));
        });
    }

    @Test
    public void saqueDoMesmoValorDoSaldo() {
        conta.sacar(BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(0), conta.getSaldo());
    }

    @Test
    public void taxaDeRendimentoComSaldoZero() {
        conta.sacar(BigDecimal.valueOf(1000));
        conta.aplicarRendimento();
        assertEquals(BigDecimal.valueOf(0), conta.getSaldo());
    }


}

