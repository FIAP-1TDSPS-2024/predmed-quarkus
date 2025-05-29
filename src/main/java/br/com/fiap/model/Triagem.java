package br.com.fiap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRIAGEM")
public class Triagem extends PanacheEntity {

    @Column(name = "DATA_HORA_TRIAGEM", nullable = false)
    public LocalDateTime dataTriagem;

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
}

