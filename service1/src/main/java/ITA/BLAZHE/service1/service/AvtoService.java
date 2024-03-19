package ITA.BLAZHE.service1.service;

import ITA.BLAZHE.service1.model.Avto;
import ITA.BLAZHE.service1.repository.AvtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class AvtoService {

    @Autowired
    private AvtoRepository avtoRepository;
    private static final Logger logger = LoggerFactory.getLogger(AvtoService.class);

    public List<Avto> findAll() {
        logger.info("Fetching all avtos");
        return avtoRepository.findAll();
    }

    public Optional<Avto> findById(String id) {
        logger.info("Fetching avto with id: {}", id);
        return avtoRepository.findById(id);
    }

    public Avto save(Avto avto) {
        logger.info("Saving new avto: {}", avto);
        return avtoRepository.save(avto);
    }

    public Avto update(String id, Avto updatedAvto) {
        logger.info("Updating avto with id: {}", id);
        return avtoRepository.findById(id)
                .map(avto -> {
                    logger.info("Found avto with id: {}, updating...", id);
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
                    logger.info("Avto with id: {} not found. Creating new avto...", id);
                    updatedAvto.setId(id);
                    return avtoRepository.save(updatedAvto);
                });
    }

    public void deleteById(String id) {
        logger.info("Deleting avto with id: {}", id);
        avtoRepository.deleteById(id);
    }
}
