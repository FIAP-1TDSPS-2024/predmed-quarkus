package br.com.fiap.model;

import br.com.fiap.Enum.SexoEnum;
import br.com.fiap.dto.PacienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PACIENTE")
public class Paciente extends PanacheEntity {

    @Column(name = "NOME", nullable = false, length = 100)
    public String nome;

    @Column(name = "CPF", length = 14)
    public String cpf;

    @Column(name = "EMAIL", length = 30)
    public String email;

    @Column(name = "CELULAR", length = 14)
    public String celular;

    @Column(name = "SEXO", nullable = false, length = 1)
    public SexoEnum sexo;

    @Column(name = "DATA_NASC", nullable = false)
    public LocalDate dataNascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    public List<Triagem> triagems;

    @ManyToOne
    public Usuario registrouPaciente;

    public Paciente() {
    }

    public Paciente(PacienteDTO pacienteDTO){
        this.nome = pacienteDTO.nome();
        this.cpf = pacienteDTO.cpf();
        this.celular = pacienteDTO.celular();
        this.email = pacienteDTO.email();
        this.sexo = pacienteDTO.sexo();
        this.dataNascimento = pacienteDTO.dataNascimento();
    }
}
