package br.com.idolink.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ConfigNewsletterFormRequest;
import br.com.idolink.api.dto.response.ConfigNewsletterFormResponse;
import br.com.idolink.api.model.ConfigNewsletterForm;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.DataUserType;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class ConfigNewsletterFormMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
			
	public ConfigNewsletterFormResponse modelToResponse(ConfigNewsletterForm model) {
		
		ConfigNewsletterFormResponse response = mapper.map(model, ConfigNewsletterFormResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.NEWSLETTER);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));
		
		convertDataUserInResponse(response, model.getDatauser());
		
		if(model.getIcon() != null && model.getIcon() != 0) {
			response.setIcon(storageApi.findFileById(model.getIcon()));
		}
		
		
		return response;
	}
	
	public ConfigNewsletterForm requestToModel(ConfigNewsletterFormRequest request) {
		ConfigNewsletterForm configContactUs = mapper.map(request, ConfigNewsletterForm.class);
		convertRequestInDataUser(request, configContactUs);
		return configContactUs;
	}
	
	public List<ConfigNewsletterFormResponse> modelToResponseList(List<ConfigNewsletterForm> models) {
		
		List<ConfigNewsletterFormResponse> list = new ArrayList<>();
		for (ConfigNewsletterForm configContactUs : models) {
			list.add(modelToResponse(configContactUs));
		}
		
		return list;
	}
	
	
	private void convertDataUserInResponse(ConfigNewsletterFormResponse response, String dataUser) {
		
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
	
	private void convertRequestInDataUser(ConfigNewsletterFormRequest request, ConfigNewsletterForm config) {
		
		StringBuffer datauser = new StringBuffer();
		
		if(request.isEmail()) {
			datauser.append(DataUserType.EMAIL.getName()).append(";");
		}
		if(request.isName()) {
			datauser.append(DataUserType.NAME.getName()).append(";");
		}
		if(request.isPhone()) {
			datauser.append(DataUserType.PHONE.getName()).append(";");
		}
						
		config.setDatauser(datauser.toString());
		
	}

}

