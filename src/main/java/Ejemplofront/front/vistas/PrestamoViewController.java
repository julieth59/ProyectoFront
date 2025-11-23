package Ejemplofront.front.vistas;

import Ejemplofront.front.webservicesclient.PrestamoApiClient;
import Ejemplofront.front.webservicesclient.UsuarioApiClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrestamoViewController {

    private final PrestamoApiClient prestamoApiClient;

    public PrestamoViewController(PrestamoApiClient prestamoApiClient) {
        this.prestamoApiClient = prestamoApiClient;
    }

    @GetMapping("/prestamos")
    public String listarPrestamos(Model model) {
        model.addAttribute("prestamos", prestamoApiClient.getPrestamos());
        return "listaPrestamos";
    }
}
