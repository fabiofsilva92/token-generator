package gft.estudo.apitemalivre.controllers;

import gft.estudo.apitemalivre.dto.AutenticacaoDTO;
import gft.estudo.apitemalivre.dto.TokenDTO;
import gft.estudo.apitemalivre.dto.UsuarioDTO;
import gft.estudo.apitemalivre.dto.responses.UsuarioResponse;
import gft.estudo.apitemalivre.security.JWTUtil;
import gft.estudo.apitemalivre.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("v1/auth")
public class AutenticacaoController {

    private JWTUtil JWTUtil;

    private UsuarioService usuarioService;

    public AutenticacaoController(JWTUtil JWTUtil, UsuarioService usuarioService) {
        this.JWTUtil = JWTUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO autenticacaoDTO){

        try{
            return ResponseEntity.ok(JWTUtil.autenticar(autenticacaoDTO));
        }catch (AuthenticationException ae){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/createuser")
    public ResponseEntity<UsuarioResponse> createuser(@RequestBody UsuarioDTO usuario){
        return ResponseEntity.ok(usuarioService.createUser(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getUsuarios(){
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }
}
