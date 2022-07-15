package br.com.idolink.api.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "single_quick_pay_product")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE single_quick_pay_product SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class SingleQuickPayProduct extends BaseModel<SingleQuickPayProduct>{

	private static final long serialVersionUID = 3188963589920510147L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "observation")
	private String observation;
			
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "single_quick_pay_id", referencedColumnName = "id")
	private SingleQuickPay singleQuickPay;
	
}
