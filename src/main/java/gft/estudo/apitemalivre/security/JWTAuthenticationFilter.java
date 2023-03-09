package gft.estudo.apitemalivre.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gft.estudo.apitemalivre.dto.AutenticacaoDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            AutenticacaoDTO autenticacaoDTO = new ObjectMapper()
                    .readValue(request.getInputStream(), AutenticacaoDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(autenticacaoDTO.getLogin(), autenticacaoDTO.getPassword(), new ArrayList<>());
            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String login = ((UserSS) authResult.getPrincipal()).getLogin();
        String token = jwtUtil.gerarToken(login);
        response.addHeader("Authorization", "Bearer "+token);
        Map<String, String> accessToken = new HashMap<>();

        accessToken.put("access_token", token);
        accessToken.put("message", "Authentication successful");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(accessToken));
//        accessToken.put("acess_token", token);
//        response.getWriter().write(accessToken.toString());
//        response.getWriter().print(objToJson(accessToken));
    }

    private static String objToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.out.println("Error when convert Obj to JSON:"+e.getMessage());
        }
        return "";
    }
}
