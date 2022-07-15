package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.LinkRequest;
import br.com.idolink.api.dto.response.LinkResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.LinkMapper;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Link;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.LinkRepository;
import br.com.idolink.api.service.ButtonAnimationService;
import br.com.idolink.api.service.LinkService;
import br.com.idolink.api.utils.IdoStringUtils;

@Service
public class LinkServiceImpl extends GenericToolsServiceImpl implements LinkService {

	@Autowired
	private LinkRepository repository;
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private ButtonAnimationService buttonAnimationService;

	@Autowired
	private LinkMapper mapper;
	
	@Override
	public List<LinkResponse> publicListByIdo(Long idoId) {

		Optional<Ido> ido =  idoRepository.findById(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		List<Link> model = repository.findByIdo(ido.get());
		return mapper.response(model);
	}
	
	@Override
	public List<LinkResponse> listByIdo(Long idoId) {

		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		List<Link> model = repository.findByIdo(ido.get());
		return mapper.response(model);
	}

	@Override
	public LinkResponse findById(Long id) {
		Optional<Link> model = repository.findById(id);
		validate(model, "Link", "api.error.link.not.found");

		return mapper.response(model.get());
	}

	@Override
	@Transactional
	public LinkResponse create(Long idoId, LinkRequest request) {
		
		request.setUrl(IdoStringUtils.validateLink(request.getUrl()));
		
		Link model = mapper.model(request);
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		model.setIdo(ido.get());
		
		if(request.getButtonsAnimation() != null) {
			model.setButtonsAnimation(buttonAnimationService.findListById(request.getButtonsAnimation()));
		}
		
		
		repository.save(model);
		
		super.createTools(ToolCodName.LINK, idoId, model.getId());
		return mapper.response(model);
	}

	@Override
	@Transactional
	public LinkResponse update(Long id, LinkRequest request) {

		request.setUrl(IdoStringUtils.validateLink(request.getUrl()));
		
		@SuppressWarnings("serial")
		Link model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Link", "api.error.link.not.found") {
				});
		BeanUtils.copyProperties(request, model, "id","dateModel", "ido");
			
		model.setButtonsAnimation(buttonAnimationService.findListById(request.getButtonsAnimation()));
		
		repository.save(model);
		return mapper.response(model);

	}

	@Transactional
	public void delete(Long id) {

		Optional<Link> model = repository.findById(id);
		validate(model,"Link", "api.error.link.not.found");
		
		try {
			
			repository.deleteById(id);
			repository.flush();
			deleteAssociationTools(model.get(), id);
			
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"Link", "api.error.ido.conflict");
		}
				
	}
			
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	@Transactional
	private void deleteAssociationTools(Link model, Long id) {
		Long idoId = model.getIdo().getId();
		Long qtd = repository.countByIdo(idoId);
		super.deleteTools(ToolCodName.LINK, idoId, id, qtd);
	}
		
}
