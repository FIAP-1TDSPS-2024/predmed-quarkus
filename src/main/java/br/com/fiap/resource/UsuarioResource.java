package br.com.fiap.resource;

import br.com.fiap.dto.UsuarioDTO;
import br.com.fiap.model.Usuario;
import br.com.fiap.service.UsuarioService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("api/usuarios")
@RolesAllowed("admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @GET
    public Response listAll(){
        List<Usuario> usuarios = usuarioService.listAll();

        if (usuarios.isEmpty()){
            return Response.noContent().build();
        }
        else {
            return Response.ok(usuarios).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id){
        Usuario usuario = usuarioService.findById(id);
        return Response.ok(usuario).build();
    }

    @POST
    public Response create(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(usuarioDTO);
        Usuario novoUsuario = usuarioService.save(usuario);
        return Response.created(URI.create("api/usuario" + novoUsuario.id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(usuarioDTO);
        Usuario novoUsuario = usuarioService.update(id, usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        usuarioService.delete(id);
        return Response.noContent().build();
    }

}
