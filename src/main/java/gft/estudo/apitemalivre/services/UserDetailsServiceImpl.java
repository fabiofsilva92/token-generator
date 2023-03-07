package gft.estudo.apitemalivre.services;

import gft.estudo.apitemalivre.entities.Usuario;
import gft.estudo.apitemalivre.repositories.UsuarioRepository;
import gft.estudo.apitemalivre.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(s).orElseThrow(() -> new UsernameNotFoundException(s));

        return new UserSS(usuario.getId(), usuario.getLogin(), usuario.getPassword(), usuario.getRoles());
    }

}
