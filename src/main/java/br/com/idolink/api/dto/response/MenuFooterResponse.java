package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuFooterResponse extends BaseToolResponse{

	private Long id;
	
	private String nameTool;
	
    private BlobFileResponse iconTool;
			
	private Boolean activated;
	
	private BlobFileResponse logo;
	
	private Boolean unpinMenu;
	
	private Boolean activateFooter;
	
	private List<ItemMenuFooterResponse> itens;
	


}
