package br.com.imobi.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobi.domain.exception.contrato.ContratoNotFoundException;
import br.com.imobi.domain.exception.contrato.ContratoUseException;
import br.com.imobi.domain.model.Contrato;
import br.com.imobi.domain.model.Imovel;
import br.com.imobi.domain.model.Locador;
import br.com.imobi.domain.model.Locatario;
import br.com.imobi.domain.repository.ContratoRepository;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository ContratoRepository;
	
	@Autowired
	private ImovelService imovelService;
	
	@Autowired
	private LocatarioService locatarioService;
	
	@Autowired
	private LocadorService locadorService;

	@Transactional
	public Contrato save(Contrato contrato) {
		Imovel imovel = imovelService.findOrNull(contrato.getImovel().getCode());
		Locatario locatario = locatarioService.findOrNull(contrato.getLocatario().getId());
		Locador locador = locadorService.findOrNull(contrato.getLocador().getId());
		contrato.setImovel(imovel);
		contrato.setLocador(locador);
		contrato.setLocatario(locatario);
		return ContratoRepository.save(contrato);
	}

	@Transactional
	public Contrato update(Long id, Contrato contrato) {
		Contrato contratoAtual = findOrNull(id);
		BeanUtils.copyProperties(contrato, contratoAtual, "id");
		return ContratoRepository.save(contratoAtual);
	}

	public List<Contrato> getAll() {
		return ContratoRepository.findAll();
	}

	public Contrato getById(Long id) {
		return findOrNull(id);
	}

	@Transactional
	public void remove(Long id) {
		try {
			Contrato contrato = findOrNull(id);
			ContratoRepository.delete(contrato);
		} catch (DataIntegrityViolationException e) {
			throw new ContratoUseException(id);
		}

	}

	public Contrato findOrNull(Long id) {
		return ContratoRepository.findById(id).orElseThrow(() -> new ContratoNotFoundException(id));
	}


}
