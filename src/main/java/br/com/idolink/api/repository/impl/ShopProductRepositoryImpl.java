package br.com.idolink.api.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.idolink.api.repository.ShopCategoryProductRepository;

public class ShopProductRepositoryImpl implements ShopCategoryProductRepository{

	@Autowired
	private JdbcTemplate template;

	@Override
	public void updateCategoryProducts(Long categoryId, Long newCategoryId) {
		template.update("UPDATE shop_product SET shop_category_id = " + newCategoryId
				+ " where shop_category_id = " + categoryId);

	}
}
