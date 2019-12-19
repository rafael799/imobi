package br.com.imobi.infrastructure;

import br.com.imobi.domain.model.Imovel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImovelRepository extends MongoRepository<Imovel, Long> {

}
