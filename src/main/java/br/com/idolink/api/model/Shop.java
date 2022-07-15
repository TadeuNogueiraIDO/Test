package br.com.idolink.api.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.FormatCardProduct;
import br.com.idolink.api.model.enums.FormatShowcase;
import br.com.idolink.api.model.enums.ShopStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shop")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE shop SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class Shop extends BaseModel<Shop> {

	private static final long serialVersionUID = -4546501430893623540L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "activated")
    @Builder.Default
    @ColumnDefault("true")
	private Boolean activated = true;
	
	@Column(name = "name")
	private String name;

	@Column(name = "activate_name")
	private Boolean activateName;

	@Column(name = "file_id")
	private Long storeBanner;
	
	@Column(name = "format_showcase")
	@Enumerated(EnumType.STRING)
	private FormatShowcase formatShowcase;
	
	@Column(name = "format_card_product")
	@Enumerated(EnumType.STRING)
	private FormatCardProduct formatCardProduct;
	
	@OneToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private Ido ido;
		
	@OneToOne 
	@JoinColumn(name = "activity_shop_id", referencedColumnName = "id")
	private ActivityShop activityShop;
	
	@OneToMany( cascade = CascadeType.REMOVE ,mappedBy = "shop", fetch = FetchType.LAZY)
	private List<ShopCategory> categories;
		
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private ShopStatus status;
	
	@Column(name = "terms_Id")
	@ColumnDefault("1")
	private Long termosIdoId;

	@Column(name = "term_privacy")
	@ColumnDefault("null")
	private String privacy;
	
	@Column(name = "term_reimbursement")
	@ColumnDefault("null")
	private String reimbursement;
	
	@Column(name = "term_use")
	@ColumnDefault("null")
	private String use;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "shop", fetch = FetchType.LAZY)
	private List<PaymentSetup> paymentSetup;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "shop", fetch = FetchType.LAZY)
	private List<DiscountCoupon> coupon;
		
	@PrePersist
	private void buildNew() {
		if(Objects.isNull(activateName)) {
			activateName = false;
		}
	}

}
