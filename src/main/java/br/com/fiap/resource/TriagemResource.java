package br.com.fiap.resource;

import br.com.fiap.dto.TriagemDTO;
import br.com.fiap.dto.UsuarioDTO;
import br.com.fiap.model.Triagem;
import br.com.fiap.model.Usuario;
import br.com.fiap.service.TriagemService;
import br.com.fiap.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("api/triagem")
@RolesAllowed({ "admin", "user" })
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TriagemResource {

    @Inject
    UsuarioService usuarioService;

    @Inject
    TriagemService triagemService;

    @GET
    @Path("/paciente/{pacienteId}")
    public Response listAll(@PathParam("pacienteId") int pacienteId) {
        List<Triagem> triagems = triagemService.listAllByIdPaciente(pacienteId);

        if (triagems.isEmpty()) {
            return Response.noContent().build();
        } else {
            return Response.ok(triagems).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        Triagem triagem = triagemService.findById(id);
        return Response.ok(triagem).build();
    }

    @POST
    @Path("/{pacienteId}")
    public Response create(@PathParam("pacienteId") int pacienteId, TriagemDTO triagemDTO) {
        Triagem triagem = new Triagem(triagemDTO);
        Triagem novaTriagem = triagemService.save(pacienteId, triagem);
        return Response.ok(novaTriagem).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, TriagemDTO triagemDTO) {
        Triagem triagem = new Triagem(triagemDTO);
        Triagem novaTriagem = triagemService.update(id, triagem);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        triagemService.delete(id);
        return Response.noContent().build();
    }

}
