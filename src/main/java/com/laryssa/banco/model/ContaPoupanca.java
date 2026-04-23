package com.laryssa.banco.model;

import com.laryssa.banco.interfaces.AplicarRendimento;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaPoupanca extends Conta implements AplicarRendimento {

    private BigDecimal taxaRendimento;


    public ContaPoupanca(String numeroConta, String dono, BigDecimal taxaRendimento){
        super(numeroConta, dono);

        if (taxaRendimento.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Taxa de rendimento não deve ser negativa!");
        }
        this.taxaRendimento = taxaRendimento.divide( new BigDecimal("100"), 2 , RoundingMode.HALF_UP);
    }

    @Override
    public void aplicarRendimento(){
        if (!isAtiva()) {
            throw new IllegalArgumentException("ERRO: Conta deve estar ativa!");
        }

        BigDecimal rendimento = getSaldo().multiply(taxaRendimento);

        if(rendimento.compareTo(BigDecimal.ZERO) > 0){
            depositar(rendimento);
        }

    }

}
