package br.com.imobi.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.imobi.domain.model.Locatario;

@Repository
public interface LocatarioRepository extends MongoRepository<Locatario, Long> {
	

}
