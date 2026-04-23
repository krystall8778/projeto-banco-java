package com.laryssa.banco.util;

import java.util.ArrayDeque;
import java.util.Deque;

import com.laryssa.banco.service.Banco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MenuNavegacao {
    private Deque <String> historicosMenus = new ArrayDeque<>();
    private static final Logger logger = LoggerFactory.getLogger(MenuNavegacao.class);



    public void irPara(String nomeDoMenu) {
        if (nomeDoMenu == null){
            return;
        }

        historicosMenus.push(nomeDoMenu);

        logger.info("Você está em : {} ", nomeDoMenu);

    }

    public void voltar(){
        if(historicosMenus.size() <= 1){
           logger.info("Você já está no menu principal!");
           return;
        }

        historicosMenus.pop();
        String menuAnterior = historicosMenus.peek();
    }












}
