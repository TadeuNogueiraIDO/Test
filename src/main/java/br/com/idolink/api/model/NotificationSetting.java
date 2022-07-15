package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "notification_setting")
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE notification_setting SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class NotificationSetting extends BaseModel<NotificationSetting>{
	 
	private static final long serialVersionUID = -1583904831113230025L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "active_notifications")
	private Boolean activeNotifications;

	@Column(name = "system_warnings")
	private Boolean systemWarnings;

	@Column(name = "requests")
	private Boolean requests;

	@Column(name = "wallet")
	private Boolean wallet;
	
	@Column(name = "form")
	private Boolean form;
	
	@Column(name = "commissions")
	private Boolean commissions;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
		
	
}
