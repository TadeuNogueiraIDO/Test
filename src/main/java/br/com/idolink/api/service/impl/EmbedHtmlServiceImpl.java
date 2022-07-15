package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.EmbedHtmlRequest;
import br.com.idolink.api.dto.response.EmbedHtmlResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.EmbedHtmlMapper;
import br.com.idolink.api.model.EmbedHtml;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.EmbedHtmlRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.service.EmbedHtmlService;

@Service
public class EmbedHtmlServiceImpl extends GenericToolsServiceImpl implements EmbedHtmlService {

	@Autowired
	private EmbedHtmlRepository repository;
	
	@Autowired
	private IdoRepository idoRepository;
		
	@Autowired
	private EmbedHtmlMapper mapper;
	
	@Override
	public List<EmbedHtmlResponse> publicListByIdo(Long idoId) {

		Optional<Ido> ido =  idoRepository.findById(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		List<EmbedHtml> model = repository.findByIdo(ido.get());
		return mapper.response(model);
	}
	
	@Override
	public List<EmbedHtmlResponse> listByIdo(Long idoId) {

		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		List<EmbedHtml> model = repository.findByIdo(ido.get());
		return mapper.response(model);
	}

	@Override
	public EmbedHtmlResponse findById(Long id) {
		Optional<EmbedHtml> model = repository.findById(id);
		validate(model, "Embed Html", "api.error.embed.html.not.found");

		return mapper.response(model.get());
	}

	@Override
	@Transactional
	public EmbedHtmlResponse create(Long idoId, EmbedHtmlRequest request) {
						
		EmbedHtml model = mapper.model(request);
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		model.setIdo(ido.get());
						
		repository.save(model);
		
		Long qtd = repository.countByIdo(idoId);
		super.createTools(ToolCodName.HTML, idoId, model.getId(), qtd);
		return mapper.response(model);
	}

	@Override
	@Transactional
	public EmbedHtmlResponse update(Long id, EmbedHtmlRequest request) {

		@SuppressWarnings("serial")
		EmbedHtml model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "EmbedHtml","api.error.embed.html.not.found") {
				});
		BeanUtils.copyProperties(request, model, "id","dateModel", "ido");
						
		repository.save(model);
		return mapper.response(model);

	}

	@Transactional
	public void delete(Long id) {

		Optional<EmbedHtml> model = repository.findById(id);
		validate(model,"Embed Html", "api.error.embed.html.not.found");
		
		try {
			
			repository.deleteById(id);
			repository.flush();
			deleteAssociationTools(model.get(), id);
			
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"EmbedHtml", "api.error.embed.html.conflict");
		}
				
	}
			
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	
	@Transactional
	private void deleteAssociationTools(EmbedHtml model, Long id) {
		Long idoId = model.getIdo().getId();
		Long qtd = repository.countByIdo(idoId);
		super.deleteTools(ToolCodName.HTML, idoId, id, qtd);
	}
		
}
