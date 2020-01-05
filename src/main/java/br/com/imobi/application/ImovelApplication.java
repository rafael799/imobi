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

	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable String code) {
		service.remove(code);
	}

	@PutMapping("/{code}")
	public Imovel update(@PathVariable String code, @RequestBody @Valid Imovel imovel) {
		return service.update(code, imovel);
	}

	@GetMapping("/{code}")
	public Imovel getByCode(@PathVariable String code) {
		return service.findOrNull(code);
	}

	@PatchMapping("/{code}")
	public Imovel updatePar(@PathVariable String code, @RequestBody Map<String, Object> fields,
			HttpServletRequest request) {
		Imovel imovel = service.getByCode(code);
		service.merge(fields, imovel, request);
		return update(code, imovel);
	}

	@PutMapping("/{code}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable String code) {
		service.activate(code);
	}

	@DeleteMapping("/{code}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable String code) {
		service.inactivate(code);
	}

	@PutMapping("/actives")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<String> codes) {
		service.activate(codes);
	}

	@DeleteMapping("/actives")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<String> codes) {
		service.inactivate(codes);
	}

}
