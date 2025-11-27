package Ejemplofront.front.vistas;

import Ejemplofront.front.DTOs.PrestamoDto;
import Ejemplofront.front.webservicesclient.PrestamoApiClient;
import Ejemplofront.front.webservicesclient.UsuarioApiClient;
import Ejemplofront.front.webservicesclient.LibroApiClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PrestamoViewController {

    private final PrestamoApiClient prestamoApiClient;
    private final UsuarioApiClient usuarioApiClient;
    private final LibroApiClient libroApiClient;

    public PrestamoViewController(
            PrestamoApiClient prestamoApiClient,
            UsuarioApiClient usuarioApiClient,
            LibroApiClient libroApiClient) {

        this.prestamoApiClient = prestamoApiClient;
        this.usuarioApiClient = usuarioApiClient;
        this.libroApiClient = libroApiClient;
    }

    @GetMapping("/prestamos")
    public String listarPrestamos(Model model) {
        model.addAttribute("prestamos", prestamoApiClient.getPrestamos());
        return "listaPrestamos";
    }

    @GetMapping("/registroprestamo")
    public String mostrarForm(Model model) {
        model.addAttribute("prestamoDto", new PrestamoDto());
        model.addAttribute("usuarios", usuarioApiClient.getUsuarios());
        model.addAttribute("libros", libroApiClient.getLibros());
        return "registroPrestamo";
    }

    @PostMapping("/registroprestamo")
    public String guardarPrestamo(@ModelAttribute("prestamoDto") PrestamoDto prestamoDto) {
        prestamoApiClient.crearPrestamo(prestamoDto);
        return "redirect:/prestamos";
    }

    @GetMapping("/prestamos/eliminar/{id}")
    public String eliminarPrestamo(@PathVariable Long id) {
        prestamoApiClient.eliminarPrestamo(id);
        return "redirect:/prestamos";
    }
}
