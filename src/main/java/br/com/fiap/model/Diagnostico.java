package br.com.fiap.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "DIAGNOSTICO")
public class Diagnostico extends PanacheEntity {

    @OneToOne
    public Triagem triagem;

    @Column(name = "PROVAVEL_COVID", nullable = false)
    public boolean provavelCovid;

    @Column(name = "DATA_HORA_DIAGNOSTICO", nullable = false)
    public LocalDateTime dataHoraDiagnostico;

}
