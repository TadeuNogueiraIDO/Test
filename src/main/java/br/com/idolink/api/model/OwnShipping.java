package br.com.idolink.api.model;

import java.math.BigDecimal;
import java.util.List;

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
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.TypePrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "own_shipping")
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE own_shipping SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
@Builder
public class OwnShipping extends BaseModel<OwnShipping>{

	private static final long serialVersionUID = 5568406045078840344L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "type_price")
	@Enumerated(EnumType.STRING)
	private TypePrice typePrice;
			
	@Column(name = "price")
	private BigDecimal price;
	
	@OneToOne
	@JoinColumn(name = "shipping_settings_id", referencedColumnName = "id")
	private ShippingSettings shippingSettings;
	
	@OneToMany(mappedBy = "ownShipping", fetch = FetchType.EAGER)
	private List<OwnShippingVariation> OwnShippingVariation;

	@Override
	public String toString() {
		return "OwnShipping [id=" + id + ", typePrice=" + typePrice + ", price=" + price + "]";
	}
	
}
