package com.laryssa.banco.service;

import com.laryssa.banco.model.Cliente;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laryssa.banco.model.Conta;
import com.laryssa.banco.util.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Banco {
    private String nomeBanco;
    private Map<String, Cliente> clientes = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(Banco.class);
    private static final BigDecimal limite_vip = new BigDecimal("10000");


    public Banco(String nomeBanco){
        this.nomeBanco = nomeBanco;

    }


    public void validarCliente(Cliente novoCliente) {
        if (novoCliente == null) {
            throw new IllegalArgumentException("ERRO: Cliente não pode ser nulo!");
        }

        if (clientes.containsKey(novoCliente.getCpf())) {
            throw new IllegalArgumentException("ERRO: Cliente já cadastrado!");
        }

    }


    public void cadastrarCliente(Cliente novoCliente, String senhaDigitada) {
        validarCliente(novoCliente);

        if(senhaDigitada == null || senhaDigitada.isBlank()){
            throw new IllegalArgumentException("ERRO: valor não pode ser nulo ou branco!");

        }

        String senhaGerada = PasswordService.gerarHash(senhaDigitada);
        novoCliente.setSenha(senhaGerada);

        clientes.put(novoCliente.getCpf(), novoCliente);
        logger.info("Cliente cadastrado com sucesso. CPF : {}", novoCliente.getCpf());
    }


    public  Cliente buscarPorCpf (String cpf) {
        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("ERRO: Crendenciais inválidas!");
        }

        Cliente clienteEncontrado = clientes.get(cpf);

        if(clienteEncontrado == null) {
            logger.error("Cliente não encontrado. CPF : {} ", cpf);
            throw new IllegalArgumentException("ERRO: Credenciais inválidas!");
        }

        return clienteEncontrado;

    }


    public void validarEntrada(String cpf, String senha){
        if(cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("ERRO: Credenciais inválidas!");
        }

        if(senha == null || senha.isBlank()){
            throw new IllegalArgumentException("ERRO: Credenciais inválidas!");
        }

    }


    public Cliente autenticarCliente (String cpf, String senha){
        validarEntrada(cpf, senha);
        Cliente cliente = buscarPorCpf(cpf);

        boolean verificacao = PasswordService.verificarSenha(senha,cliente.getSenha());
            if(!verificacao){
                throw new IllegalArgumentException("ERRO: Crendeciais inválidas!");
            }

        logger.info("Login realizado com sucesso para o CPF : {}", cpf);
        return cliente;

    }


    public List listarVips() {
        List <Cliente> listaVips = clientes.values().stream().filter(cliente -> cliente.getSaldoTotal().compareTo(limite_vip) >= 0).toList();
        return listaVips;
    }


    public Map<String, Cliente> getClientes() {
        return clientes;
    }
}