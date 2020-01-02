package br.com.imobi.domain.repository;

import java.util.List;

import br.com.imobi.domain.model.Imovel;

public interface ImovelRepositoryQueries {
	List<Imovel> findByAllFields(Imovel imovel);
}
