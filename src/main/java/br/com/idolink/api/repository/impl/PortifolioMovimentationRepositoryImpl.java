package br.com.idolink.api.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.idolink.api.filter.PortifolioMovimentationFilter;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.repository.PortifolioMovimentsRepository;
import br.com.idolink.api.utils.IdoStringUtils;

@Repository
public class PortifolioMovimentationRepositoryImpl implements PortifolioMovimentsRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<SingleQuickPay> findSingleQuickPay(Long userId, PortifolioMovimentationFilter filter,
			List<LocalDateTime> dates) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SingleQuickPay> criteria = builder.createQuery(SingleQuickPay.class);
		Root<SingleQuickPay> root = criteria.from(SingleQuickPay.class);

		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("user").get("id"), userId));

		predicates.add(builder.and(builder.equal(root.get("statusSeding"), QuickPaySedingStatus.DELIVERED), 
				builder.equal(root.get("statusPayment"), QuickPayPaymentStatus.PAID)));
		
		if(dates != null && dates.size()>0) {
			predicates.add(builder.between(root.get("dateModel").get("dt_created"), dates.get(0), dates.get(1)));
		}
		criteria.where(predicates.toArray(new Predicate[0])).distinct(false);
		TypedQuery<SingleQuickPay> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	@Override
	public List<ShopQuickPay> findShopQuickPay(Long userId, PortifolioMovimentationFilter filter,
			List<LocalDateTime> dates) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ShopQuickPay> criteria = builder.createQuery(ShopQuickPay.class);
		Root<ShopQuickPay> root = criteria.from(ShopQuickPay.class);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("user").get("id"), userId));
		
		Join<ShopQuickPay, Ido> tagJoin = root.joinList("products").join("shopProductVariation").join("shopProduct").join("shopCategory").join("shop").join("ido");
		
		Predicate statusSedingCanceled = builder.equal(root.get("statusSeding"), QuickPaySedingStatus.DELIVERED);
		Predicate statusPaymentCanceled = builder.equal(root.get("statusPayment"), QuickPayPaymentStatus.PAID);
		predicates.add(builder.and(statusSedingCanceled, statusPaymentCanceled));
		
		if(Objects.nonNull(filter.getIdoId())) {
			List<Long> idoIds = IdoStringUtils.convertStringLong(filter.getIdoId());
			In<Long> ido = builder.in(tagJoin.get("id"));
			idoIds.forEach(i ->{
				ido.value(i);
			});
			predicates.add(ido);
		}
		
		if(dates != null && dates.size()>0) {
			predicates.add(builder.between(root.get("dateModel").get("dt_created"), dates.get(0), dates.get(1)));
		}
		criteria.where(predicates.toArray(new Predicate[0])).distinct(false);
		TypedQuery<ShopQuickPay> query = manager.createQuery(criteria);
		System.out.println(query.getResultList());
		return query.getResultList();
	}
}
