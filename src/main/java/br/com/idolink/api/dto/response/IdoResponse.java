package br.com.idolink.api.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.model.Language;
import br.com.idolink.api.model.enums.IdoStatus;
import br.com.idolink.api.model.enums.PredefinedModelsTemplate;
import br.com.idolink.api.model.enums.WallpaperType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdoResponse {

	private Long id;
	
	private String name;

	private CountryResponse country;

	private String domain;
	
	private Language language;
	
	private TimezoneResponse timezone;
	
	private boolean hideIdolink;
	
	private boolean sensitiveContent;
	
	private IdoSharingThumbResponse sharingThumb;

	private String description;

	private List<CategoryResponse> categories = new ArrayList<>();

	private String position;

	private IdoStatus status;
	
	private List<IdoModelParamResponse> modelParams = new ArrayList<>();
	
	private Integer numberVisitors;
		
	private BlobFileResponse icon;
	
	private WallpaperType wallpaperType;
	
	private Object wallpaper;
	
	private PredefinedModelsTemplate template; 
	
	private AppearanceButtonResponse appearanceButtons;
	
	private AppearanceCardsResponse appearanceCard;
	
	private AppearanceTextResponse appearanceText;
	
	//TOOLS
	private IdoContactResponse contacts; 
	
	private BusinessHourResponse businessHour;
	
	private ConfigContactUsResponse ConfigContactUs;
	
	private ConfigFaqResponse configFaq;
	
	private List<ImageBannerResponse> imageBanners;
	
	private List<VideoBannerResponse> videoBanners;
	
	private List<LinkResponse> links;
		
	private ConfigNewsletterFormResponse configNewsletterForm;
	
	private List<AttachedPdfResponse> attachedPdfs;
		
	private ShopResponse shop;
			
	private List<ImageCarouselResponse> imagesCarousel;	

	private List<EmbedHtmlResponse> embedHtmls;

	private LogoBioResponse logoBio;
	
	private MenuFooterResponse menuFooter;
		
	//ENDTOOLS
	
	@JsonIgnore
	private Long idoPublished;
	
	
}
