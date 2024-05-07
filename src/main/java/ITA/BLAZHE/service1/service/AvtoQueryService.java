package ITA.BLAZHE.service1.service;

import ITA.BLAZHE.service1.model.Avto;
import ITA.BLAZHE.service1.repository.AvtoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvtoQueryService {

    @Autowired
    private AvtoRepository avtoRepository;
    private static final Logger logger = LoggerFactory.getLogger(AvtoQueryService.class);

    public List<Avto> getAllAvto() {
        logger.info("Fetching all avtos");
        return avtoRepository.findAll();
    }

    public Avto getAvtoById(String id) {
        logger.info("Fetching avto with ID: {}");
        return avtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avto not found with id: " + id));
    }
}
