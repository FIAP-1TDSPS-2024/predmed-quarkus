package br.com.fiap.Enum;

public enum SimNao {

    Sim,
    Não;

    public static SimNao fromBoolean(boolean valor){
        if (valor){
            return Sim;
        }
        else{
            return Não;
        }
    }
}
