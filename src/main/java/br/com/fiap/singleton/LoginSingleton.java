package br.com.fiap.singleton;

import br.com.fiap.model.Usuario;

public class LoginSingleton {

    private static Usuario usuarioLogado;

    public static Usuario login(Usuario usuario){
        if (usuarioLogado == null){
            usuarioLogado = usuario;
            System.out.println("Tá logado familia");
        }
        return usuarioLogado;
    }

    public static void logoff(){
        if (usuarioLogado != null){
            usuarioLogado = null;
        }
    }

    public static Usuario getFuncionarioLogado() {
        return usuarioLogado;
    }
}
