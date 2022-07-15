package br.com.idolink.api.dto.response;

import java.util.ArrayList;
import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.request.ButtonAnimationRequest;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import br.com.idolink.api.model.enums.Typeicon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkResponse extends BaseToolResponse{

	private Long id;
	
	private String nameTool;
	
    private BlobFileResponse iconTool;

	private String title;

	private String subtitle;
	
	private Boolean activated;

	private BlobFileResponse icon;

    private String url;
	
	private Typeicon typeicon;

	@Builder.Default
	private List<ButtonAnimationRequest> buttonsAnimation = new ArrayList<>();
	
	
	

}
