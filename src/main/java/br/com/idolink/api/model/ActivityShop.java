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
import org.springframework.lang.Nullable;

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
@Table(name = "activity_shop")
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE activity_shop SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
@Builder
public class ActivityShop extends BaseModel<ActivityShop>{

	private static final long serialVersionUID = 2363519057557577374L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne 
	@JoinColumn(name = "activity_id",  referencedColumnName = "id")
	private Activity activity;
	
	@Column(name = "other_segment")
	@Nullable
	private String otherSegment;
	

}
