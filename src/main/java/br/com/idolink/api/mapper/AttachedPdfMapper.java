package br.com.idolink.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.AttachedPdfRequest;
import br.com.idolink.api.dto.response.AttachedPdfResponse;
import br.com.idolink.api.model.AttachedPdf;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.DataUserType;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class AttachedPdfMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	public AttachedPdfResponse modelToResponse(AttachedPdf model) {
		
		AttachedPdfResponse response = mapper.map(model, AttachedPdfResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.PDF);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));
	
		convertDataUserInResponse(response, model.getDataUser());
		
		if(Objects.nonNull(model.getIcon()) && model.getIcon() != 0) {
			response.setIcon(storageApi.findFileById(model.getIcon()));
		}
		
		if(Objects.nonNull(model.getPdf()) && model.getPdf() != 0) {
			response.setPdf(storageApi.findFileById(model.getPdf()));
		}
		
		if(Objects.nonNull(model.getBanner()) && model.getBanner() != 0) {
			response.setBanner(storageApi.findFileById(model.getBanner()));
		}
		
		return response;
	}
	
	public AttachedPdf requestToModel(AttachedPdfRequest request) {
		AttachedPdf attachedPdf = mapper.map(request, AttachedPdf.class);
		convertRequestInDataUser(request, attachedPdf);
		return attachedPdf;
	}
	
	public List<AttachedPdfResponse> modelToResponseList(List<AttachedPdf> models) {
		
		List<AttachedPdfResponse> list = new ArrayList<>();
		for (AttachedPdf attachedPdf : models) {
			list.add(modelToResponse(attachedPdf));
		}
		
		return list;
	}
	
	
	private void convertDataUserInResponse(AttachedPdfResponse response, String dataUser) {
		
		String[]splitDataUser = dataUser.split(";");
		
		for (String data : splitDataUser) {
			
			if(data.equals(DataUserType.EMAIL.getName())) {
				response.setEmail(true);
			}
			if(data.equals(DataUserType.NAME.getName())) {
				response.setName(true);
			}
			if(data.equals(DataUserType.PHONE.getName())) {
				response.setPhone(true);
			}
		}
	}
	
	private void convertRequestInDataUser(AttachedPdfRequest request, AttachedPdf attachedPdf) {
		
		StringBuffer dataUser = new StringBuffer();
		
		if(request.isEmail()) {
			dataUser.append(DataUserType.EMAIL.getName()).append(";");
		}
		if(request.isName()) {
			dataUser.append(DataUserType.NAME.getName()).append(";");
		}
		if(request.isPhone()) {
			dataUser.append(DataUserType.PHONE.getName());
		}
		
		attachedPdf.setDataUser(dataUser.toString());
		
	}
	
	
	
	
	
	
}
