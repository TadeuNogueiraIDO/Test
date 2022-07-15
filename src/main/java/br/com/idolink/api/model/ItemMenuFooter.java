package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import br.com.idolink.api.model.enums.MenuFooterItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item_menu_footer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemMenuFooter {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "type_item")
	@Enumerated(EnumType.STRING)
	private MenuFooterItem typeItem;
	
	@Column(name = "label")
	private String label;
	
	@Column(name = "external_link")
	private String externalLink;
	
	@Column(name = "hide")
	@NotNull
	@Builder.Default
	@ColumnDefault("false")
	private Boolean hide = false;
	
	@OneToOne
	@JoinColumn(name = "ido_tool_id", referencedColumnName = "id")
	private IdoTool idoTool;

	@OneToOne
	@JoinColumn(name = "shop_category_id", referencedColumnName = "id")
	private ShopCategory shopCategory;	
	
	@ManyToOne
	@JoinColumn(name = "menu_footer_id", referencedColumnName = "id", nullable = false, insertable = true)
	private MenuFooter menuFooter;

}
