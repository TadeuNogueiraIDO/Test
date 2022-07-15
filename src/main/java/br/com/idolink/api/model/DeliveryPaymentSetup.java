package br.com.idolink.api.model;

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
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="delivery_payment_setup")
@Where(clause="dt_deleted is null")
@SQLDelete(sql="UPDATE delivery_payment_setup SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
@Builder
public class DeliveryPaymentSetup extends BaseModel<DeliveryPaymentSetup> {


	private static final long serialVersionUID = 707460740504187231L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "other_payment")
	private String otherPayment;
	
	@ManyToOne
	@JoinColumn(name = "payment_type_id", referencedColumnName = "id")
	private PaymentType paymentType;
	
	@ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    public Shop shop;
}
