package Ejemplofront.front.webservicesclient;

import Ejemplofront.front.DTOs.LibroDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "libro-front-api-client",
        url = "${api.libros.base-url:http://localhost:8085/ejemplo/api}"
)
public interface LibroFrontApiClient {

    @GetMapping("/libros")
    List<LibroDto> listarLibros();

    @PostMapping("/libros")
    LibroDto guardarLibro(@RequestBody LibroDto libroDto);

    @DeleteMapping("/libros/{id}")
    void eliminarLibro(@PathVariable Long id);
}
