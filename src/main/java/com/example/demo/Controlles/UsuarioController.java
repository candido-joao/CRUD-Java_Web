package com.example.demo.Controlles;
import com.example.demo.Models.UsuarioModel;
import com.example.demo.Utils.UsuarioUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("Usuario")
public class UsuarioController {
    @RequestMapping("/Novo")
    public String getFormUsuarioVazio(Model model) {
        model.addAttribute("usuario", new UsuarioModel("", 0, "", ""));

        return "Usuario";
    }

    @RequestMapping("/Alterar")
    public String getFormUsuario(@RequestParam("id") int id, Model model, HttpServletRequest request) {
        List<UsuarioModel> usuarios = UsuarioUtils.getListaDeUsuariosNaSessao(request.getSession());

        UsuarioModel usuario = new UsuarioModel("", 0, "", "");

        for (UsuarioModel o: usuarios) {
            if (o.getId() == id) {
                usuario = o;
                break;
            }
        }

        model.addAttribute("usuario", usuario);

        return "Usuario";
    }

    @RequestMapping("/Excluir")
    public String excluir(HttpServletRequest request, @RequestParam("id") int id){
        List<UsuarioModel> usuarios = UsuarioUtils.getListaDeUsuariosNaSessao(request.getSession());

        for (UsuarioModel usuario: usuarios) {
            if (usuario.getId() == id) {
                usuarios.remove(usuario);
                break;
            }
        }

        UsuarioUtils.setListaDeUsuariosNaSessao(request.getSession(), usuarios);

        return "redirect:Lista/Usuarios";
    }

    @PostMapping("/Salvar")
    public String salvar(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");

        List<UsuarioModel> usuarios = UsuarioUtils.getListaDeUsuariosNaSessao(request.getSession());

        if (id == 0) {
            usuarios.add(new UsuarioModel(nome, getNextId(usuarios), cpf, telefone));
        } else {
            for (UsuarioModel usuario: usuarios) {
                if (usuario.getId() == id) {
                    usuario.setNome(nome);
                    break;
                }
            }
        }

        UsuarioUtils.setListaDeUsuariosNaSessao(request.getSession(), usuarios);

        return "redirect:Lista/Usuarios";
    }

    private int getNextId(List<UsuarioModel> usuarios) {
        int id = 0;

        for (UsuarioModel usuario: usuarios) {
            id = usuario.getId() > id ? usuario.getId() : id;
        }

        return  ++id;
    }
}
