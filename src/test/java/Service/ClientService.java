package Service;


import Entity.Client;
import Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;


    public Client addClient(Client client){
        return  clientRepository.save(client);
    }

    public void  deleteClientById(Long id){
        clientRepository.deleteById(id);
    }


    public  Client findClientById(Long id){
        return  clientRepository.findById(id).get();
    }

}
