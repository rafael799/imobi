package br.com.imobi.domain.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.imobi.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document(collection = "locador")
public class Locador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull(groups = Groups.ContratoGroup.class)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.ContratoGroup.class)
	private Endereco endereco;

}
