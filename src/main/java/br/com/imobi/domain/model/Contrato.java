package br.com.imobi.domain.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.imobi.core.validation.Groups;

import javax.validation.groups.Default;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document(collection = "imovel")
public class Contrato implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	private String descricao;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.ContratoGroup.class)
	private Imovel imovel;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.ContratoGroup.class)
	private Locador locador;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.ContratoGroup.class)
	private Locatario locatario;

}
