package gft.estudo.apitemalivre.services;

import gft.estudo.apitemalivre.dto.UsuarioDTO;
import gft.estudo.apitemalivre.dto.responses.UsuarioResponse;
import gft.estudo.apitemalivre.entities.Usuario;
import gft.estudo.apitemalivre.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService{

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

    public List<UsuarioDTO> getUsuarios(){
        List<UsuarioDTO> collect = usuarioRepository.findAll().stream().map(u -> new UsuarioDTO().fromModel(u)).collect(Collectors.toList());
        return collect;
    }


    public Usuario buscarUsuarioPorID(Long idUsuario) {
        Optional<Usuario> byId = usuarioRepository.findById(idUsuario);
        if(byId.isEmpty()) throw new RuntimeException("Usuário não encontrado no findbyid");

        return byId.get();
    }

    public UsuarioResponse createUser(UsuarioDTO usuario){
        Usuario usuario1 = Usuario.standardFromDTO(usuario);
        return UsuarioResponse.fromUsuario(usuarioRepository.save(usuario1));
    }


}
