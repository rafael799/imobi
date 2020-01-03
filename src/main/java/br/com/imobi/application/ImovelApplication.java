package br.com.imobi.application;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobi.domain.model.Imovel;
import br.com.imobi.domain.service.ImovelService;

@RestController
@RequestMapping(value = "/imoveis")
public class ImovelApplication {

	@Autowired
	private ImovelService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Imovel save(@RequestBody @Valid Imovel imovel) {
		return service.save(imovel);
	}

	@GetMapping
	public List<Imovel> getAll() {
		return service.getAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		service.remove(id);
	}

	@PutMapping("/{id}")
	public Imovel update(@PathVariable Long id, @RequestBody @Valid Imovel imovel) {
		return service.update(id, imovel);
	}

	@GetMapping("/{id}")
	public Imovel getById(@PathVariable Long id) {
		return service.findOrNull(id);
	}

	@PatchMapping("/{id}")
	public Imovel updatePar(@PathVariable Long id, @RequestBody Map<String, Object> fields,HttpServletRequest request) {
		Imovel imovel = service.getById(id);
		service.merge(fields, imovel,request);
		return update(id, imovel);
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long id) {
		service.activate(id);
	}
	
	@DeleteMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long id) {
		service.inactivate(id);
	}


}
