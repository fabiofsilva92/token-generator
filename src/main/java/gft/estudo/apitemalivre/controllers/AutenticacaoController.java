package gft.estudo.apitemalivre.controllers;

import gft.estudo.apitemalivre.dto.AutenticacaoDTO;
import gft.estudo.apitemalivre.dto.TokenDTO;
import gft.estudo.apitemalivre.dto.UsuarioDTO;
import gft.estudo.apitemalivre.dto.responses.UsuarioResponse;
import gft.estudo.apitemalivre.entities.Usuario;
import gft.estudo.apitemalivre.services.AutenticacaoService;
import gft.estudo.apitemalivre.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("v1/auth")
public class AutenticacaoController {

    private AutenticacaoService autenticacaoService;

    private UsuarioService usuarioService;

    public AutenticacaoController(AutenticacaoService autenticacaoService, UsuarioService usuarioService) {
        this.autenticacaoService = autenticacaoService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO autenticacaoDTO){

        try{
            return ResponseEntity.ok(autenticacaoService.autenticar(autenticacaoDTO));
        }catch (AuthenticationException ae){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/createuser")
    public ResponseEntity<UsuarioResponse> createuser(UsuarioDTO usuario){
        return ResponseEntity.ok(usuarioService.createUser(usuario));
    }
}
