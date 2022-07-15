package br.com.idolink.api.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.InvoicePaymentStatus;
import br.com.idolink.api.model.enums.Month;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invoice", schema = "public")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE invoice SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class Invoice extends BaseModel<Invoice>{
	
	private static final long serialVersionUID = 3076235224569423227L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status")
	private InvoicePaymentStatus paymentStatus;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "year")
	private Long year;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "month")
	private Month month;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

}
