package br.com.fiap.resource;

import br.com.fiap.dto.LoginUsuarioDTO;
import br.com.fiap.dto.TokenDTO;
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

    @POST
    public Response login(LoginUsuarioDTO login) {
        String token = usuarioService.login(login);
        return Response.ok(new TokenDTO(token)).build();
    }
}