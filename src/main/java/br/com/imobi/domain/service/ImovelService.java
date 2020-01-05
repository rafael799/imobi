package br.com.imobi.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.imobi.domain.exception.imovel.ImovelNotFoundException;
import br.com.imobi.domain.exception.imovel.ImovelUseException;
import br.com.imobi.domain.model.Imovel;
import br.com.imobi.domain.repository.ImovelRepository;

@Service
public class ImovelService {

	@Autowired
	private ImovelRepository repository;

	@Transactional
	public Imovel save(Imovel imovel) {
		return repository.save(imovel);
	}

	@Transactional
	public Imovel update(String code, Imovel imovel) {
		Imovel imovelAtual = findOrNull(code);
		BeanUtils.copyProperties(imovel, imovelAtual, "id");
		return repository.save(imovelAtual);
	}

	public List<Imovel> getAll() {
		return repository.findAll();
	}

	public Imovel getByCode(String code) {
		return findOrNull(code);
	}
	
	@Transactional
	public void remove(String code) {
		try {

			Imovel imovel = findOrNull(code);
			repository.delete(imovel);

		} catch (DataIntegrityViolationException e) {
			throw new ImovelUseException(code);

		}

	}
	
	@Transactional
	public void activate(String code) {
		Imovel imovel = findOrNull(code);
		imovel.activate();
		repository.save(imovel);
	}
	
	@Transactional
	public void inactivate(String code) {
		Imovel imovel = findOrNull(code);
		imovel.inactivate();
		repository.save(imovel);
	}
	
	@Transactional
	public void activate(List<String> codes) {
		codes.forEach(this::activate);
	}
	
	@Transactional
	public void inactivate(List<String> codes) {
		codes.forEach(this::inactivate);
	}

	public Imovel findOrNull(String code) {
		return repository.findBycode(code).orElseThrow(() -> new ImovelNotFoundException(code));
	}

	public void merge(Map<String, Object> fields, Imovel imovelUpdate,HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			Imovel imovelOrigin = objectMapper.convertValue(fields, Imovel.class);
	
			fields.forEach((nameProperties, valorProperties) -> {
				Field field = ReflectionUtils.findField(Imovel.class, nameProperties);
				field.setAccessible(true);
				Object novoValor = ReflectionUtils.getField(field, imovelOrigin);
				ReflectionUtils.setField(field, imovelUpdate, novoValor);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}

}
