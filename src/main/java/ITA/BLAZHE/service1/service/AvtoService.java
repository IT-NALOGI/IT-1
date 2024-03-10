package ITA.BLAZHE.service1.service;


import ITA.BLAZHE.service1.model.Avto;
import ITA.BLAZHE.service1.repository.AvtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvtoService {

    @Autowired
    private AvtoRepository avtoRepository;

    public List<Avto> findAll() {
        return avtoRepository.findAll();
    }

    public Optional<Avto> findById(String id) {
        return avtoRepository.findById(id);
    }

    public Avto save(Avto avto) {
        return avtoRepository.save(avto);
    }

    public Avto update(String id, Avto updatedAvto) {
        return avtoRepository.findById(id)
                .map(avto -> {
                    avto.setZnamka(updatedAvto.getZnamka());
                    avto.setModel(updatedAvto.getModel());
                    avto.setLetoIzdelave(updatedAvto.getLetoIzdelave());
                    avto.setTipGorivo(updatedAvto.getTipGorivo());
                    avto.setRegistracija(updatedAvto.getRegistracija());
                    avto.setPrvaRegistracija(updatedAvto.getPrvaRegistracija());
                    avto.setRegistracijaDo(updatedAvto.getRegistracijaDo());
                    return avtoRepository.save(avto);
                })
                .orElseGet(() -> {
                    updatedAvto.setId(id);
                    return avtoRepository.save(updatedAvto);
                });
    }


    public void deleteById(String id) {
        avtoRepository.deleteById(id);
    }
}

