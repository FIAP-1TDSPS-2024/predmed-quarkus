package br.com.fiap.resource;

import br.com.fiap.dto.DiagnosticoDTO;
import br.com.fiap.model.Diagnostico;
import br.com.fiap.model.Paciente;
import br.com.fiap.service.DiagnosticoService;
import br.com.fiap.service.PacienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/diagnostico")
//@RolesAllowed({"admin", "user"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DiagnosticoResource {


    @Inject
    DiagnosticoService diagnosticoService;

    @POST
    @Path("/{idTriagem}")
    public Response createDiagnostico(@PathParam("idTriagem") int idTriagem){
        Diagnostico diagnostico = diagnosticoService.gerarDiagnostico(idTriagem);
        return Response.ok(diagnostico).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id){
        Diagnostico diagnostico = diagnosticoService.findById(id);
        return Response.ok(diagnostico).build();
    }
}
