package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Role;
import org.example.travlingprojetsb.Repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Retrieve all roles
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    // Find roles by their IDs
    public List<Role> findRolesByIds(List<Long> roleIds) {
        return roleRepository.findAllById(roleIds);
    }

    // Find a role by its name
    public Optional<Role> findByName(String name) {
        return Optional.ofNullable(roleRepository.findByName(name));
    }
}
