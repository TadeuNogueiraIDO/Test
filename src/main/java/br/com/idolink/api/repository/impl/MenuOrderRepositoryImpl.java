package br.com.idolink.api.repository.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.filter.MenuOrderFilter;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.ShopQuickPayProduct;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.enums.OrderTypeStatus;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.repository.MenuOrderRepository;
import br.com.idolink.api.utils.IdoStringUtils;

@Repository
public class MenuOrderRepositoryImpl implements MenuOrderRepository{

	@PersistenceContext
	private EntityManager manager;
		
	@Override
	public List<SingleQuickPay> findSingleQuiqkPay(Long userId, MenuOrderFilter filter, List<LocalDateTime> dates) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SingleQuickPay> criteria = builder.createQuery(SingleQuickPay.class);
		Root<SingleQuickPay> root = criteria.from(SingleQuickPay.class);

		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("user").get("id"), userId));
		
		if(Objects.nonNull(filter.getOrderTypeStatus())) {
			Predicate statusSeding = null;
			Predicate statusPayment = null;
			
			if(filter.getOrderTypeStatus().equals(OrderTypeStatus.OPEN)) {
				statusSeding = builder.notEqual(root.get("statusSeding"), QuickPaySedingStatus.DELIVERED);
				Predicate statusSedingCanceled = builder.notEqual(root.get("statusSeding"), QuickPaySedingStatus.SENDCANCELED);
				statusPayment= builder.notEqual(root.get("statusPayment"), QuickPayPaymentStatus.PAID);
				Predicate statusPaymentCanceled = builder.notEqual(root.get("statusPayment"), QuickPayPaymentStatus.PAYMENTCANCELED);
				predicates.add(builder.and(builder.or(statusSeding, statusPayment), statusSedingCanceled, statusPaymentCanceled));
			}else {
				statusSeding = builder.equal(root.get("statusSeding"), QuickPaySedingStatus.DELIVERED);
				statusPayment= builder.equal(root.get("statusPayment"), QuickPayPaymentStatus.PAID);
				Predicate statusSedingCanceled = builder.equal(root.get("statusSeding"), QuickPaySedingStatus.SENDCANCELED);
				Predicate statusPaymentCanceled = builder.equal(root.get("statusPayment"), QuickPayPaymentStatus.PAYMENTCANCELED);
				predicates.add(builder.or(builder.and(statusPayment, statusSeding), builder.or(statusPaymentCanceled, statusSedingCanceled)));
			}
		}
		
		if(!StringUtils.isBlank(filter.getNameIdo()) || Objects.nonNull(filter.getIdoId())) {
			
			ListJoin<SingleQuickPay, Ido> tagJoin = root.join("user").join("business").joinList("idos");
			
			if(!StringUtils.isBlank(filter.getNameIdo())) {
				predicates.add(builder.like(tagJoin.get("name"), filter.getNameIdo()));
			}
			
			if(Objects.nonNull(filter.getIdoId())) {
				
				List<Long> idoIds = IdoStringUtils.convertStringLong(filter.getIdoId());			
				In<Long> ido = builder.in(tagJoin.get("id"));
				idoIds.forEach(i ->{
					ido.value(i);
				});
				predicates.add(ido);
			}
		}
		
		if(Objects.nonNull(filter.getOrderNumber())) {
			predicates.add(builder.equal(root.get("orderNumber"), filter.getOrderNumber()));
		}
		if(Objects.nonNull(filter.getStatusSeding())) {
			predicates.add(builder.equal(root.get("statusSeding"), filter.getStatusSeding()));
		}
		if(Objects.nonNull(filter.getStatusPayment())) {
			predicates.add(builder.equal(root.get("statusPayment"), filter.getStatusPayment()));
		}
		if(dates != null && dates.size()>0) {
			predicates.add(builder.between(root.get("dateModel").get("dt_created"), dates.get(0), dates.get(1)));
		}
		
		criteria.where(predicates.toArray(new Predicate[0])).distinct(true);
		criteria.orderBy(builder.desc(root.get("id")));
		TypedQuery<SingleQuickPay> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	@Override
	public List<ShopQuickPay> findShopQuiqkPay(Long userId, MenuOrderFilter filter, List<LocalDateTime> dates){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ShopQuickPay> criteria = builder.createQuery(ShopQuickPay.class);
		Root<ShopQuickPay> root = criteria.from(ShopQuickPay.class);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("user").get("id"), userId));
		
		Join<ShopQuickPay, Ido> tagJoin = root.joinList("products").join("shopProductVariation")
				.join("shopProduct").join("shopCategory").join("shop").join("ido");
		
		if(Objects.nonNull(filter.getOrderTypeStatus())) {
			Predicate statusSeding = null;
			Predicate statusPayment = null;
			
			if(filter.getOrderTypeStatus().equals(OrderTypeStatus.OPEN)) {
				statusSeding = builder.notEqual(root.get("statusSeding"), QuickPaySedingStatus.DELIVERED);
				Predicate statusSedingCanceled = builder.notEqual(root.get("statusSeding"), QuickPaySedingStatus.SENDCANCELED);
				statusPayment= builder.notEqual(root.get("statusPayment"), QuickPayPaymentStatus.PAID);
				Predicate statusPaymentCanceled = builder.notEqual(root.get("statusPayment"), QuickPayPaymentStatus.PAYMENTCANCELED);
				predicates.add(builder.and(builder.or(statusSeding, statusPayment), statusSedingCanceled, statusPaymentCanceled));
			}else {
				statusSeding = builder.equal(root.get("statusSeding"), QuickPaySedingStatus.DELIVERED);
				statusPayment= builder.equal(root.get("statusPayment"), QuickPayPaymentStatus.PAID);
				Predicate statusSedingCanceled = builder.equal(root.get("statusSeding"), QuickPaySedingStatus.SENDCANCELED);
				Predicate statusPaymentCanceled = builder.equal(root.get("statusPayment"), QuickPayPaymentStatus.PAYMENTCANCELED);
				predicates.add(builder.or(builder.and(statusPayment, statusSeding), builder.or(statusPaymentCanceled, statusSedingCanceled)));
			}
		}	
		if(Objects.nonNull(filter.getOrderNumber())) {
			predicates.add(builder.equal(root.get("orderNumber"), filter.getOrderNumber()));
		}
		if(Objects.nonNull(filter.getStatusSeding())) {
			predicates.add(builder.equal(root.get("statusSeding"), filter.getStatusSeding()));
		}
		if(Objects.nonNull(filter.getStatusPayment())) {
			predicates.add(builder.equal(root.get("statusPayment"), filter.getStatusPayment()));
		}
		if(dates != null && dates.size()>0) {
			predicates.add(builder.between(root.get("dateModel").get("dt_created"), dates.get(0), dates.get(1)));
		}
		if(Objects.nonNull(filter.getIdoId())) {
			List<Long> idoIds = IdoStringUtils.convertStringLong(filter.getIdoId());
			In<Long> ido = builder.in(tagJoin.get("id"));
			idoIds.forEach(i ->{
				ido.value(i);
			});
			predicates.add(ido);
		}
		criteria.where(predicates.toArray(new Predicate[0])).distinct(true);
		criteria.orderBy(builder.desc(root.get("id")));
		TypedQuery<ShopQuickPay> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	@Override
	public List<SingleQuickPay> findSingleQuickPayInvoice(LocalDate date, List<Long> idosId, Long userId){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SingleQuickPay> criteria = builder.createQuery(SingleQuickPay.class);
		Root<SingleQuickPay> root = criteria.from(SingleQuickPay.class);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("user").get("id"), userId));
		
		ListJoin<ShopQuickPay, Ido> tagJoin = root.join("user").join("business").joinList("idos");
		
		predicates.add(
				builder.and(
						builder.equal(root.get("statusPayment"), QuickPayPaymentStatus.PAID),
						builder.between(root.get("dateModel").get("dt_created"), date.withDayOfMonth(1).atStartOfDay(), date.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59))
						)
				);
		
		if(Objects.nonNull(idosId)) {
			In<Long> ido = builder.in(tagJoin.get("id"));
			idosId.forEach(i ->{
				ido.value(i);
			});
			predicates.add(ido);
		}
		criteria.where(predicates.toArray(new Predicate[0])).distinct(true);
		TypedQuery<SingleQuickPay> query = manager.createQuery(criteria);
		return query.getResultList();
		
	}
	
	@Override
	public List<ShopQuickPay> findShopQuickPayInvoice(LocalDate date, List<Long> idosId, Long userId){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ShopQuickPay> criteria = builder.createQuery(ShopQuickPay.class);
		Root<ShopQuickPay> root = criteria.from(ShopQuickPay.class);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("user").get("id"), userId));
		
		Join<ShopQuickPay, Ido> tagJoin = root.joinList("products").join("shopProductVariation")
				.join("shopProduct").join("shopCategory").join("shop").join("ido");
		
		predicates.add(
				builder.and(
						builder.equal(root.get("statusPayment"), QuickPayPaymentStatus.PAID),
						builder.between(root.get("dateModel").get("dt_created"), date.withDayOfMonth(1).atStartOfDay(), date.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59))
						)
				);
		
		if(Objects.nonNull(idosId)) {
			In<Long> ido = builder.in(tagJoin.get("id"));
			idosId.forEach(i ->{
				ido.value(i);
			});
			predicates.add(ido);
		}
		criteria.where(predicates.toArray(new Predicate[0])).distinct(true);
		TypedQuery<ShopQuickPay> query = manager.createQuery(criteria);
		return query.getResultList();
		
	}

	@Override
	public List<ShopQuickPay> findShopQuickPayDashboard(List<LocalDateTime> dates, Long userId, Long shopId) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ShopQuickPay> criteria = builder.createQuery(ShopQuickPay.class);
		Root<ShopQuickPay> root = criteria.from(ShopQuickPay.class);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("user").get("id"), userId));
		ListJoin<ShopQuickPay, ShopQuickPayProduct> tagJoin = root.joinList("products");
		
		if(dates!= null) {			
			predicates.add(
					builder.and(
							builder.between(root.get("dateModel").get("dt_created"), dates.get(0), dates.get(1))
							)
					);
		}
		predicates.add(builder.equal(tagJoin.get("shopProductVariation").get("shopProduct").get("shopCategory").get("shop"), shopId));
		criteria.where(predicates.toArray(new Predicate[0])).distinct(true);
		TypedQuery<ShopQuickPay> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<SingleQuickPay> findSingleQuickPayDashboard(List<LocalDateTime> dates, Long userId, Long shopId) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SingleQuickPay> criteria = builder.createQuery(SingleQuickPay.class);
		Root<SingleQuickPay> root = criteria.from(SingleQuickPay.class);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("user").get("id"), userId));
		ListJoin<ShopQuickPay, Ido> tagJoin = root.join("user").join("business").joinList("idos");
		
		if(dates != null) {			
			predicates.add(
					builder.and(
							builder.between(root.get("dateModel").get("dt_created"), dates.get(0), dates.get(1))
							)
					);
		}
		predicates.add(builder.equal(tagJoin.get("shop").get("id"),shopId ));
		criteria.where(predicates.toArray(new Predicate[0])).distinct(true);
		TypedQuery<SingleQuickPay> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	
	
	
}
