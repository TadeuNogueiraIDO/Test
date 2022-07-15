package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.idolink.api.model.ShopProductVariation;


public interface MyShopRepository extends JpaRepository<ShopProductVariation, Long>{
	
	@Query(value = "select sum(my_shop.category) as categories, sum(my_shop.produtos) as totalProducts, sum(my_shop.low) as lowStock, sum(my_shop.zero) as withoutStock\r\n"
			+ "from (select count(distinct(sc)) as category, \r\n"
			+ "		(select count(sp.id) from shop_product sp where sp.shop_category_id = sc.id and sp.dt_deleted is null) as produtos,\r\n"
			+ "		(select count(pv.id) as estoque from product_variation pv\r\n"
			+ "		inner join shop_product sp on pv.shop_product_id = sp.id \r\n"
			+ "		where pv.amount > 0 and pv.amount < 5 and pv.amount is not null and sp.shop_category_id = sc.id and pv.dt_deleted is null) as low,\r\n"
			+ "		(select count(pv.id) as estoque from product_variation pv\r\n"
			+ "		inner join shop_product sp on pv.shop_product_id = sp.id \r\n"
			+ "		where pv.amount = 0 and pv.amount is not null and sp.shop_category_id = sc.id and pv.dt_deleted is null) as zero\r\n"
			+ "		from ido i, shop s, shop_category sc \r\n"
			+ "		where i.id = :idoId and s.ido_id = i.id and\r\n"
			+ "		sc.dt_deleted is null and\r\n"
			+ " sc.shop_id = s.id group by sc.id ) as my_shop", nativeQuery = true)
	MyShopProject dashboardMyShop(@Param("idoId") Long idoId);

}
