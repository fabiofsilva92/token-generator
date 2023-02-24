package gft.estudo.apitemalivre.dto.responses;

import gft.estudo.apitemalivre.entities.Role;
import gft.estudo.apitemalivre.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    public Long id;
    public String login;
    public Role role;

    public static UsuarioResponse fromUsuario(Usuario usuario){
        return new UsuarioResponse(usuario.getId(), usuario.getLogin(), usuario.getRole());
    }
}
