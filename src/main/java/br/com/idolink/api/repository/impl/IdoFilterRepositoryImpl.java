package br.com.idolink.api.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.model.Business;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.User;
import br.com.idolink.api.repository.BusinessRepository;
import br.com.idolink.api.repository.IdoFilterRepository;
import br.com.idolink.api.repository.UserRepository;

@Repository
public class IdoFilterRepositoryImpl implements IdoFilterRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;
	
	@Autowired
	private BusinessRepository businessRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Optional<Ido> findByIdUserFilter(Long idoId){
		
		try {
			Long userId =  idoLinkSecurity.getUsuarioId();
			
			Optional<User> user = userRepository.findById(userId);
			Business business = businessRepository.findByUser(user.get());  
					
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Ido> criteria = builder.createQuery(Ido.class);
			Root<Ido> root = criteria.from(Ido.class);
			
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(builder.equal(root.get("id"), idoId));
			predicates.add(builder.equal(root.get("business"), business));
			criteria.where(predicates.toArray(new Predicate[0]));
			
			TypedQuery<Ido> query = manager.createQuery(criteria);
				
			return Optional.of(query.getSingleResult());
		}catch (NoResultException e) {
			return Optional.empty();
		}
	}
	
}
