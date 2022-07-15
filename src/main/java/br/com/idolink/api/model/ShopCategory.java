package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shop_category")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE shop_category SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ShopCategory extends BaseModel<ShopCategory> {

	private static final long serialVersionUID = 1902302672167096176L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "category_cover")
	private Long categoryCover;

	@Column(name = "hide_product")
	private Boolean hideProduct;
	
	@Column(name = "hide_category")
    @ColumnDefault("false")
	private Boolean hideCategory;
	
	@ManyToOne
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private Shop shop;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "shopCategory", fetch = FetchType.LAZY)
	private List<ShopProduct> products;

	@Override
	public String toString() {
		return "ShopCategory [id=" + id + ", name=" + name + "]";
	}

	
}

