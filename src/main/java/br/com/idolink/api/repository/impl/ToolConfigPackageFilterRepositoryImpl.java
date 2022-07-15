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

import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ToolConfigPackage;
import br.com.idolink.api.model.UserPlanPackage;
import br.com.idolink.api.model.enums.PlanPackagePaymentStatus;
import br.com.idolink.api.repository.ToolConfigPackageFilterRepository;

@Repository
public class ToolConfigPackageFilterRepositoryImpl implements ToolConfigPackageFilterRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<ToolConfigPackage> findByIdo(Long idoId, PlanPackagePaymentStatus status) {
			
		CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ToolConfigPackage> criteria = builder.createQuery(ToolConfigPackage.class);
        Root<ToolConfigPackage> toolConfigPackageRoot = criteria.from(ToolConfigPackage.class);
        
        ListJoin<ToolConfigPackage, UserPlanPackage> join = toolConfigPackageRoot.joinList("packages")
        		.joinList("userPlanPackages");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(join.get("paymentStatus"), status));
        predicates.add(builder.equal(join.get("ido").get("id"), idoId));
        
        criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<ToolConfigPackage> query = manager.createQuery(criteria);
        
		return query.getResultList();

	}
	
	

}
