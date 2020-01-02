package br.com.imobi.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.imobi.domain.exception.imovel.ImovelNotFoundException;
import br.com.imobi.domain.exception.imovel.ImovelUseException;
import br.com.imobi.domain.exception.locador.LocadorNotFoundException;
import br.com.imobi.domain.model.Locador;
import br.com.imobi.domain.repository.LocadorRepository;

@Service
public class LocadorService {

	@Autowired
	private LocadorRepository locadorRepository;
	
	public Locador save(Locador locador) {
		return locadorRepository.save(locador);
	}

	public Locador update(Long id, Locador locador) {
		Locador locadorAtual = findOrNull(id);
		BeanUtils.copyProperties(locador, locadorAtual, "id");
		return locadorRepository.save(locadorAtual);
	}

	public List<Locador> getAll() {
		return locadorRepository.findAll();
	}

	public Locador getById(Long id) {
		return locadorRepository.findById(id).orElseThrow(() -> new LocadorNotFoundException(id));
	}

	public void remove(Long id) {
		try {
			Locador locador = findOrNull(id);
			locadorRepository.delete(locador);
		} catch (EmptyResultDataAccessException e) {
			throw new ImovelNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new ImovelUseException(id);

		}

	}

	public Locador findOrNull(Long id) {
		return locadorRepository.findById(id).orElseThrow(() -> new LocadorNotFoundException(id));
	}


}
