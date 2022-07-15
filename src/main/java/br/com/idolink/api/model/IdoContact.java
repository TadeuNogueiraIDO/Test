package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
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
@Table(name = "ido_contact")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE ido_contact SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class IdoContact extends BaseModel<IdoContact> {
	
	private static final long serialVersionUID = 6837637313561038023L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;
	
	@OneToOne
	@JoinColumn(name = "contact_id", referencedColumnName = "id")
	private Contact contact;
	
	@Column(name = "enable")
	private Boolean enable;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "activated")
	@ColumnDefault("false")
	private Boolean activated;
	
}
