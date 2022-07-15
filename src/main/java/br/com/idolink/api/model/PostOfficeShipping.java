package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.PostOfficeServiceOption;
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
@Table(name = "post_office_shipping")
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE post_office_shipping SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
@Builder
public class PostOfficeShipping extends BaseModel<PostOfficeShipping>{

	private static final long serialVersionUID = -1821995281786575257L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "shipping_settings_id", referencedColumnName = "id")
	private ShippingSettings shippingSettings;
	
	@Column(name = "zipcode_origin")
	private String zipcodeOrigin;
	
	@Column(name = "type_shipping")
	private String typeShipping;
			
    @Column(name = "service_option")
    @Enumerated(EnumType.STRING)
	private PostOfficeServiceOption serviceOption;
	
	@Column(name = "adm_code")
	private String admCode;
	
	@Column(name = "adm_password")
	private String admPassword;
	
	@Column(name = "optional_services")
	private String optionalServices;

	@Override
	public String toString() {
		return "PostOfficeShipping [id=" + id + ", zipcodeOrigin=" + zipcodeOrigin + ", serviceOption=" + serviceOption
				+ "]";
	}
		
		
}

