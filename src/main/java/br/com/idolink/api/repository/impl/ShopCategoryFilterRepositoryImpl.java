package br.com.idolink.api.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.idolink.api.filter.ShopCategoryStockFilter;
import br.com.idolink.api.model.ShopCategory;
import br.com.idolink.api.model.ShopProductVariation;
import br.com.idolink.api.repository.ShopCategoryFilterRepository;

public class ShopCategoryFilterRepositoryImpl implements ShopCategoryFilterRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<ShopCategory> findShopCategoryByShopId(Long shopId, ShopCategoryStockFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ShopCategory> criteria = builder.createQuery(ShopCategory.class);
		Root<ShopCategory> root = criteria.from(ShopCategory.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get("shop").get("id"), shopId));
		if(filter != null) {
		  if(filter.getHideCategoriesWithoutProducts() || filter.getHideLowStockProducts() || filter.getHideOutStock()) {
			ListJoin<ShopCategory, ShopProductVariation> joinVariation = root.join("products").joinList("variations");
			if(filter.getHideCategoriesWithoutProducts()) {
				predicates.add(builder.isNotEmpty(root.get("products")));
			}
			if(filter.getHideLowStockProducts()) {
				predicates.add(builder.greaterThanOrEqualTo(joinVariation.get("amount"), 4));
			}
			if(filter.getHideOutStock()) {
				predicates.add(builder.notEqual(joinVariation.get("amount"), 0));
			} 
			if(!filter.getHideCategoriesWithoutProducts()) {
				predicates.add(builder.isNotNull(root.get("id")));
			}
		  }
		}
		
		criteria.where(predicates.toArray(new Predicate[0])).distinct(true);
		TypedQuery<ShopCategory> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

}
