package gft.estudo.apitemalivre.services;

import gft.estudo.apitemalivre.dto.UsuarioDTO;
import gft.estudo.apitemalivre.dto.responses.UsuarioResponse;
import gft.estudo.apitemalivre.entities.Usuario;
import gft.estudo.apitemalivre.entities.UsuarioCustomUserDetails;
import gft.estudo.apitemalivre.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleService roleService;

    public Usuario buscarPorLogin(String login){
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);

        if(usuario.isEmpty()){
            throw new RuntimeException("Usuário não encontrado");
        }

        return usuario.get();
    }


    public Usuario buscarUsuarioPorID(Long idUsuario) {
        Optional<Usuario> byId = usuarioRepository.findById(idUsuario);
        if(byId.isEmpty()) throw new RuntimeException("Usuário não encontrado no findbyid");

        return byId.get();
    }

    public UsuarioResponse createUser(UsuarioDTO usuario){
        Usuario usuario1 = Usuario.standardFromDTO(usuario);
        usuario1.setRole(roleService.getRoleById(2l));
        return UsuarioResponse.fromUsuario(usuarioRepository.save(usuario1));
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return new UsuarioCustomUserDetails(buscarPorLogin(login));
    }

}
