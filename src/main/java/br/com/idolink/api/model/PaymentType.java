package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "payment_type")
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE payment_type SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class PaymentType extends BaseModel<PaymentType>{

	private static final long serialVersionUID = -4736164405838139440L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "file_uuid")
	private String file;
	
	@Column(name = "name")
	private String name;
			
}
