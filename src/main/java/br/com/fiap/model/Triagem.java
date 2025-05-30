package br.com.fiap.model;

import br.com.fiap.dto.TriagemDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRIAGEM")
public class Triagem extends PanacheEntity {

    @Column(name = "DATA_TRIAGEM", nullable = false)
    public LocalDate dataTriagem;

    @ManyToOne
    public Paciente paciente;

    @JsonIgnore
    @ManyToOne
    public Usuario registrouTriagem;

    @OneToOne
    public Diagnostico diagnostico;

    @Column(name = "PROBLEMA_RESPIRATORIO", nullable = false)
    public Boolean problemaRespiratorio;

    @Column(name = "FEBRE", nullable = true)
    public Boolean febre;

    @Column(name = "TOSSE_SECA", nullable = true)
    public Boolean tosseSeca;

    @Column(name = "DOR_GARGANTA", nullable = true)
    public Boolean dorGarganta;

    @Column(name = "CORIZA", nullable = true)
    public Boolean coriza;

    @Column(name = "ASMA", nullable = true)
    public Boolean asma;

    @Column(name = "DOENCA_PULMONAR_CRONICA", nullable = true)
    public Boolean doencaPulmonarCronica;

    @Column(name = "DOR_CABECAC", nullable = true)
    public Boolean dorCabeca;

    @Column(name = "DOENCA_CARDIACA", nullable = true)
    public Boolean doencaCardiaca;

    @Column(name = "DIABETES", nullable = true)
    public Boolean diabetes;

    @Column(name = "HIPERTENSAO", nullable = true)
    public Boolean hipertensao;

    @Column(name = "FADIGA", nullable = true)
    public Boolean fadiga;

    @Column(name = "PROBLEMAS_GASTRO", nullable = true)
    public Boolean problemasGastro;

    @Column(name = "VIAGEM_EXTERIOR", nullable = true)
    public Boolean viagemExterior;

    @Column(name = "CONTATO_INFECTADO", nullable = true)
    public Boolean contatoInfectado;

    @Column(name = "MULIDAO", nullable = true)
    public Boolean multidao;

    @Column(name = "LOCAL_PUBLICO", nullable = true)
    public Boolean localPublico;

    @Column(name = "FAMILIAR_LOCAL_PUBLICO", nullable = true)
    public Boolean familarLocalPublico;

    @Column(name = "USA_MARCAR", nullable = true)
    public Boolean usaMarcara;

    @Column(name = "HIGIENE_LOCAL_TRABALHO", nullable = true)
    public Boolean higieneLocalTrabalho;

    public Triagem(){}

    public Triagem(TriagemDTO triagemDTO) {
        this.dataTriagem = LocalDate.now();
        System.out.println(dataTriagem);
        this.problemaRespiratorio = triagemDTO.problemaRespiratorio();
        this.febre = triagemDTO.febre();
        this.tosseSeca = triagemDTO.tosseSeca();
        this.dorGarganta = triagemDTO.dorGarganta();
        this.coriza = triagemDTO.coriza();
        this.asma = triagemDTO.asma();
        this.doencaPulmonarCronica = triagemDTO.doencaPulmonarCronica();
        this.dorCabeca = triagemDTO.dorCabeca();
        this.doencaCardiaca = triagemDTO.doencaCardiaca();
        this.diabetes = triagemDTO.diabetes();
        this.hipertensao = triagemDTO.hipertensao();
        this.fadiga = triagemDTO.fadiga();
        this.problemasGastro = triagemDTO.problemasGastro();
        this.viagemExterior = triagemDTO.viagemExterior();
        this.contatoInfectado = triagemDTO.contatoInfectado();
        this.multidao = triagemDTO.multidao();
        this.localPublico = triagemDTO.localPublico();
        this.familarLocalPublico = triagemDTO.familarLocalPublico();
        this.usaMarcara = triagemDTO.usaMarcara();
        this.higieneLocalTrabalho = triagemDTO.higieneLocalTrabalho();
    }
}

