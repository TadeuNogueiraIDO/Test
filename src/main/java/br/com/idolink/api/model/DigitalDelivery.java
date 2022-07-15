package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "digital_delivery")
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE digital_delivery SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
@Builder
public class DigitalDelivery
    extends
    BaseModel<DigitalDelivery>
{

	private static final long serialVersionUID = 714429786460885294L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "shipping_settings_id", referencedColumnName = "id")
	private ShippingSettings shippingSettings;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled_password")
	private Boolean enabledPassword;

	@Override
	public String toString ()
	{
		return "DigitalDelivery [id=" + id + ", password=" + password + ", enabledPassword=" + enabledPassword + "]";
	}

}
