package br.com.imobi.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.imobi.domain.model.Imovel;
import br.com.imobi.infrastructure.ImovelRepository;

@RestController
public class ImovelApplication {
	
	@Autowired
	private ImovelRepository repository;
	
	@PostMapping("/add")
	public String saveBook(@RequestBody Imovel imovel) {
		repository.save(imovel);
		return "Added book with id : " + imovel.getId();
	}

	@GetMapping("/findAll")
	public List<Imovel> getBooks() {
		return repository.findAll();
	}

	@GetMapping("/findAllBooks/{id}")
	public Optional<Imovel> getBook(@PathVariable Long id) {
		return repository.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		repository.deleteById(id);
		return "book deleted with id : " + id;
	}

}
