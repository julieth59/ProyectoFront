package Ejemplofront.front.webservicesclient;

import Ejemplofront.front.DTOs.LibroDto;
import Ejemplofront.front.entities.Libro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name= "libro-api-client",url="${api.Libro.base-url:http://localhost:8085/ejemplo/api}")
public interface LibroApiClient {

    @GetMapping("/libros")
    List<LibroDto> getLibros();

    @PostMapping("/libros")
    LibroDto crearLibro(@RequestBody LibroDto libro);
}
