package br.com.fiap.dto;

import br.com.fiap.Enum.SimNao;
import br.com.fiap.model.Triagem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public record TriagemRequestDTO(
        @SerializedName("Problema Respiratório") SimNao problemaRespiratorio,
        @SerializedName("Febre") SimNao febre,
        @SerializedName("Tosse Seca") SimNao tosseSeca,
        @SerializedName("Dor de Garganta") SimNao dorGarganta,
        @SerializedName("Coriza") SimNao coriza,
        @SerializedName("Asma") SimNao asma,
        @SerializedName("Doença Pulmonar Crônica") SimNao doencaPulmonarCronica,
        @SerializedName("Dor de Cabeça") SimNao dorCabeca,
        @SerializedName("Doença Cardíaca") SimNao doencaCardiaca,
        @SerializedName("Diabetes") SimNao diabetes,
        @SerializedName("Hipertensão") SimNao hipertensao,
        @SerializedName("Fadiga") SimNao fadiga,
        @SerializedName("Problemas Gastrointestinais") SimNao problemasGastro,
        @SerializedName("Viagem ao Exterior") SimNao viagemExterior,
        @SerializedName("Contato com Paciente COVID") SimNao contatoInfectado,
        @SerializedName("Participou de Multidão") SimNao multidao,
        @SerializedName("Visitou Locais Públicos") SimNao localPublico,
        @SerializedName("Familiar em Local Público") SimNao familarLocalPublico
) {
    public TriagemRequestDTO(Triagem triagem) {
        this(
                SimNao.fromBoolean(triagem.problemaRespiratorio),
                SimNao.fromBoolean(triagem.febre),
                SimNao.fromBoolean(triagem.tosseSeca),
                SimNao.fromBoolean(triagem.dorGarganta),
                SimNao.fromBoolean(triagem.coriza),
                SimNao.fromBoolean(triagem.asma),
                SimNao.fromBoolean(triagem.doencaPulmonarCronica),
                SimNao.fromBoolean(triagem.dorCabeca),
                SimNao.fromBoolean(triagem.doencaCardiaca),
                SimNao.fromBoolean(triagem.diabetes),
                SimNao.fromBoolean(triagem.hipertensao),
                SimNao.fromBoolean(triagem.fadiga),
                SimNao.fromBoolean(triagem.problemasGastro),
                SimNao.fromBoolean(triagem.viagemExterior),
                SimNao.fromBoolean(triagem.contatoInfectado),
                SimNao.fromBoolean(triagem.multidao),
                SimNao.fromBoolean(triagem.localPublico),
                SimNao.fromBoolean(triagem.familarLocalPublico)
        );
    }
}
