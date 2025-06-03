package br.com.fiap.dto;

public record TriagemDTO (
        Boolean problemaRespiratorio,
        Boolean febre,
        Boolean tosseSeca,
        Boolean dorGarganta,
        Boolean coriza,
        Boolean asma,
        Boolean doencaPulmonarCronica,
        Boolean dorCabeca,
        Boolean doencaCardiaca,
        Boolean diabetes,
        Boolean hipertensao,
        Boolean fadiga,
        Boolean problemasGastro,
        Boolean viagemExterior,
        Boolean contatoInfectado,
        Boolean multidao,
        Boolean localPublico,
        Boolean familarLocalPublico
){}
