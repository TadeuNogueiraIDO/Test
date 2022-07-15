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
import org.springframework.lang.Nullable;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.TypeActivity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "activity")
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE activity SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class Activity extends BaseModel<Activity>{


	private static final long serialVersionUID = 8584196737392219025L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "type_activity", nullable = false)
	@Enumerated(EnumType.STRING)
	private TypeActivity typeActivity;
	
	@Column(name = "segment")
	@Nullable
	private String segment;
	
	@Column(name = "has_other_segment")
	private Boolean hasOtherSegment;
	
	@Column(name = "img_uiid")
	private String imgUiid; 
	
	@Column(name = "filter")
	private String filter;
	
}
