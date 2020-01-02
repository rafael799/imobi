package br.com.imobi.domain.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document(collection = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String UF;
	
	@NotNull
	private Integer numero;
	
	private Integer cep;

}
