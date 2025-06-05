package br.com.fiap.model;

import br.com.fiap.dto.DiagnosticoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "DIAGNOSTICO")
public class Diagnostico extends PanacheEntity {

    @OneToOne(mappedBy = "diagnostico")
    @JsonIgnore
    public Triagem triagem;

    @Column(name = "PROVAVEL_COVID", nullable = false)
    public boolean provavelCovid;

    @Column(name = "DATA_HORA_DIAGNOSTICO", nullable = false)
    public LocalDateTime dataDiagnostico;

    public Diagnostico() {}

    public Diagnostico(DiagnosticoDTO diagnosticoDTO) {

        this.dataDiagnostico = LocalDateTime.now();

        if (diagnosticoDTO.previsao() == 1){
            this.provavelCovid = true;
        }
        else{
            this.provavelCovid = false;
        }
    }
}
