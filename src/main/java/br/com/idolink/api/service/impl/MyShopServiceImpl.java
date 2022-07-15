package br.com.idolink.api.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.MyShopResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ShopCategory;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.MyShopProject;
import br.com.idolink.api.repository.MyShopRepository;
import br.com.idolink.api.repository.ShopCategoryRepository;
import br.com.idolink.api.service.MyShopService;

@Service
public class MyShopServiceImpl implements MyShopService{

	@Autowired
	private MyShopRepository repository;

	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private ShopCategoryRepository shopCategoryRepository;

	@Override
	public MyShopResponse dashboardMyShop (Long idoId, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		MyShopProject myshop = repository.dashboardMyShop(idoId);
		
		MyShopResponse response = new MyShopResponse(myshop.getTotalProducts(), myshop.getCategories(), myshop.getLowStock(), myshop.getWithoutStock());

		
		return response;
	}
	
	@Transactional
	@Override
	public void hideCategory(Long id) {
		ShopCategory model = shopCategoryRepository.findById(id).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "ShopCategory", "api.error.shop.category.not.found"));

		if (model.getHideCategory()) {
			model.setHideCategory(false);
		} else {
			model.setHideCategory(true);
		}

		model = shopCategoryRepository.save(model);
	}

}
