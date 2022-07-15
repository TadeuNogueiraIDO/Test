
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "general_settings")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE general_settings SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class GeneralSettings extends BaseModel<GeneralSettings> {

	private static final long serialVersionUID = -7818607649228215549L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "language_id", referencedColumnName = "id")
	private Language language;

	@Column(name = "sensitive_content")
	private Boolean sensitiveContent;

	@Column(name = "receive_report_by_email_week")
	private Boolean receiveReportEmailWeek = true;

	@Column(name = "receive_report_by_email_month")
	private Boolean receiveReportEmailMonth = true;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private User user;


}
