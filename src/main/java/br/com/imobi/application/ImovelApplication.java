package br.com.imobi.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Imovel save(@RequestBody Imovel imovel) {
		return service.save(imovel);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Imovel> remove(@PathVariable Long id) {
		return service.remove(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Imovel> update(@PathVariable Long id, @RequestBody Imovel imovel) {
		return service.update(id,imovel);
	}

	@GetMapping("/findAll")
	public List<Imovel> getAll() {
		return service.getAll();
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Imovel> getById(@PathVariable Long id) {
		return service.getById(id); 
	}

}
