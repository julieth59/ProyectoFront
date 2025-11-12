package Ejemplofront.front.controller;

import Ejemplofront.front.DTOs.LibroDto;
import Ejemplofront.front.webservicesclient.LibroApiClient;
import Ejemplofront.front.webservicesclient.UsuarioApiClient;
import Ejemplofront.front.DTOs.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.List;

@RestController
public class PruebaClients {

    @Autowired
    private UsuarioApiClient usuarioApiClient;

    @Autowired
    private LibroApiClient libroApiClient;

    @GetMapping("/prueba/usuarios")
    public List<UsuarioDto> getUsuarios() {
        return usuarioApiClient.getUsuarios();
    }

    public List<LibroDto> getLibros() {
        return libroApiClient.getLibros();
    }
}
