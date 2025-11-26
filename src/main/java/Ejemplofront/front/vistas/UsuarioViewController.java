package Ejemplofront.front.vistas;

import Ejemplofront.front.DTOs.UsuarioDto;
import Ejemplofront.front.webservicesclient.UsuarioApiClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/registrousuario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuarioDto", new UsuarioDto());
        return "registroUsuario";
    }

    @PostMapping("/registrousuario")
    public String guardarUsuario(@ModelAttribute("usuarioDto") UsuarioDto usuarioDto) {
        usuarioApiClient.crearUsuario(usuarioDto);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioApiClient.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
}
