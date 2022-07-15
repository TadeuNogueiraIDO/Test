package br.com.idolink.api.repository.impl;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.filter.ToolFilter;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.UserPlanPackage;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolFilterRepository;

@Repository
public class ToolFilterRepositoryImpl implements ToolFilterRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Tool> findAll(ToolFilter filter, Boolean allTools) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tool> criteria = builder.createQuery(Tool.class);
		Root<Tool> root = criteria.from(Tool.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isBlank(filter.getName())) {
			predicates.add(builder.like(builder.lower(root.get("filter")), "%" + filter.getName().toLowerCase() + "%"));
		}
		if(Objects.nonNull(filter.getAvailability())) {
			predicates.add(builder.equal(root.get("availability"), filter.getAvailability()));
		}
		if(!allTools) {
			//para ignorar ferramentas que nao podem ser listadas. Ex: logo bio
			predicates.add(builder.equal(root.get("isListable"), true));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Tool> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	@Override
	public List<Tool> findAll(ToolFilter filter) {
		return this.findAll(filter, false);
	}
	
	@Override
	public Tool findByCodName(ToolCodName codName){
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tool> criteria = builder.createQuery(Tool.class);
		Root<Tool> root = criteria.from(Tool.class);
		
		Predicate predicates = builder.equal(root.get("codName"), codName);
				
		criteria.where(predicates);
		TypedQuery<Tool> query = manager.createQuery(criteria);
		return query.getSingleResult();
	}

	@Override
	public List<UserPlanPackage> findToolInvoice(LocalDate date, Long userId, List<Long> idosId) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<UserPlanPackage> criteria = builder.createQuery(UserPlanPackage.class);
		Root<UserPlanPackage> root = criteria.from(UserPlanPackage.class);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(
				builder.equal(root.get("ido").get("business").get("user").get("id"), userId)
				);
		predicates.add(
						builder.between(root.get("expirationDate"), date.withDayOfMonth(1), date.with(TemporalAdjusters.lastDayOfMonth()))
					);
		if(Objects.nonNull(idosId)) {
			In<Long> ido = builder.in(root.get("ido").get("id"));
			idosId.forEach(i ->{
				ido.value(i);
			});
			predicates.add(ido);
		}
		criteria.where(predicates.toArray(new Predicate[0])).distinct(true);
		TypedQuery<UserPlanPackage> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	

}
