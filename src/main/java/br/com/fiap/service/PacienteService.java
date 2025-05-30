package br.com.fiap.service;

import br.com.fiap.dto.LoginFuncionarioDTO;
import br.com.fiap.exception.LoginInvalidoException;
import br.com.fiap.model.Paciente;
import br.com.fiap.model.Usuario;
import br.com.fiap.singleton.LoginSingleton;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class PacienteService {

    public List<Paciente> listAll(){

        return Paciente.listAll();
    }

    public Paciente findById(int id){

        return (Paciente) Paciente.findByIdOptional(id).orElseThrow(() -> new NotFoundException("Paciente não foi encontrado"));
    }

    @Transactional
    public Paciente save(Paciente paciente){

        //verificaFuncionario(usuario);

        Paciente.persist(paciente);
        return paciente;
    }

    @Transactional
    public Paciente update(int id, Paciente novoPaciente){

        //verificaFuncionario(usuarioNovo);

        Paciente pacienteExistente = findById(id);

        pacienteExistente.nome = novoPaciente.nome;
        pacienteExistente.cpf = novoPaciente.cpf;
        pacienteExistente.email = novoPaciente.email;
        pacienteExistente.celular = novoPaciente.celular;
        pacienteExistente.dataNascimento = novoPaciente.dataNascimento;
        pacienteExistente.sexo = novoPaciente.sexo;

        return pacienteExistente;
    }

    @Transactional
    public void delete(int id){
        Paciente.deleteById(id);
    }

    private void verificaPaciente(Paciente paciente){
        if (paciente.nome == null){
            throw new BadRequestException("O nome do paciente não pode ser nulo");
        }
        else if (paciente.cpf == null){
            throw new BadRequestException("O cpf do paciente não pode ser nulo");
        }
        else if (!paciente.cpf.matches("^\\d{11}$")){
            throw new BadRequestException("O cpf do paciente deve ter apenas 11 caracteres e apenas números");
        }
        else if (!paciente.email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            throw new BadRequestException("Insira um e-mail válido");
        }
    }
}
