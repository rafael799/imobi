package br.com.imobi.application;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobi.domain.model.Contrato;
import br.com.imobi.domain.service.ContratoService;

@RestController
@RequestMapping(value = "/contratos")
public class ContratoApplication {

	@Autowired
	private ContratoService service;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Contrato save(@RequestBody @Valid Contrato contrato) {
		return service.save(contrato);
	}

	@GetMapping("/findAll")
	public List<Contrato> getAll() {
		return service.getAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		service.remove(id);
	}

	@PutMapping("/{id}")
	public Contrato update(@PathVariable Long id, @RequestBody @Valid Contrato contrato) {
		return service.update(id, contrato);
	}

	@GetMapping("/findById/{id}")
	public Contrato getById(@PathVariable Long id) {
		return service.findOrNull(id);
	}

}
