package br.com.fiap.resource;

import br.com.fiap.dto.PacienteDTO;
import br.com.fiap.model.Paciente;
import br.com.fiap.model.Usuario;
import br.com.fiap.service.PacienteService;
import br.com.fiap.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("api/pacientes")
@RolesAllowed({"admin", "user"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PacienteResource {

    @Inject
    PacienteService pacienteService;

    @GET
    public Response listAll(){
        List<Paciente> pacientes = pacienteService.listAll();

        if (pacientes.isEmpty()){
            return Response.noContent().build();
        }
        else {
            return Response.ok(pacientes).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id){
        Paciente paciente = pacienteService.findById(id);
        return Response.ok(paciente).build();
    }

    @POST
    public Response create(PacienteDTO pacienteDTO){
        Paciente paciente = new Paciente(pacienteDTO);
        Paciente novoPaciente = pacienteService.save(paciente);
        return Response.created(URI.create("api/funcionarios" + novoPaciente.id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, PacienteDTO pacienteDTO){
        Paciente paciente = new Paciente(pacienteDTO);
        Paciente novoPaciente = pacienteService.update(id, paciente);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        pacienteService.delete(id);
        return Response.noContent().build();
    }

}
