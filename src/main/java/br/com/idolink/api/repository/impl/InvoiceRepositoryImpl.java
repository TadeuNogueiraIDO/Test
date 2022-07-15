package br.com.idolink.api.repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.idolink.api.filter.InvoiceFilter;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Invoice;
import br.com.idolink.api.repository.InvoiceRepositoryFilter;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepositoryFilter {


	@PersistenceContext
	private EntityManager manager;

	@Override
	public Invoice find(Long userId, InvoiceFilter filter, LocalDate date) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Invoice> criteria = builder.createQuery(Invoice.class);
		Root<Invoice> root = criteria.from(Invoice.class);

		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("user").get("id"), userId));
		
		predicates.add(builder.equal(root.get("year"), filter.getYear()));
		predicates.add(builder.equal(root.get("month"), filter.getMonth()));
		
		
		if(Objects.nonNull(filter.getIdoIds())) {
			List<Long> idoIds = Arrays.asList(filter.getIdoIds().split(",")).stream().map(i -> Long.valueOf(i)).collect(Collectors.toList());
			ListJoin<Invoice, Ido> joinIdo = root.join("user").join("business").joinList("idos");
			In<Long> ido = builder.in(joinIdo.get("id"));
			idoIds.forEach(i ->{
				ido.value(i);
			});
			predicates.add(ido);
		}
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Invoice> query = manager.createQuery(criteria.distinct(true));
		List<Invoice> response = query.getResultList();
		return response.size() != 0 ? response.get(0) : null;
	}
	
}
