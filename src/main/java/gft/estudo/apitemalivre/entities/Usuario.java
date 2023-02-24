package gft.estudo.apitemalivre.entities;


import gft.estudo.apitemalivre.dto.UsuarioDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;


@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;

    @ManyToOne
    private Role role;

    public Usuario() {
    }

    public Usuario(Long id, String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public static Usuario standardFromDTO(UsuarioDTO usuarioDTO){
        return new Usuario(null, usuarioDTO.getUser(), new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()), null);
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
