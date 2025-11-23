package Ejemplofront.front.vistas;

import Ejemplofront.front.DTOs.LibroDto;
import Ejemplofront.front.webservicesclient.LibroApiClient; // 👈 tu Feign client existente
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LibroViewController {
    private final LibroApiClient libroApiClient;

    public LibroViewController(LibroApiClient libroApiClient) {
        this.libroApiClient = libroApiClient;
    }

    @GetMapping("/registrolibro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("libroDto", new LibroDto());
        return "registroLibro"; // -> templates/registroLibro.html
    }

    @PostMapping("/registrolibro")
    public String guardarLibro(@ModelAttribute("libroDto") LibroDto libroDto, Model model) {
        System.out.println("entrando al guardado del libro");
        System.out.println("titulo " + libroDto.getTitulo());
        System.out.println("autor " + libroDto.getAutor());

        LibroDto creado = libroApiClient.crearLibro(libroDto);

        System.out.println("⬅ Libro creado en backend: " + creado);

        model.addAttribute("libroRegistrado", creado);

        return "libroRegistrado";
    }
    @GetMapping("/libros")
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroApiClient.getLibros());
        return "listaLibros"; // -> templates/listaLibros.html
    }
    @GetMapping("/libros/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroApiClient.eliminarLibro(id);
        return "redirect:/libros";
    }


}
