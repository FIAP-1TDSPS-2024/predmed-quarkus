package br.com.fiap.resource;

import br.com.fiap.dto.LoginFuncionarioDTO;
import br.com.fiap.model.Usuario;
import br.com.fiap.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LoginResource {

    @Inject
    UsuarioService usuarioService;

    @GET
    public Response login(LoginFuncionarioDTO login){
        Usuario usuarioLogado = usuarioService.login(login);
        return Response.ok(usuarioLogado).build();
    }

    @GET
    @Path("/exit")
    public Response logoff(){
        usuarioService.logoff();
        return Response.ok().build();
    }



}