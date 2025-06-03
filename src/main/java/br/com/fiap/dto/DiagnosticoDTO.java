package br.com.fiap.dto;

public record DiagnosticoDTO (
        int previsao,
        Double probabilidade,
        String resultado
){ }
