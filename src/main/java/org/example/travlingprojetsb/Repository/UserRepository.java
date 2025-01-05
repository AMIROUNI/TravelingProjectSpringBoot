package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
