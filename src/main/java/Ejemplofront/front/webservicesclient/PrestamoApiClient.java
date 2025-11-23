package Ejemplofront.front.webservicesclient;

import Ejemplofront.front.DTOs.PrestamoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name= "prestamo-api-client",url="${api.Prestamo.base-url:http://localhost:8085/ejemplo/api}")
public interface PrestamoApiClient {

    @GetMapping("/prestamos")
    List<PrestamoDto> getPrestamos();

    @PostMapping("/prestamos")
    PrestamoDto crearPrestamo(@RequestBody PrestamoDto prestamo);

    @DeleteMapping("/prestamos/{id}")
    void eliminarPrestamo(@PathVariable Long id);

}
