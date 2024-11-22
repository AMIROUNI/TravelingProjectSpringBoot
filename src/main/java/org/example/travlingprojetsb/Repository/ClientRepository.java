package org.example.travlingprojetsb.Repository;

import org.example.travlingprojetsb.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long > {

}
