package Ejemplofront.front.vistas;

import Ejemplofront.front.webservicesclient.UsuarioApiClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioViewController {

    private final UsuarioApiClient usuarioApiClient;

    public UsuarioViewController(UsuarioApiClient usuarioApiClient) {
        this.usuarioApiClient = usuarioApiClient;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioApiClient.getUsuarios());
        return "listaUsuarios";
    }

}
