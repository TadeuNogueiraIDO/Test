package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.idolink.api.model.common.BaseModel;
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
@Table(name = "own_shipping_variation")
@Builder
public class OwnShippingVariation extends BaseModel<OwnShippingVariation>{

	
    private static final long serialVersionUID = 1338993741988033856L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "label")
	private String label;
			
	@Column(name = "price")
	private Float price;
	
	@ManyToOne
	@JoinColumn(name = "own_shipping_id", referencedColumnName = "id")
	private OwnShipping ownShipping;

}
