package br.com.idolink.api.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.filter.PersonalHomeFilter;
import br.com.idolink.api.model.Category;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ShopProduct;
import br.com.idolink.api.repository.ShopProductFilterRepository;

@Repository
public class ShopProductFilterRepositoryImpl implements ShopProductFilterRepository{

	
	@Autowired
	private EntityManager manager;
	
	@Override
	public List<ShopProduct> findShopProductPersonal(PersonalHomeFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ShopProduct> criteria = builder.createQuery(ShopProduct.class);
		Root<ShopProduct> root = criteria.from(ShopProduct.class);
		
		ListJoin<Ido, Category> joinCategory = root.join("shopCategory").join("shop").join("ido").joinList("categories");
		
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getCategories() != null && filter.getCategories().size() > 0) {
			In<Long> category = builder.in(joinCategory.get("id"));
			filter.getCategories().forEach(c ->{
				category.value(c);					
			});
			predicates.add(category);
		}
		if(!StringUtils.isBlank(filter.getParam())) {
			predicates.add(
					builder.or(
							builder.like(root.get("name").as(String.class), "%" + filter.getParam() + "%"),
							builder.like(joinCategory.get("name").as(String.class), "%" + filter.getParam() + "%"),
							builder.like(root.get("shopCategory").get("shop").get("name").as(String.class), "%" + filter.getParam() + "%")
				));
		}
		predicates.add(
				builder.and(
						builder.isNull(root.get("dateModel").get("dt_deleted")),
						builder.isNull(joinCategory.get("dateModel").get("dt_deleted"))
						));
			
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<ShopProduct> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

}
