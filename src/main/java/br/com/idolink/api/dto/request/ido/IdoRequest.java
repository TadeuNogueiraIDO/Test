package br.com.idolink.api.dto.request.ido;

import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.idolink.api.dto.request.CategoryRequest;
import br.com.idolink.api.dto.request.CountryRequest;
import br.com.idolink.api.dto.request.FaqRequest;
import br.com.idolink.api.dto.request.LinkRequest;
import br.com.idolink.api.dto.request.ModelParamRequest;
import br.com.idolink.api.model.enums.IdoStatus;
import br.com.idolink.api.model.enums.WallpaperType;
import lombok.Data;

@Data
public class IdoRequest {
		
	@NotBlank
	private String name;

	private CountryRequest country;

	@NotBlank
	private String domain;

	@NotBlank
	private String description;
	
	private List<CategoryRequest> categories;

	private String position;

	private IdoStatus status;
		
	private List<ModelParamRequest> modelParams;
	
	private List<LinkRequest> links;
	
	private List<FaqRequest> faqs;
	
	private Integer numberVisitors;
	
	private WallpaperType wallpaperType;
	
	private Object wallpaper;
	
	private Long icon;
	
}
