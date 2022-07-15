package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.ProfileIdoCodName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "idolink_profile")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE idolink_profile SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ProfileIdoLink extends BaseModel<ProfileIdoLink> {

	private static final long serialVersionUID = 8698690988922146114L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "url")
	private String url;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "cod_name")
	private ProfileIdoCodName codName;
	
}
