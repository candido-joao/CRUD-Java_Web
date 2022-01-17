package com.example.demo.Controlles;
import com.example.demo.Utils.UsuarioUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("Usuario/Lista")
public class UsuarioListaController {
    @RequestMapping("/Usuarios")
    public String getFormListaUsuarios(Model model, HttpServletRequest request) {
        model.addAttribute("usuarios", UsuarioUtils.getListaDeUsuariosNaSessao(request.getSession()));

        return "UsuarioLista";
    }
}
