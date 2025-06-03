package br.com.fiap.model;

import br.com.fiap.dto.DiagnosticoDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "DIAGNOSTICO")
public class Diagnostico extends PanacheEntity {

    @OneToOne
    public Triagem triagem;

    @Column(name = "PROVAVEL_COVID", nullable = false)
    public boolean provavelCovid;

    @Column(name = "DATA_HORA_DIAGNOSTICO", nullable = false)
    public LocalDateTime dataDiagnostico;

    public Diagnostico() {}

    public Diagnostico(DiagnosticoDTO diagnosticoDTO, Triagem triagem) {
        this.triagem = triagem;
        this.dataDiagnostico = LocalDateTime.now();

        if (diagnosticoDTO.previsao() == 1){
            this.provavelCovid = true;
        }
        else{
            this.provavelCovid = false;
        }
    }
}
