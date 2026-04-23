package com.laryssa.banco.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordService {


    public static String gerarHash(String senha){
        if(senha == null || senha.isBlank()){
            throw new IllegalArgumentException("ERRO: valor inválido");
        }

        String hash = BCrypt.hashpw(senha, BCrypt.gensalt(10));
        return hash;
    }


    public static boolean verificarSenha(String senhaDigitada, String hashSalvo){
        if(senhaDigitada == null || senhaDigitada.isBlank()){
            throw new IllegalArgumentException("ERRO: valor inválido!");
        }

        if(hashSalvo == null || hashSalvo.isBlank()){
            throw new IllegalArgumentException("ERRO: valor inválido");
        }

        return  BCrypt.checkpw(senhaDigitada, hashSalvo);


    }

}

