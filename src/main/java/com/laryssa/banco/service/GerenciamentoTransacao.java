package com.laryssa.banco.service;

import com.laryssa.banco.model.Conta;
import com.laryssa.banco.model.RegistroTransacao;
import com.laryssa.banco.model.enums.StatusTransacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;


public class GerenciamentoTransacao {
    private Queue <RegistroTransacao> filaPendencias = new LinkedList<>();
    private static final Logger logger = LoggerFactory.getLogger(GerenciamentoTransacao.class);


    public void enfilerartransacao(RegistroTransacao transacao){

        if(transacao == null){
            throw new IllegalArgumentException("ERRO: Valor inválido");
        }

        if (transacao.getId() == null){
            throw new IllegalArgumentException("ERRO: Transação inválida!");
        }

        if(transacao.getStatus() == null){
            throw new IllegalArgumentException("ERRO: Status inválido!");
        }

        transacao.setStatus(StatusTransacao.PENDENTE);
        filaPendencias.offer(transacao);
        logger.info("Transação id: {} aguardando processamento...", transacao.getId());
    }


    public void processaLote() {
        while (!filaPendencias.isEmpty()) {

            RegistroTransacao transacaoAtual = filaPendencias.poll();

            if(transacaoAtual == null) {
                continue;
            }

            transacaoAtual.validarTransacao();

            try {
                transacaoAtual.executar();
                transacaoAtual.setStatus(StatusTransacao.CONCLUIDA);

            } catch (Exception e) {

                transacaoAtual.desfazer();
                logger.error ("Erro ao processar id : {}", transacaoAtual.getId(), e);
                transacaoAtual.setStatus(StatusTransacao.FALHOU);

            }

        }

    }

}



