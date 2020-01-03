package br.com.imobi.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobi.domain.exception.locador.LocadorNotFoundException;
import br.com.imobi.domain.exception.locador.LocadorUseException;
import br.com.imobi.domain.model.Locador;
import br.com.imobi.domain.repository.LocadorRepository;

@Service
public class LocadorService {

	@Autowired
	private LocadorRepository locadorRepository;
	
	@Transactional
	public Locador save(Locador locador) {
		return locadorRepository.save(locador);
	}

	@Transactional
	public Locador update(Long id, Locador locador) {
		Locador locadorAtual = findOrNull(id);
		BeanUtils.copyProperties(locador, locadorAtual, "id");
		return locadorRepository.save(locadorAtual);
	}

	public List<Locador> getAll() {
		return locadorRepository.findAll();
	}

	public Locador getById(Long id) {
		return findOrNull(id);
	}

	@Transactional
	public void remove(Long id) {
		try {
			Locador locador = findOrNull(id);
			locadorRepository.delete(locador);

		} catch (DataIntegrityViolationException e) {
			throw new LocadorUseException(id);

		}

	}

	public Locador findOrNull(Long id) {
		return locadorRepository.findById(id).orElseThrow(() -> new LocadorNotFoundException(id));
	}


}
