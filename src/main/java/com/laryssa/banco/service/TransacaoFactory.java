package com.laryssa.banco.service;

import com.laryssa.banco.model.Conta;
import com.laryssa.banco.model.RegistroTransacao;
import com.laryssa.banco.model.enums.TipoTransacao;

import java.math.BigDecimal;

public class TransacaoFactory {

    public static RegistroTransacao criarSaque(Conta contaOrigem, BigDecimal valor){
        if(contaOrigem == null){
        throw new IllegalArgumentException("ERRO : Conta origem obrigatória!");
        }

        return new RegistroTransacao(TipoTransacao.SAQUE, valor, contaOrigem, null );

    }


    public static RegistroTransacao criarDeposito(Conta contaDestino, BigDecimal valor){
        if(contaDestino == null){
            throw new IllegalArgumentException("ERRO : Conta destino obrigatória!");

        }

        return new RegistroTransacao(TipoTransacao.DEPOSITO, valor, null, contaDestino);

    }


    public static RegistroTransacao criarTransferencia(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
        if (contaOrigem == null || contaDestino == null) {
            throw new IllegalArgumentException("ERRO : Transferência precisa de origem e destino!");
        }

        return new RegistroTransacao(TipoTransacao.TRANSFERENCIA, valor, contaOrigem, contaDestino);

    }

}
