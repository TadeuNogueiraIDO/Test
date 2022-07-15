package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.ShopRequest;
import br.com.idolink.api.dto.request.UpdateFormatCardShopRequest;
import br.com.idolink.api.dto.response.PersonalShopProfile;
import br.com.idolink.api.dto.response.ShopGenericResponse;
import br.com.idolink.api.dto.response.ShopResponse;

public interface ShopService {
	
	ShopResponse create(Long idIdo, ShopRequest request);

	ShopResponse update(Long id, ShopRequest request);
	
	ShopResponse updateFormatCardShop(Long id, UpdateFormatCardShopRequest request);

	void delete(Long id);

	ShopResponse findById(Long id);

	ShopResponse findByIdo(Long idoId);
	
	List<ShopResponse> findAll();

	ShopResponse findByIdo(Long idoId, boolean validation);

	List<ShopResponse> findByUser(Long userId);
	
	ShopResponse publicFindByIdo(Long idoId);
	
	ShopResponse publicFindByIdo(Long idoId, boolean validation);
	
	List<ShopGenericResponse> findAllShopProfile(String Filter);
	
	PersonalShopProfile findProfileShop(Long shopId);

	ShopResponse updateStatus(Long idShop, Boolean status);

}
