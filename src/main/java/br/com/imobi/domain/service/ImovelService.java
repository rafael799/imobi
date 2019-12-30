package br.com.imobi.domain.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.imobi.domain.model.Imovel;
import br.com.imobi.infrastructure.ImovelRepository;

@Service
public class ImovelService {

	@Autowired
	private ImovelRepository repository;

	public Imovel save(Imovel imovel) {
		return repository.save(imovel);
	}

	public ResponseEntity<Imovel> remove(Long id) {
		try {

			Imovel imovel = repository.findById(id).get();
			repository.delete(imovel);
			return ResponseEntity.noContent().build();

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<Imovel> update(Long id, Imovel imovel) {
		try {
			Imovel imovelAtual = repository.findById(id).get();

			BeanUtils.copyProperties(imovel, imovelAtual, "id");
			imovelAtual = repository.save(imovelAtual);
			return ResponseEntity.ok(imovelAtual);

		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	public List<Imovel> getAll() {
		return repository.findAll();
	}

	public ResponseEntity<Imovel> getById(Long id) {
		try {
			Imovel imovel = repository.findById(id).get();
			return ResponseEntity.ok(imovel);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
