package br.com.idolink.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tool_menu")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ToolMenu {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_menu_id", referencedColumnName = "id", insertable = true)
	private ItemMenuFooter menu;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tool_id", referencedColumnName = "id", insertable = true)
	private Tool tool;

	@Column(name = "label")
	private String label;

}
