package br.com.fiap.dto;

import br.com.fiap.Enum.SexoEnum;
import br.com.fiap.model.Triagem;
import br.com.fiap.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public record PacienteDTO(
        String nome,
        String cpf,
        String email,
        String celular,
        SexoEnum sexo,
        LocalDate dataNascimento
) {}
