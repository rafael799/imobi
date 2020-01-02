package br.com.imobi.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.imobi.domain.exception.imovel.ImovelNotFoundException;
import br.com.imobi.domain.exception.imovel.ImovelUseException;
import br.com.imobi.domain.model.Imovel;
import br.com.imobi.domain.repository.ImovelRepository;

@Service
public class ImovelService {

	@Autowired
	private ImovelRepository repository;

	public Imovel save(Imovel imovel) {
		return repository.save(imovel);
	}

	public Imovel update(Long id, Imovel imovel) {
		Imovel imovelAtual = findOrNull(id);
		BeanUtils.copyProperties(imovel, imovelAtual, "id");
		return repository.save(imovelAtual);
	}

	public List<Imovel> getAll() {
		return repository.findAll();
	}

	public Imovel getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ImovelNotFoundException(id));
	}

	public void remove(Long id) {
		try {

			Imovel imovel = findOrNull(id);
			repository.delete(imovel);

		} catch (EmptyResultDataAccessException e) {
			throw new ImovelNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new ImovelUseException(id);

		}

	}

	public Imovel findOrNull(Long id) {
		return repository.findById(id).orElseThrow(() -> new ImovelNotFoundException(id));
	}

	public void merge(Map<String, Object> fields, Imovel imovelUpdate) {
		ObjectMapper objectMapper = new ObjectMapper();
		Imovel imovelOrigin = objectMapper.convertValue(fields, Imovel.class);

		fields.forEach((nameProperties, valorProperties) -> {
			Field field = ReflectionUtils.findField(Imovel.class, nameProperties);
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, imovelOrigin);
			ReflectionUtils.setField(field, imovelUpdate, novoValor);
		});
	}

}
