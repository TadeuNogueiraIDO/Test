package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "contact")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE contact SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class Contact extends BaseModel<Contact> {
	
	private static final long serialVersionUID = 6837637313561038023L;
	
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
	
	@Column(name = "mask")
	private String mask;
	
	@Column(name = "logo")
	private String logo;
	


}
