package br.com.idolink.api.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.QuickPayType;
import br.com.idolink.api.model.enums.TypeShipping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "single_quick_pay")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE single_quick_pay SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
@Builder
public class SingleQuickPay extends BaseModel<SingleQuickPay>{

	private static final long serialVersionUID = -4463621445052922935L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "single_price")
	private BigDecimal singlePrice;
	
	@Column(name = "discount_value")
	private BigDecimal discountValue;
	
	@Column(name = "additional_value")
	private BigDecimal additionalValue;
		
	@Column(name = "observation")
	private String observation;
	
	@Column(name = "single_quantity")
	private Integer singleQuantity;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private QuickPayType type;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "singleQuickPay", orphanRemoval = true)
	private List<SingleQuickPayProduct> products;
	
	@Embedded
	private ClientQuickPay dataClient;
	
	@Column(name="finalization_type")
	@Enumerated(EnumType.STRING)
	private QuickPayFinalizationType finalizationType;
	
	@Column(name="has_client_data")
	private Boolean hasClientData;
	
	@Column(name="has_delivery_adress")
	private Boolean hasDeliveryAdress;
	
	@Column(name = "order_number")
	private String orderNumber;
	
	@Column(name = "status_payment")
	@Enumerated(EnumType.STRING)
	private QuickPayPaymentStatus statusPayment;
	
	@Column(name = "status_sending")
	@Enumerated(EnumType.STRING)
	private QuickPaySedingStatus statusSeding;

	@OneToOne
	@JoinColumn(name = "payment_type_id", referencedColumnName = "id")
	private PaymentType paymentType;
		
	@Column(name = "anotherPaymentType")
	private String anotherPaymentType;
	
	@Column(name = "type_shipping")
	@Enumerated(EnumType.STRING)
	private TypeShipping typeShipping;
	
	@Column(name = "shipping_description")
	private String shippingDescription;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
}
