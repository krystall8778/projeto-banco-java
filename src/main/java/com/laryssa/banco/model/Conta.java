package com.laryssa.banco.model;

import com.laryssa.banco.interfaces.OperacoesBancarias;
import com.laryssa.banco.model.enums.TipoTransacao;
import com.laryssa.banco.service.TransacaoFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Conta implements OperacoesBancarias {

    private final String numeroconta;
    private final String dono;
    private BigDecimal saldo;
    private boolean ativa;
    protected List <RegistroTransacao> historico = new ArrayList<>();

    public Conta(String numeroConta, String dono) {
        this.numeroconta = numeroConta;
        this.dono = dono;
        this.saldo = BigDecimal.ZERO;
        this.ativa = false;
    }

    public void abrirConta() {
        this.ativa = true;
    }

    public void fecharConta() {
        if (saldo.compareTo(BigDecimal.ZERO) == 0) {
            this.ativa = false;
        }
    }

    @Override
    public void depositar(BigDecimal valor) {
        validarValor(valor);

        creditar(valor);
        RegistroTransacao novaTransacao = TransacaoFactory.criarDeposito(this, valor);
        historico.add(novaTransacao);
    }

    @Override
    public void sacar(BigDecimal valor) {
        validarValor(valor);

        if (valor.compareTo(saldo) > 0)        {
            throw new IllegalArgumentException("ERRO: Valor inválido!");
        }

        debitar(valor);
        RegistroTransacao novaTransacao = TransacaoFactory.criarSaque(this, valor);
        historico.add(novaTransacao);
    }

    public void transferir(Conta contaDestino, BigDecimal valor){
        validarValor(valor);

        if(contaDestino == null){
            throw new IllegalArgumentException("ERRO: Conta destino não pode ser nula!");
        }

        if (valor.compareTo(saldo) > 0)        {
            throw new IllegalArgumentException("ERRO: Valor inválido!");
        }

        debitar(valor);
        contaDestino.creditar(valor);

        RegistroTransacao novaTransacao = TransacaoFactory.criarTransferencia(this, contaDestino, valor);
        historico.add(novaTransacao);
    }

    protected void debitar (BigDecimal valor)  {
        validarValor(valor);
        this.saldo = saldo.subtract(valor);
        aposAlteracaoSaldo();
    }

    protected void creditar(BigDecimal valor){
        validarValor(valor);
        saldo = saldo.add(valor);
        aposAlteracaoSaldo();
    }


    protected void validarValor(BigDecimal valor){
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("O valor da operação deve ser positivo!");
        }
        if (!ativa) {
            throw new IllegalArgumentException("A conta deve estar ativa!");
        }
    }

    protected void aposAlteracaoSaldo(){

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(numeroconta, conta.numeroconta);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroconta);
    }

    public String getDono() {
        return dono;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroconta;
    }

    public boolean isAtiva() {
        return ativa;
    }

}


