package gft.estudo.apitemalivre.security;

import gft.estudo.apitemalivre.dto.AutenticacaoDTO;
import gft.estudo.apitemalivre.dto.TokenDTO;
import gft.estudo.apitemalivre.repositories.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Date;

@Component
public class JWTUtil {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${apitemalivre.jwt.expiraton}")
    private String expiration;

    @Value("${apitemalivre.jwt.secret}")
    private String secret;

    @Value("${apitemalivre.jwt.issuer}")
    private String issuer;

    public TokenDTO autenticar(AutenticacaoDTO autenticacaoDTO) throws AuthenticationException {

        usuarioRepository.findByLogin(autenticacaoDTO.getLogin()).orElseThrow(()-> new RuntimeException("Usuário não cadastrado, impossível gerar token."));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(autenticacaoDTO.getLogin()
                        , autenticacaoDTO.getPassword()));


        String token = gerarToken(autenticacaoDTO.getLogin());

        return new TokenDTO(token);

    }

    public String gerarToken(String login) {

        return Jwts.builder()
                .setSubject(login)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .setIssuer(issuer)
                .compact();

//        UsuarioCustomUserDetails principal = (UsuarioCustomUserDetails) authentication.getPrincipal();
//
//        Date hoje = new Date();
//        Date expirar = new Date(hoje.getTime() + Long.parseLong(expiration));
//
//        Algorithm algorithm = criarAlgoritmo();
//
//        return JWT.create()
//                .withIssuer(issuer)
//                .withExpiresAt(expirar)
//                .withSubject(principal.getId().toString())
//                .sign(algorithm);
    }

    public boolean checkToken(String token) {
        Claims claims = getClaims(token);
        if(claims != null){
            String login = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if(login != null && expirationDate != null && now.before(expirationDate)){
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }
    }

    public String getLogin(String token) {
        Claims claims = getClaims(token);
        if(claims != null) {
            return claims.getSubject();
        }
        return null;
    }

//    public boolean verificaToken(String token){
//        try{
//            if(token == null) return false;
//
//            JWT.require(this.criarAlgoritmo())
//                    .withIssuer(issuer)
//                    .build().verify(token);
//
//            return true;
//
//        }catch (JWTVerificationException exception){
//            return false;
//        }
//    }
//
//    public Long retornarIdUsuario(String token){
//
//        String subject = JWT.require(this.criarAlgoritmo())
//                .withIssuer(issuer)
//                .build().verify(token).getSubject();
//
//        return Long.parseLong(subject);
//
//    }


}
