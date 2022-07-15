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
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "local_pickup")
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE local_pickup SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
@Builder
public class LocalPickup extends BaseModel<LocalPickup>{

	private static final long serialVersionUID = -1883309470619241837L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "shipping_settings_id", referencedColumnName = "id")
	private ShippingSettings shippingSettings;
	
	@Column(name = "address_line_1")
	private String addressLine1;
	
	@Column(name = "address_line_2")
	private String addressLine2;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "complement")
	private String complement;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "addressName")
	private String addressName;
	
	@Column(name = "city")
	private String city;
			
    @Column(name = "state")
	private String state;
    
	@ManyToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private State stateBrasil;
	
	@Column(name = "zipcode")
	private String zipCode;
	
	@Column(name = "is_enabled")
	private Boolean isEnabled;		
	
	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private Country country;
}
