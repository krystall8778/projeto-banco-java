package com.laryssa.banco.interfaces;


import java.math.BigDecimal;

public interface OperacoesBancarias {
    void depositar(BigDecimal depositar);
    void sacar(BigDecimal sacar);
}
