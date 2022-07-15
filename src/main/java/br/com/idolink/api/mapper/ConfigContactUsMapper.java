package br.com.idolink.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ConfigContactUsRequest;
import br.com.idolink.api.dto.response.ConfigContactUsResponse;
import br.com.idolink.api.model.ConfigContactUs;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.DataUserType;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@SuppressWarnings("unused")
@Component
public class ConfigContactUsMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
			
	public ConfigContactUsResponse modelToResponse(ConfigContactUs model) {
		
		ConfigContactUsResponse response = mapper.map(model, ConfigContactUsResponse.class);

		Tool tool = toolRepository.findByCodName(ToolCodName.CONTACTUS);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));
		
		convertDataUserInResponse(response, model.getDatauser());
		return response;
		
	}
	
	public ConfigContactUs requestToModel(ConfigContactUsRequest request) {
		ConfigContactUs configContactUs = mapper.map(request, ConfigContactUs.class);
		convertRequestInDataUser(request, configContactUs);
		return configContactUs;
	}
	
	public List<ConfigContactUsResponse> modelToResponseList(List<ConfigContactUs> models) {
		
		List<ConfigContactUsResponse> list = new ArrayList<>();
		for (ConfigContactUs configContactUs : models) {
			list.add(modelToResponse(configContactUs));
		}
		
		return list;
	}
	
	
	private void convertDataUserInResponse(ConfigContactUsResponse response, String dataUser) {
		
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
			if(data.equals(DataUserType.TOPIC.getName())) {
				response.setTopic(true);
			}
			if(data.equals(DataUserType.MESSAGE.getName())) {
				response.setMessage(true);
			}
		}
	}
	
	private void convertRequestInDataUser(ConfigContactUsRequest request, ConfigContactUs config) {
		
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
		if(request.isTopic()) {
			datauser.append(DataUserType.TOPIC.getName()).append(";");
		}
		if(request.isMessage()) {
			datauser.append(DataUserType.MESSAGE.getName());
		}
				
		config.setDatauser(datauser.toString());
		
	}

}

