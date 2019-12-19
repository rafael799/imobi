package br.com.imobi.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document(collection = "imovel")
public class Imovel {
	
	@Id
	@EqualsAndHashCode.Include
	private Long id;
	private String descricao;
	

}
