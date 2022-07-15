package br.com.idolink.api.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.apache.logging.log4j.util.Strings;
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
@SQLDelete(sql = "UPDATE public.user SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
@Where(clause = "dt_deleted is null")
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "public")
@Builder
public class User extends BaseModel<User> {

	private static final long serialVersionUID = 6837637313561038023L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(nullable = false, name = "uuid")
    private String uuid;

	@Column(name = "name")
	@Nullable
	private String name;

	@Column(name = "email")
	@Nullable
	private String email;

	@Column(name = "password")
	@Nullable
	private String password;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "birth_data")
	private LocalDate birthData;
	
	@Column(name = "dial_code")
	private String dialCode;
	
	@Column(name = "number")
	private String number;
	
	@Column (name = "validate_email")
	private Boolean validateEmail;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
	private GeneralSettings settings;
	
	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private Country country;
	
	@Column(name = "file_avatar")
	private Long fileAvatar;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Business> business;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Notification> notification;
	
	@PrePersist
	private void initialize ()	{
		if (Strings.isEmpty(uuid))
		{
			uuid = UUID.randomUUID().toString();
		}
		if(Objects.isNull(status)) {
			status = true;
		}
	}

}
