package org.example.travlingprojetsb.Service;


import org.example.travlingprojetsb.Entity.Vol;
import org.example.travlingprojetsb.Repository.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolService {
    @Autowired
    VolRepository volRepository;


    public Vol addVol(Vol vol){
        return  volRepository.save(vol);
    }

    public void  deleteVolById(Long id){
        volRepository.deleteById(id);
    }


    public  Vol findVolById(Long id){
        return  volRepository.findById(id).get();
    }

}
