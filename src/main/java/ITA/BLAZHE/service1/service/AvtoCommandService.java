package ITA.BLAZHE.service1.service;

import ITA.BLAZHE.service1.model.Avto;
import ITA.BLAZHE.service1.repository.AvtoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvtoCommandService {

    @Autowired
    private AvtoRepository avtoRepository;
    private static final Logger logger = LoggerFactory.getLogger(AvtoCommandService.class);

    public Avto createAvto(Avto avto) {
        logger.info("Creating new avto: {}");
        return avtoRepository.save(avto);
    }

    public Avto updateAvto(String id, Avto updatedAvto) {
        logger.info("Updating avto with ID: {}");
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
                .orElseThrow(() -> new RuntimeException("Avto not found with id: " + id));
    }

    public void deleteAvto(String id) {
        logger.info("Deleting avto with ID: {}");
        avtoRepository.deleteById(id);
    }
}
