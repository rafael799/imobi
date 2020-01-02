package br.com.imobi.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.imobi.domain.exception.contrato.ContratoNotFoundException;
import br.com.imobi.domain.exception.imovel.ImovelNotFoundException;
import br.com.imobi.domain.exception.imovel.ImovelUseException;
import br.com.imobi.domain.model.Locatario;
import br.com.imobi.domain.repository.LocatarioRepository;

@Service
public class LocatarioService {

	@Autowired
	private LocatarioRepository locatarioRepository;
	
	public Locatario save(Locatario locatario) {
		return locatarioRepository.save(locatario);
	}

	public Locatario update(Long id, Locatario locatario) {
		Locatario locatarioAtual = findOrNull(id);
		BeanUtils.copyProperties(locatario, locatarioAtual, "id");
		return locatarioRepository.save(locatarioAtual);
	}

	public List<Locatario> getAll() {
		return locatarioRepository.findAll();
	}

	public Locatario getById(Long id) {
		return locatarioRepository.findById(id).orElseThrow(() -> new ContratoNotFoundException(id));
	}

	public void remove(Long id) {
		try {
			Locatario locatario = findOrNull(id);
			locatarioRepository.delete(locatario);
		} catch (EmptyResultDataAccessException e) {
			throw new ImovelNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new ImovelUseException(id);
		}
	}

	public Locatario findOrNull(Long id) {
		return locatarioRepository.findById(id).orElseThrow(() -> new ContratoNotFoundException(id));
	}


}
