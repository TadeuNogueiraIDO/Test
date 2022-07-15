package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.ItemMenuFooterRequest;
import br.com.idolink.api.dto.request.MenuFooterRequest;
import br.com.idolink.api.dto.request.UpdateMenuFooterRequest;
import br.com.idolink.api.dto.response.ItemMenuFooterResponse;
import br.com.idolink.api.dto.response.MenuFooterResponse;
import br.com.idolink.api.dto.response.ToolsIdoReponse;
import br.com.idolink.api.dto.response.UpdateMenuFooterResponse;

public interface MenuFooterService {
	
	List<ToolsIdoReponse> findToolByIdo(Long idoId);

	MenuFooterResponse findByIdo(Long idoId, boolean validation);

	MenuFooterResponse publicFindByIdo(Long idoId, boolean validation);
	
	MenuFooterResponse findByIdo(Long idoid);
	
	MenuFooterResponse publicFindByIdo(Long idoid); 

	MenuFooterResponse findById(Long id);

	MenuFooterResponse create(MenuFooterRequest request, Long idIdo);

	ItemMenuFooterResponse hideItem(Long itemId, Long menuId);
	
	ItemMenuFooterResponse addItem(ItemMenuFooterRequest request, Long menuId);

	UpdateMenuFooterResponse updateMenuFooter(Long id, UpdateMenuFooterRequest request);

	ItemMenuFooterResponse updateItemMenuFooter(Long id, Long menu, ItemMenuFooterRequest request);
	
	void delete(Long id);

	void deleteItemMenuFooter(Long itemId, Long menuId);

	MenuFooterResponse update(MenuFooterRequest request, Long idMenu);
	
}
