package ITA.BLAZHE.service1.repository;


import ITA.BLAZHE.service1.model.Avto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvtoRepository extends MongoRepository<Avto, String> {
}

