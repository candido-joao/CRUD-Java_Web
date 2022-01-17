package com.example.demo.Utils;

import com.example.demo.Models.UsuarioModel;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioUtils {
    public static final String USUARIOS_PARM_SESSION = "usuarios";

    public static List<UsuarioModel> getListaDeUsuariosNaSessao(HttpSession session) {
        if (!Optional.ofNullable(session.getAttribute(USUARIOS_PARM_SESSION)).isPresent())
            setListaDeUsuariosNaSessao(session, dadosDeExemplo());

        return (List<UsuarioModel>) session.getAttribute(USUARIOS_PARM_SESSION);
    }

    public static void setListaDeUsuariosNaSessao(HttpSession session, List<UsuarioModel> usuarios) {
        session.setAttribute(USUARIOS_PARM_SESSION, usuarios);
    }

    private static List<UsuarioModel> dadosDeExemplo() {
        List<UsuarioModel> usuarios = new ArrayList<>();

        usuarios.add(new UsuarioModel("Teste", 1, "12345678910", "46999999999"));
        usuarios.add(new UsuarioModel("Fulano", 2, "98765432101", "46999999998"));

        return usuarios;
    }
}
