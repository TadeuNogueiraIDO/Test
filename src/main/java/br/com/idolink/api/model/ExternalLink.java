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
@Table(name = "external_link")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE external_link SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ExternalLink extends BaseModel<ExternalLink>{
	
	private static final long serialVersionUID = 2020333461107269289L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "link")
	private String link;
	
	@ManyToOne
	@JoinColumn(name = "item_menu_footer_id", referencedColumnName = "id")
	private ItemMenuFooter configMenuFooter;
}
