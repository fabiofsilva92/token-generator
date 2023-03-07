package gft.estudo.apitemalivre.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gft.estudo.apitemalivre.entities.Usuario;
import gft.estudo.apitemalivre.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String user;
    @JsonIgnore
    private String password;
    private Set<RoleEnum> roles = new HashSet<>();

    public UsuarioDTO fromModel(Usuario usuario){
        return new UsuarioDTO(usuario.getLogin(), usuario.getPassword(), usuario.getRoles());
    }

}
