package br.com.idolink.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ShopRequest;
import br.com.idolink.api.dto.response.ActivityResponse;
import br.com.idolink.api.dto.response.ActivityShopResponse;
import br.com.idolink.api.dto.response.PersonalProductShop;
import br.com.idolink.api.dto.response.PersonalShopProfile;
import br.com.idolink.api.dto.response.ShopGenericResponse;
import br.com.idolink.api.dto.response.ShopResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.ActivityShop;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ActivityShopRepository;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.ActivityService;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class ShopMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ShopProductMapper productMapper;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private ActivityShopRepository activityShopRepository;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	public Shop requestToModel(ShopRequest request) {
		return mapper.map(request, Shop.class);
	}
	
	public ShopResponse response(Shop model) {
		
		ShopResponse response = mapper.map(model, ShopResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.SHOP);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));
		
		if(Objects.nonNull(model.getActivityShop())) {
			
			Optional<ActivityShop> activityShop = activityShopRepository.findById(model.getActivityShop().getId()); 
			response.setActivity(responseActivityShop(activityShop.get()));
		}
				
		try {
			if(Objects.nonNull(model.getStoreBanner())) {
				response.setStoreBanner(storageApi.findFileById(model.getStoreBanner()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "ShopBanner", "api.error.shop.image.inconsistency");
		}
		
		return response;
	}
	
	public ShopGenericResponse responseEspecific(Shop model) {

	     ShopGenericResponse response = ShopGenericResponse.builder().id(model.getId()).name(model.getName()).build();
					
			try {
				if(Objects.nonNull(model.getStoreBanner())) {
					response.setStoreBanner(storageApi.findFileById(model.getStoreBanner()));
				}
			} catch (Exception e) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "ShopBanner", "api.error.shop.image.inconsistency");
			}
			
			return response;
		}
	
	public List<ShopResponse> response(List<Shop> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
	
	public Shop model(ShopRequest request) {
		return mapper.map(request, Shop.class);
	}
	
	public PersonalShopProfile modelToPersonalShopProfile(Shop shop) {
		
		List<PersonalProductShop> products = new ArrayList<>();
		
		
		shop.getCategories().forEach(c ->{
			
			products.addAll(c.getProducts().stream().map(p -> productMapper.
					modelToPersonalProduct(p)).collect(Collectors.toList()));
		
			
		});
		
		PersonalShopProfile response = PersonalShopProfile.builder()
				.description(shop.getIdo().getDescription())
				.name(shop.getName())
				.shopProduct(products)
				.id(shop.getId())
				.link(shop.getIdo().getDomain())
				.build();
		
		if(shop.getStoreBanner() != null) {
			
			response.setStoreBanner(storageApi.findFileById(shop.getStoreBanner()));
		}
	
		return response;
	}
	
	
	
	
	private ActivityShopResponse responseActivityShop(ActivityShop model) {
		ActivityResponse activity = activityService.findById(model.getActivity().getId());
		ActivityShopResponse response = mapper.map(model, ActivityShopResponse.class);
		response.setTypeActivity(activity);
		return response;
	}
	
	
}
