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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "ido_domain")
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE ido_domain SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class CustomDomain extends BaseModel<CustomDomain> {

	private static final long serialVersionUID = 6239141912527937097L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "domain")
	private String domain;
	
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	@OneToOne
	private Ido ido;
}
