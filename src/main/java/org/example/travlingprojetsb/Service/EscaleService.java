package org.example.travlingprojetsb.Service;

import org.example.travlingprojetsb.Entity.Escale;
import org.example.travlingprojetsb.Repository.EscaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscaleService {

    @Autowired
    private EscaleRepository escaleRepository;

    public Escale addEscale(Escale escale) {
        return escaleRepository.save(escale);
    }

    public List<Escale> findEscalesByVolId(Long id) {
        return escaleRepository.findByVolId(id);
    }


    public  void  deleteEscale(Long id){
        escaleRepository.deleteById(id);
    }
}
