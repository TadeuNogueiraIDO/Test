package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "config_contact_us")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE config_contact_us SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ConfigContactUs extends BaseModel<ConfigContactUs> {

	
	private static final long serialVersionUID = -6109412241431317348L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "activated")
    @Builder.Default
    @ColumnDefault("true")
	private Boolean activated = true;

	@Column(name = "title")
	private String title;

	@Column(name = "subtitle")
	private String subtitle;
	
	@Column(name = "datauser")
	private String datauser;

	@OneToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;
	
	@OneToMany(mappedBy = "configContactUs", fetch = FetchType.LAZY)
	private List<FormContactUs> formContactUs;


}

