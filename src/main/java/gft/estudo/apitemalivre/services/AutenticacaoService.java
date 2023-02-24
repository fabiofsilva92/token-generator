package gft.estudo.apitemalivre.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import gft.estudo.apitemalivre.dto.AutenticacaoDTO;
import gft.estudo.apitemalivre.dto.TokenDTO;
import gft.estudo.apitemalivre.entities.Usuario;
import gft.estudo.apitemalivre.entities.UsuarioCustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Date;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${apitemalivre.jwt.expiraton}")
    private String expiration;

    @Value("${apitemalivre.jwt.secret}")
    private String secret;

    @Value("${apitemalivre.jwt.issuer}")
    private String issuer;

    public TokenDTO autenticar(AutenticacaoDTO autenticacaoDTO) throws AuthenticationException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(autenticacaoDTO.getLogin()
                        , autenticacaoDTO.getPassword()));

        String token = gerarToken(authentication);

        return new TokenDTO(token);

    }

    private Algorithm criarAlgoritmo(){
        return Algorithm.HMAC256(secret);
    }

    private String gerarToken(Authentication authentication) {

        UsuarioCustomUserDetails principal = (UsuarioCustomUserDetails) authentication.getPrincipal();

        Date hoje = new Date();
        Date expirar = new Date(hoje.getTime() + Long.parseLong(expiration));

        Algorithm algorithm = criarAlgoritmo();

        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(expirar)
                .withSubject(principal.getUsuario().getId().toString())
                .sign(algorithm);
    }

    public boolean verificaToken(String token){
        try{
            if(token == null) return false;

            JWT.require(this.criarAlgoritmo())
                    .withIssuer(issuer)
                    .build().verify(token);

            return true;

        }catch (JWTVerificationException exception){
            return false;
        }
    }

    public Long retornarIdUsuario(String token){

        String subject = JWT.require(this.criarAlgoritmo())
                .withIssuer(issuer)
                .build().verify(token).getSubject();

        return Long.parseLong(subject);

    }


}
