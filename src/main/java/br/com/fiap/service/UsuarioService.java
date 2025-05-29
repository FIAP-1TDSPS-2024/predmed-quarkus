package br.com.fiap.service;

import br.com.fiap.dto.LoginFuncionarioDTO;
import br.com.fiap.exception.AcessoNegadoexception;
import br.com.fiap.exception.LoginInvalidoException;
import br.com.fiap.model.Usuario;
import br.com.fiap.singleton.LoginSingleton;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class UsuarioService {

    public Usuario login(LoginFuncionarioDTO login){

        if ((login.emailFuncionario() != null) && (!login.senhaFuncionario().isEmpty())){
            Usuario usuarioLogado = Usuario.find("email = :email and senha = :senha",
                    Parameters.with("email", login.emailFuncionario()).and("senha", login.senhaFuncionario())).firstResult();

            if (usuarioLogado == null) {
                throw new LoginInvalidoException("Email ou senha inválidos.");
            }

            LoginSingleton.login(usuarioLogado);

            return usuarioLogado;
        }
        else {
            throw new LoginInvalidoException("Campos de login não podem estar vazios.");
        }
    }

    public void logoff(){
        LoginSingleton.logoff();
    }

    public List<Usuario> listAll(){

        return Usuario.listAll();
    }

    public Usuario findById(int id){

        return (Usuario) Usuario.findByIdOptional(id).orElseThrow(() -> new NotFoundException("Não foi encontrado"));
    }

    @Transactional
    public Usuario save(Usuario usuario){

        //verificaFuncionario(usuario);

        Usuario.persist(usuario);
        return usuario;
    }

    @Transactional
    public Usuario update(int id, Usuario usuarioNovo){

        //verificaFuncionario(usuarioNovo);

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

    private void verificaFuncionario(Usuario usuario){
        if (usuario.nome == null){
            throw new BadRequestException("O nome do funcionário não pode ser nulo");
        }
        else if (usuario.cpf == null){
            throw new BadRequestException("O cpf do funcionário não pode ser nulo");
        }
        else if (!usuario.cpf.matches("^\\d{11}$")){
            throw new BadRequestException("O cpf do funcionário deve ter apenas 11 caracteres e apenas números");
        }
        else if (!usuario.email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            throw new BadRequestException("Insira um e-mail válido");
        }
        else if (usuario.senha == null) {
            throw new BadRequestException("A senha do funcionário não pode ser nulo");
        }
    }
}
