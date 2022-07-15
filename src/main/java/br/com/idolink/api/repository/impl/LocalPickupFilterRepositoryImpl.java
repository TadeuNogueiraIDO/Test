package br.com.idolink.api.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.LocalPickup;
import br.com.idolink.api.repository.LocalPickupFilterRepository;

@Repository
public class LocalPickupFilterRepositoryImpl implements LocalPickupFilterRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<LocalPickup> findByShippingSettings(Long shippingSettingsId, Boolean isEnabled){
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<LocalPickup> criteria = builder.createQuery(LocalPickup.class);
		Root<LocalPickup> root = criteria.from(LocalPickup.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("shippingSettings"), shippingSettingsId));
		
		if(Objects.nonNull(isEnabled)) {
			predicates.add(builder.equal(root.get("isEnabled"), isEnabled));
		}
			
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<LocalPickup> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	

}
