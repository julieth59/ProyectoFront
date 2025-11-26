package Ejemplofront.front.webservicesclient;

import Ejemplofront.front.DTOs.UsuarioDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class UsuarioApiClient {

    private final RestTemplate restTemplate;

    public UsuarioApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.usuarios.base-url:http://localhost:8085/ejemplo/api}")
    private String baseUrl;

    // =======================
    //   LISTAR USUARIOS
    // =======================
    public List<UsuarioDto> getUsuarios() {
        String url = baseUrl + "/usuarios";
        UsuarioDto[] usuariosArray = restTemplate.getForObject(url, UsuarioDto[].class);
        return List.of(usuariosArray);
    }

    // =======================
    //   CREAR USUARIO
    // =======================
    public UsuarioDto crearUsuario(UsuarioDto usuarioDto) {
        String url = baseUrl + "/usuarios";
        return restTemplate.postForObject(url, usuarioDto, UsuarioDto.class);
    }

    // =======================
    //   ELIMINAR USUARIO
    // =======================
    public void eliminarUsuario(Long id) {
        String url = baseUrl + "/usuarios/" + id;
        restTemplate.delete(url);
    }
}
