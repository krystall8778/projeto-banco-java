package com.laryssa.banco.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ContaCorrenteTest {

    ContaCorrente conta;

    @BeforeEach
    public void setUp() {
        conta = new ContaCorrente("laryssa", BigDecimal.valueOf(500), BigDecimal.valueOf(5));
        conta.depositar(BigDecimal.valueOf(200));
    }

    @Test
    public void deveDepositarCorretamente(){
        assertEquals(BigDecimal.valueOf(200), conta.getSaldo());
    }

    @Test
    public void naoDeveAutorizarSaqueMaiorQSaldoMaisLimite (){
        assertThrows(IllegalArgumentException.class , () -> {
            conta.sacar(BigDecimal.valueOf(800));

        });

    }

    @Test
    public void devePermitirSaqueUsandoLimite () {
        conta.sacar(BigDecimal.valueOf(600));
        assertEquals(BigDecimal.valueOf(-400), conta.getSaldo());
    }

    @Test
    public void deveCobrarTaxaMensal () {
        conta.pagarMensal();
        assertEquals(BigDecimal.valueOf(195), conta.getSaldo());
    }

    @Test
    public void transferenciaValida () {


        conta.transferir(conta, BigDecimal.valueOf(500));



    }

}
