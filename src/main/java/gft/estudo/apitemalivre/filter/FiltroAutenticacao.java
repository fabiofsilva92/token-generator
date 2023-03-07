//package gft.estudo.apitemalivre.filter;
//
//import gft.estudo.apitemalivre.entities.Usuario;
//import gft.estudo.apitemalivre.security.UserSS;
//import gft.estudo.apitemalivre.security.JWTUtil;
//import gft.estudo.apitemalivre.services.UsuarioService;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class FiltroAutenticacao extends OncePerRequestFilter {
//
//
//    private JWTUtil JWTUtil;
//
//    private UserDetailsService usuarioService;
//
//    public FiltroAutenticacao(JWTUtil JWTUtil, UsuarioService usuarioService) {
//        this.JWTUtil = JWTUtil;
//        this.usuarioService = usuarioService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String header = request.getHeader("Authorization");
//        String token = null;
//        if(header != null && header.startsWith("Bearer ")){
//            token = header.substring(7, header.length());
//        }
//
//        if(JWTUtil.verificaToken(token)){
//            Long idUsuario = JWTUtil.retornarIdUsuario(token);
//            Usuario usuario = usuarioService.buscarUsuarioPorID(idUsuario);
//            SecurityContextHolder
//                    .getContext()
//                    .setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, new UserSS(usuario).getAuthorities()));
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
