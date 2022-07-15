package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.ToolCodName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tool")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE tool SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class Tool extends BaseModel<Tool> {

	private static final long serialVersionUID = 8426529154618312764L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "description")
	private String description;

	@Column(name = "file_uuid")
	private String icon;

	@Column(name = "reuse")
	private Boolean reuse;

	@Enumerated(EnumType.STRING)
	@Column(name = "availability")
	private IdoToolStatus availability;

	@OneToMany(mappedBy = "tool", fetch = FetchType.LAZY)
	private List<ToolCurrency> toolCurrency;

	@Column(name = "url")
	private String url;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "cod_name")
	private ToolCodName codName;
	
	@Column(name = "appversion")
	private String appversion;
	
	@Column(name = "is_listable")
	private Boolean isListable;
	
	@OneToMany(mappedBy = "tool")
	private List<IdoTool> idoTool;
	
	@Column(name = "info_html")
    private String infoHtml;
	
	@Column(name = "filter")
	private String filter;
	
		
}
