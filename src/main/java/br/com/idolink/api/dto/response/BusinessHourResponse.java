package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import br.com.idolink.api.model.enums.BusinessHourType;
import br.com.idolink.api.model.enums.TimeFormat;
import br.com.idolink.api.model.enums.Typeicon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessHourResponse extends BaseToolResponse{

	private Boolean activated;
	
	private Long id;
	
    private String nameTool;
	
    private BlobFileResponse iconTool;
	
	private String title;
	
	private BusinessHourType type;
	
	private List<BusinessHourDayResponse> days;
	
	private List<BusinessHourAlternateScheduleResponse> alternativeSchedules;
	
	private TimeFormat timeFormat;
	
	private BlobFileResponse icon;
	
	private Typeicon typeicon;
	

	
}
