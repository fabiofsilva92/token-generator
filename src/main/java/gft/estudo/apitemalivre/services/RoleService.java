package gft.estudo.apitemalivre.services;

import gft.estudo.apitemalivre.entities.Role;
import gft.estudo.apitemalivre.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role getRoleById(Long id){
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role não encontrada"));
    }

}
