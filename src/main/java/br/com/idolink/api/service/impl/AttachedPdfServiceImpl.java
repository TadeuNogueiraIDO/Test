package br.com.idolink.api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.AttachedPdfLeadsRequest;
import br.com.idolink.api.dto.request.AttachedPdfRequest;
import br.com.idolink.api.dto.response.AttachedPdfLeadsResponse;
import br.com.idolink.api.dto.response.AttachedPdfResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.AttachedPdfLeadsMapper;
import br.com.idolink.api.mapper.AttachedPdfMapper;
import br.com.idolink.api.model.AttachedPdf;
import br.com.idolink.api.model.AttachedPdfLeads;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Notification;
import br.com.idolink.api.model.NotificationSetting;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.enums.DisplayFormPdf;
import br.com.idolink.api.model.enums.NotificationType;
import br.com.idolink.api.model.enums.StatusLeads;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.AttachedPdfLeadsRepository;
import br.com.idolink.api.repository.AttachedPdfRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.NotificationSettingRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.AttachedPdfService;
import br.com.idolink.api.service.MessagePropertieService;
import br.com.idolink.api.service.NotificationService;

@Service
public class AttachedPdfServiceImpl extends GenericToolsServiceImpl implements AttachedPdfService {

	@Autowired
	private AttachedPdfRepository repository;

	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private NotificationSettingRepository notificationServiceRepository;

	@Autowired
	private AttachedPdfMapper mapper;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AttachedPdfLeadsMapper leadsMapper;

	@Autowired
	private AttachedPdfLeadsRepository leadsRepository;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private MessagePropertieService messagePropertieService;

	@Override
	public List<AttachedPdfResponse> publicFindByIdo(Long idoId) {

		Optional<Ido> ido = idoRepository.findById(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		List<AttachedPdf> model = repository.findByIdo(ido.get());
		return mapper.modelToResponseList(model);
	}

	@Override
	public List<AttachedPdfResponse> findByIdo(Long idoId) {

		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		List<AttachedPdf> model = repository.findByIdo(ido.get());
		return mapper.modelToResponseList(model);
	}

	@Override
	public AttachedPdfResponse findById(Long id) {
		Optional<AttachedPdf> model = repository.findById(id);
		validate(model, "Pdf", "api.error.pdf.not.found");

		return mapper.modelToResponse(model.get());
	}

	@Override
	@Transactional
	public AttachedPdfResponse save(Long idoId, AttachedPdfRequest request) {
		AttachedPdf model = mapper.requestToModel(request);

		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		if (!request.getDisplayForm().equals(DisplayFormPdf.BANNER)) {
			model.setBanner(null);
		}

		model.setIdo(ido.get());
		model = repository.save(model);

		Long qtd = repository.countByIdo(idoId);
		super.createTools(ToolCodName.PDF, idoId, model.getId(), qtd);

		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public AttachedPdfResponse update(Long id, AttachedPdfRequest request) {

		AttachedPdf model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "pdf", "api.error.pdf.not.found"));

		if (!request.getDisplayForm().equals(DisplayFormPdf.BANNER)) {
			model.setBanner(null);
		}

		AttachedPdf attachedPdf = mapper.requestToModel(request);
		BeanUtils.copyProperties(attachedPdf, model, "id", "dateModel", "ido");
		model = repository.save(model);
		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		AttachedPdf model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "pdf", "api.error.pdf.not.found"));
		try {

			Long idIdo = model.getIdo().getId();
			repository.delete(model);
			repository.flush();
			avaliableAssociateIdoTool(idIdo, id);

		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "pdf", "api.error.pdf.conflict");
		}

	}

	@Override
	@Transactional
	public AttachedPdfLeadsResponse saveLeads(Long idPdf, AttachedPdfLeadsRequest request) {

		Optional<AttachedPdf> model = repository.findById(idPdf);
				 
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		AttachedPdfLeads leads = leadsMapper.requestToModel(request);
		leads.setAttachedPdf(model.get());

		leads.setStatusLeads(StatusLeads.NOTREAD);
				
		Optional<User> user = userRepository.findByIdo(model.get().getIdo().getId());
		if(user.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "User","api.error.user.not.found");
		}
		leadsRepository.save(leads);

		createNotification(user.get().getId(), leads.getId());

		return leadsMapper.response(leads);

	}

	@Override
	public AttachedPdfLeadsResponse findLeadsById(Long id) {
		Optional<AttachedPdfLeads> model = leadsRepository.findById(id);
		validate(model, "Leads", "api.error.pdf.lead.not.found");

		return leadsMapper.response(model.get());
	}

	@Override
	public List<AttachedPdfLeadsResponse> findLeadsByAttachedPdf(Long attachedPdfId) {

		Optional<AttachedPdf> pdf = repository.findById(attachedPdfId);
		validate(pdf, "Pdf", "api.error.pdf.not.found");

		List<AttachedPdfLeads> model = leadsRepository.findByAttachedPdf(pdf.get());

		return leadsMapper.modelToResponse(model);
	}

	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

	@Transactional
	private void avaliableAssociateIdoTool(Long idIdo, Long id) {
		Long qtd = repository.countByIdo(idIdo);
		super.deleteTools(ToolCodName.PDF, idIdo, id, qtd);
	}
	
	private void createNotification(Long userId, Long id) {
		
        Optional<User> user = userRepository.findById(userId);
        
        NotificationSetting settingNotification = notificationServiceRepository.findBySettingUserId(user.get().getId());
	
		if(settingNotification.getRequests()) {
			
		Notification notification =	Notification.builder()
		.title(messagePropertieService.getMessageAttributeByUser("api.notification.user.title.pdf.lead", userId))
		.message(messagePropertieService.getMessageAttributeByUser("api.notification.user.leads", userId))
		.icon("56c33952-dda2-4e40-a0ba-ba2855ae7242")
		.user(user.get())
		.read(false)
		.creationDate(LocalDate.now())
		.typeNotification(NotificationType.PDFLEAD).build();
		
		
		notificationService.create(notification, id.toString());
		}
		
		
	}
		

}
