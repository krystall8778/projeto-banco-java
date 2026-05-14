package com.laryssa.banco.model;

import com.laryssa.banco.model.enums.StatusTransacao;
import com.laryssa.banco.model.enums.TipoTransacao;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

public class RegistroTransacao {
    private final LocalDateTime dataHora;
    private final TipoTransacao tipo;
    private final BigDecimal valor;
    private StatusTransacao status;
    private UUID id;
    private Conta contaOrigem;
    private Conta contaDestino;


    public RegistroTransacao(TipoTransacao tipo, BigDecimal valor, Conta contaOrigem, Conta contaDestino) {
        this.dataHora = LocalDateTime.now();
        this.id = UUID.randomUUID();
        this.tipo = tipo;
        this.valor = valor;
        this.status = StatusTransacao.PENDENTE;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;


   }

    public void executar(){
        validarTransacao();

        if (tipo == TipoTransacao.SAQUE){
            contaOrigem.debitar(getValor());
        }

        else if (tipo == TipoTransacao.DEPOSITO){
            contaDestino.creditar(getValor());
        }

        else if (tipo == TipoTransacao.TRANSFERENCIA){
            contaOrigem.debitar(getValor());
            contaDestino.creditar(getValor());

        }

    }


    public void desfazer(){
        validarTransacao();

        if (tipo == TipoTransacao.SAQUE){
            contaOrigem.creditar(getValor());
        }

        else if (tipo == TipoTransacao.DEPOSITO){
            contaDestino.debitar(getValor());
        }

        else if (tipo == TipoTransacao.TRANSFERENCIA){
            contaOrigem.creditar(getValor());
            contaDestino.debitar(getValor());

        }

    }

    public void validarTransacao(){
        if (tipo == TipoTransacao.SAQUE && contaOrigem == null) {
            throw new IllegalArgumentException("ERRO: conta origem não podeb ser nula!");
        }

        if(tipo == TipoTransacao.DEPOSITO && contaDestino == null){
            throw new IllegalArgumentException("ERR0: conta destino não pode ser nula!");
        }

        if(tipo == TipoTransacao.TRANSFERENCIA && (contaOrigem == null || contaDestino == null)) {
            throw new IllegalArgumentException("ERRO: conta origem ou conta destino não podem ser nulas!");

        }


    }


    public String processarRecibo(){
       if(id != null){
           String conteudo = gerarRecibo();
           this.status = StatusTransacao.CONCLUIDA;
           return conteudo;
       }
       else {
           this.status = StatusTransacao.FALHOU;
           throw new IllegalArgumentException("ERRO: Recibo inexistente!");

       }
    }


    public String gerarRecibo(){

        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataFormatada = formatadorData.format(dataHora);

        NumberFormat formatadorValor = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));
        String valorFormatado = formatadorValor.format(valor);

        return String.format(
                "---COMPROVANTE---\n" +
                        "ID : %S \n" +
                        "Data : %s \n" +
                        "tipo : %s \n" +
                        "valor : %s \n" +
                        "status : %s",

                id,
                dataFormatada,
                tipo,
                valorFormatado,
                status
        );

    }


    public Conta getContaDestino() {
        return contaDestino;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public UUID getId() {
        return id;
    }

    public void setStatus(StatusTransacao status) {
       this.status = status;
   }
}
