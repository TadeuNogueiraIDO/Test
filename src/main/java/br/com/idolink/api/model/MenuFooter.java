package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "menu_footer")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE menu_footer SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class MenuFooter extends BaseModel<MenuFooter> {

	private static final long serialVersionUID = -1839340067223945275L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "activated")
    @Builder.Default
    @ColumnDefault("true")
	private Boolean activated = true;

	@Column(name = "logo")
	private Long logo;
	
	@Column(name = "unpin_menu")
	private Boolean unpinMenu;

	@Column(name = "activate_footer")
	private Boolean activateFooter;
	
	@OneToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "menuFooter", orphanRemoval = true)
	private List<ItemMenuFooter> itens;

}
