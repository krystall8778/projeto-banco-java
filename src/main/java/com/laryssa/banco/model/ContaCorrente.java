package com.laryssa.banco.model;

import com.laryssa.banco.interfaces.CobrancaMensal;
import com.laryssa.banco.service.TransacaoFactory;

import java.math.BigDecimal;

public class ContaCorrente extends Conta implements CobrancaMensal {

    private BigDecimal limiteEspecial;
    private BigDecimal taxaMensal;


    public ContaCorrente(String dono, BigDecimal limiteEspecial, BigDecimal taxaMensal){
        super(dono);

        if (limiteEspecial.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Limite Especial não pode ser negativo.");
        }
        if (taxaMensal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Taxa mensal não pode ser negativa.");
        }
        this.limiteEspecial = limiteEspecial;
        this.taxaMensal = taxaMensal;
    }

    @Override
    public void pagarMensal() {
        if (!isAtiva()) {
            throw new IllegalArgumentException("ERRO: Conta deve estar ativa!");
        }
        debitar(taxaMensal);

    }

    @Override
    public void sacar(BigDecimal valor){
        validarValor(valor);
        if (valor.compareTo(getSaldoDisponivelParaSaque()) > 0) {
            throw new IllegalArgumentException("ERRO: Valor inválido!");
        }
        debitar(valor);
        RegistroTransacao novaTransacao = TransacaoFactory.criarSaque(this, valor);
        historico.add(novaTransacao);
    }

    public boolean isEmUsoChequeEspecial(){
        return  getSaldo().compareTo(BigDecimal.ZERO) < 0;
    }

    public BigDecimal getValorUsadoChequeEspecial(){
        if (getSaldo().compareTo(BigDecimal.ZERO) < 0) {
            return getSaldo().abs();
        } else {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getLimiteDisponivel(){
        return  limiteEspecial.subtract(getSaldoDisponivelParaSaque());
    }

    public BigDecimal getSaldoDisponivelParaSaque(){
        return getSaldo().add(limiteEspecial);
    }

}

