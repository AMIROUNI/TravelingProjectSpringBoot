package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Client;
import org.example.travlingprojetsb.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
    public  Client updateClinet(Client client){
        return   clientRepository.saveAndFlush(client);
    }
}
