package br.com.idolink.api.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idolink.api.dto.request.BusinessHourRequest;
import br.com.idolink.api.dto.response.BusinessHourResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.businesshour.BusinessHourMapper;
import br.com.idolink.api.model.BusinessHour;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.BusinessHourAlternativeScheduleRepository;
import br.com.idolink.api.repository.BusinessHourDayRepository;
import br.com.idolink.api.repository.BusinessHourRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.service.BusinessHourService;

@Service
public class BusinessHourServiceImpl extends GenericToolsServiceImpl implements BusinessHourService {

	@Autowired
	private BusinessHourRepository repository;
	
	@Autowired
	private BusinessHourDayRepository dayRepository;
	
	@Autowired
	private BusinessHourAlternativeScheduleRepository alternativeScheduleRepository;

	@Autowired
	private BusinessHourMapper mapper;
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Override
	public Page<BusinessHourResponse> list(Pageable pageable) {

		Page<BusinessHour> model = repository.findAll(pageable);

		return model.map(m -> mapper.response(m));
	}
	
	@Override
	public BusinessHourResponse publicFindByIdo(Long idoId, Boolean validation) {
		
	Optional<Ido> ido =  idoRepository.findById(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		BusinessHour hour = repository.findByIdo(idoId);

		if(Objects.isNull(hour)) {
			if(validation) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Horário de funcionamento", "api.error.bussiness.hour.not.found");
			}
			return null;
		}	
		return mapper.response(hour);
	}
	
	@Override
	public BusinessHourResponse findByIdo(Long idoid) {
		return this.findByIdo(idoid, true);
	} 

	@Override
	public BusinessHourResponse findByIdo(Long idoId, Boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		BusinessHour hour = repository.findByIdo(idoId);

		if(Objects.isNull(hour)) {
			if(validation) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Horário de funcionamento", "api.error.bussiness.hour.not.found");
			}
			return null;
		}	
		
		return mapper.response(hour);
	}

	@Override
	public BusinessHourResponse findById(Long id) {
		BusinessHour model = validate(id);
		return mapper.response(model);
	}

	@Transactional
	@Override
	public BusinessHourResponse create(Long idIdo, BusinessHourRequest request) {

		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idIdo);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		BusinessHour hour = repository.findByIdo(idIdo);
		
		if(Objects.nonNull(hour)) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Ido", "api.error.bussiness.hour.ido.conflict ");
		}
		
		BusinessHour model = mapper.create(request, ido.get());
		model = repository.save(model);
		
		createTools(ToolCodName.BUSINESSHOUR, idIdo, model.getId());
				
		return mapper.response(model);
	}

	@Transactional
	@Override
	public BusinessHourResponse update(Long id, BusinessHourRequest request) {

		BusinessHour modelOld = validate(id);
				
		//limpa os escalonamentos antigos
		if(Objects.nonNull(modelOld.getAlternativeSchedules()) && !modelOld.getAlternativeSchedules().isEmpty()) {
			alternativeScheduleRepository.deleteAll(modelOld.getAlternativeSchedules());
		}
			
		if(Objects.nonNull(modelOld.getDays()) && !modelOld.getDays().isEmpty()) {
			dayRepository.deleteAll(modelOld.getDays());
		}
		
		BusinessHour modelRequest = mapper.create(request, modelOld.getIdo());
		BeanUtils.copyProperties(modelRequest, modelOld, "id","ido", "dateModel");
				
		modelOld = repository.save(modelOld);
		return mapper.response(modelOld);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		
		BusinessHour model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"Ido", "api.error.bussiness.hour.not.found"));
		try {
			
			repository.delete(model);
			repository.flush();
			Long idoId = model.getIdo().getId();
			
			deleteTools(ToolCodName.BUSINESSHOUR, idoId, id, null);
							
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Ido", "api.error.bussiness.hour.conflict");
		}
	}

	private BusinessHour validate(Long id) {
		Optional<BusinessHour> model = repository.findById(id);

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "BusinessHour", "api.error.bussiness.hour.not.found");
		}
		return model.get();
	}

}
