package com.desafio.api.utils;


public class ValidadorCpfCnpj {
    public String verificaSeCpf(String cpfCnpj) {
        String cpf = cpfCnpj;

        if (cpfCnpj.length() == 11) {
            return cpf;
        }
        return null;
    }

    public String verificaSeCnpj(String cpfCnpj) {
        String cnpj = cpfCnpj;

        if (cpfCnpj.length() == 14) {
            return cnpj;
        }
        return null;
    }
}
