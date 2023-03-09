package gft.estudo.apitemalivre.controllers;

import gft.estudo.apitemalivre.dto.UsuarioDTO;
import gft.estudo.apitemalivre.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/test")
public class TestSecurityController {

    private UsuarioService usuarioService;

    public TestSecurityController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getUsuarios(){
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

}
