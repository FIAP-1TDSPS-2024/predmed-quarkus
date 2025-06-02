package br.com.fiap.service;

import br.com.fiap.dto.LoginUsuarioDTO;
import br.com.fiap.exception.LoginInvalidoException;
import br.com.fiap.model.Usuario;
import br.com.fiap.utils.KeyUtils;
import io.quarkus.panache.common.Parameters;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.security.PrivateKey;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class UsuarioService {

    // Injetar parser para validar token
    @Inject
    JWTParser jwtParser;

    // Aqui você pode carregar a private key de forma segura (variável ambiente, cofre, etc)
    private PrivateKey privateKey = KeyUtils.loadPrivateKeyFromEnv();

    public String generateToken(Usuario usuario) {
        // Duração do token em segundos (ex: 1 hora)
        long duration = 3600;

        return Jwt.issuer("http://localhost:8080")
                .upn(usuario.nome)
                .groups(usuario.cargo)
                .expiresAt(System.currentTimeMillis() / 1000 + duration)
                .sign(privateKey);
    }

    public String login(LoginUsuarioDTO login){

        if ((login.emailFuncionario() != null) && (!login.senhaFuncionario().isEmpty())){
            Usuario usuarioLogado = Usuario.find("email = :email and senha = :senha",
                    Parameters.with("email", login.emailFuncionario()).and("senha", login.senhaFuncionario())).firstResult();

            if (usuarioLogado == null) {
                throw new LoginInvalidoException("Email ou senha inválidos.");
            }
            else{
                String token = generateToken(usuarioLogado);
                return token;
            }
        }
        else {
            throw new LoginInvalidoException("Campos de login não podem estar vazios.");
        }
    }

    public List<Usuario> listAll(){

        return Usuario.listAll();
    }

    public Usuario findById(int id){

        return (Usuario) Usuario.findByIdOptional(id).orElseThrow(() -> new NotFoundException("Não foi encontrado"));
    }

    @Transactional
    public Usuario save(Usuario usuario){

        verificaUsuario(usuario);

        Usuario.persist(usuario);
        return usuario;
    }

    @Transactional
    public Usuario update(int id, Usuario usuarioNovo){

        verificaUsuario(usuarioNovo);

        Usuario usuarioExistente = findById(id);

        usuarioExistente.nome = usuarioNovo.nome;
        usuarioExistente.cpf = usuarioNovo.cpf;
        usuarioExistente.email = usuarioNovo.email;
        usuarioExistente.senha = usuarioNovo.senha;

        return usuarioExistente;
    }

    @Transactional
    public void delete(int id){
        Usuario.deleteById(id);
    }

    private void verificaUsuario(Usuario usuario){
        if (usuario.nome == null){
            throw new BadRequestException("O nome do usuário não pode ser nulo");
        }
        else if (usuario.cpf == null){
            throw new BadRequestException("O cpf do usuário não pode ser nulo");
        }
        else if (!usuario.cpf.matches("^\\d{11}$")){
            throw new BadRequestException("O cpf do usuário deve ter apenas 11 caracteres e apenas números");
        }
        else if (!usuario.email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            throw new BadRequestException("Insira um e-mail válido");
        }
        else if (usuario.senha == null) {
            throw new BadRequestException("A senha do usuário não pode ser nulo");
        }
    }
}
