package com.laryssa.banco.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cliente {
    private final UUID id;
    private String nome;
    private final String cpf;
    private String senha;
    private List<Conta> contas = new ArrayList<>( );

    public Cliente(String nome, String cpf) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.cpf = cpf;
    }

    public void vincularConta (Conta novaConta){
        if(novaConta == null){
            throw new IllegalArgumentException("ERRO: Conta não pode ser nula!");
        }

        if(contas.stream().anyMatch(novaConta::equals)) {
            throw new IllegalArgumentException("ERRO: Conta já existe!");
        }

        this.contas.add(novaConta);
    }

    public BigDecimal getSaldoTotal(){
        return this.contas.stream().map(Conta::getSaldo).reduce(BigDecimal.ZERO, BigDecimal::add);

    }

        public UUID getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List getContas() {
        return contas;
    }

}


