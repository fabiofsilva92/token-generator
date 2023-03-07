package gft.estudo.apitemalivre.entities;


import gft.estudo.apitemalivre.dto.UsuarioDTO;
import gft.estudo.apitemalivre.enums.RoleEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="TB_ROLES")
    private Set<Integer> roles = new HashSet<>();

    public Usuario() {
        addRole(RoleEnum.USUARIO);
    }

    public Usuario(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        addRole(RoleEnum.USUARIO);
    }

    public static Usuario standardFromDTO(UsuarioDTO usuarioDTO){
        return new Usuario(null, usuarioDTO.getUser(), new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEnum> getRoles() {
        return roles.stream().map(x -> RoleEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void setRole(Set<RoleEnum> role) {
        this.roles = roles;
    }

    public void addRole(RoleEnum roleEnum){
        roles.add(roleEnum.getCod());
    }
}
