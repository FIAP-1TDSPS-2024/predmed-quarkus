package br.com.fiap.model;

import br.com.fiap.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "USUARIO")
public class Usuario extends PanacheEntity {

    @Column(name = "NOME", nullable = false, length = 100)
    public String nome;

    @Column(name = "CPF", nullable = false, length = 14)
    public String cpf;

    @Column(name = "EMAIL", nullable = false, length = 30)
    public String email;

    @Column(name = "SENHA", nullable = false, length = 40)
    public String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "registrouTriagem")
    public List<Triagem> registrosTriagem;

    @JsonIgnore
    @OneToMany(mappedBy = "registrouPaciente")
    public List<Paciente> pacientesRegistrados;

    public Usuario() {
    }

    public Usuario(UsuarioDTO usuarioDTO){
        this.nome = usuarioDTO.nome();
        this.cpf = usuarioDTO.cpf();
        this.email = usuarioDTO.email();
        this.senha = usuarioDTO.senha();
    }
}
