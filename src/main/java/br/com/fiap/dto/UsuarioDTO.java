package br.com.fiap.dto;

import br.com.fiap.Enum.SexoEnum;

import java.time.LocalDate;

public record UsuarioDTO(
        String nome,
        String cpf,
        String email,
        String senha,
        String cargo
) {}
