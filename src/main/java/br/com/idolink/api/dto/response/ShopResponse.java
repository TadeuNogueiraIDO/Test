package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import br.com.idolink.api.model.enums.FormatCardProduct;
import br.com.idolink.api.model.enums.FormatShowcase;
import br.com.idolink.api.model.enums.ShopStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopResponse extends BaseToolResponse{
	
	private Long id;
	
	private String nameTool;
	
    private BlobFileResponse iconTool;
	
	private Boolean activated;
	
	private ActivityShopResponse activity;
	
	private String name;

	private Boolean activateName;
	
	private BlobFileResponse storeBanner;
	
	private FormatShowcase formatShowcase;
	
	private FormatCardProduct formatCardProduct;
	
	private List<ShopCategorySimpleResponse> categories;
	
	private ShopStatus status;
	
	

}
