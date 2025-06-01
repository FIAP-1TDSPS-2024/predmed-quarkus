package br.com.fiap.resource;

import br.com.fiap.model.Paciente;
import br.com.fiap.service.PacienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/diagnostico")
@RolesAllowed({"admin", "user"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DiagnosticoResource {


    @Inject
    PacienteService pacienteService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id){
        return Response.ok("Tem nada aqui ainda não kkkkkkk").build();
    }
}
