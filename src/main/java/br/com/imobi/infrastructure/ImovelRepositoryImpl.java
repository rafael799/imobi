package br.com.imobi.infrastructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.imobi.domain.model.Imovel;
import br.com.imobi.domain.repository.ImovelRepositoryQueries;

@Repository
public class ImovelRepositoryImpl implements ImovelRepositoryQueries {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Imovel> findByAllFields(Imovel imovel) {
		Query query = new Query();
		addCriteria(imovel, query);
        return mongoTemplate.find(query, Imovel.class);
	}
	
	public void addCriteria(Imovel imovel,Query query) {
		if (StringUtils.hasText(imovel.getDescricao())) {
			query.addCriteria(Criteria.where("descricao").in(imovel.getDescricao()));
		}
	}

}
