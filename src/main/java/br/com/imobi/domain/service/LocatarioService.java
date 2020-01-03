package br.com.imobi.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobi.domain.exception.contrato.ContratoNotFoundException;
import br.com.imobi.domain.exception.locatario.LocatarioUseException;
import br.com.imobi.domain.model.Locatario;
import br.com.imobi.domain.repository.LocatarioRepository;

@Service
public class LocatarioService {

	@Autowired
	private LocatarioRepository locatarioRepository;
	
	@Transactional
	public Locatario save(Locatario locatario) {
		return locatarioRepository.save(locatario);
	}

	@Transactional
	public Locatario update(Long id, Locatario locatario) {
		Locatario locatarioAtual = findOrNull(id);
		BeanUtils.copyProperties(locatario, locatarioAtual, "id");
		return locatarioRepository.save(locatarioAtual);
	}

	public List<Locatario> getAll() {
		return locatarioRepository.findAll();
	}

	public Locatario getById(Long id) {
		return findOrNull(id);
	}

	@Transactional
	public void remove(Long id) {
		try {
			Locatario locatario = findOrNull(id);
			locatarioRepository.delete(locatario);

		} catch (DataIntegrityViolationException e) {
			throw new LocatarioUseException(id);
		}
	}

	public Locatario findOrNull(Long id) {
		return locatarioRepository.findById(id).orElseThrow(() -> new ContratoNotFoundException(id));
	}


}
