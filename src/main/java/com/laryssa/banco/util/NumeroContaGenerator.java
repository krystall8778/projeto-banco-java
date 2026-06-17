package com.laryssa.banco.util;

import java.util.Random;

public class NumeroContaGenerator {
    private static final Random random = new Random();

    public static String gerar() {
        int numero = 10000000 + random.nextInt(90000000);
        return String.valueOf(numero);
    }
}