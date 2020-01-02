package br.com.imobi.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.imobi.domain.model.Imovel;

@Repository
public interface ImovelRepository extends MongoRepository<Imovel, Long>, ImovelRepositoryQueries {
	
	List<Imovel> findBydescricao(String descricao);
	
	Imovel findFirstBydescricao(String descricao);
	
	List<Imovel> findBydescricaoContaining(String descricao);
	
	@Query("from Imovel where descricao like %:descricao%")
	List<Imovel> findBydescricaoLike(String descricao);

}
