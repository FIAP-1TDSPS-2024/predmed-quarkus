package br.com.fiap.service;

import br.com.fiap.model.Paciente;
import br.com.fiap.model.Triagem;
import br.com.fiap.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class TriagemService {

    public List<Triagem> listAllByIdPaciente(int pacienteId){
        return Triagem.list("funcionario.id", pacienteId);
    }

    public Triagem findById(int id){

        return (Triagem) Triagem.findByIdOptional(id).orElseThrow(() -> new NotFoundException("Triagem em questão não foi encontrada"));
    }

    @Transactional
    public Triagem save(int idPaciente, Triagem triagem){

        triagem.paciente = Paciente.findById(idPaciente);

        Triagem.persist(triagem);
        return triagem;
    }

    @Transactional
    public Triagem update(int id, Triagem novaTriagem){

        Triagem triagemExistente = findById(id);

        triagemExistente.asma = novaTriagem.asma;
        triagemExistente.doencaPulmonarCronica = novaTriagem.doencaPulmonarCronica;
        triagemExistente.dorCabeca = novaTriagem.dorCabeca;
        triagemExistente.doencaCardiaca = novaTriagem.doencaCardiaca;
        triagemExistente.diabetes = novaTriagem.diabetes;
        triagemExistente.hipertensao = novaTriagem.hipertensao;
        triagemExistente.fadiga = novaTriagem.fadiga;
        triagemExistente.problemasGastro = novaTriagem.problemasGastro;
        triagemExistente.viagemExterior = novaTriagem.viagemExterior;
        triagemExistente.contatoInfectado = novaTriagem.contatoInfectado;
        triagemExistente.multidao = novaTriagem.multidao;
        triagemExistente.localPublico = novaTriagem.localPublico;
        triagemExistente.familarLocalPublico = novaTriagem.familarLocalPublico;
        triagemExistente.usaMarcara = novaTriagem.usaMarcara;
        triagemExistente.higieneLocalTrabalho = novaTriagem.higieneLocalTrabalho;

        return triagemExistente;
    }

    @Transactional
    public void delete(int id){
        Triagem.deleteById(id);
    }
}
