package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
