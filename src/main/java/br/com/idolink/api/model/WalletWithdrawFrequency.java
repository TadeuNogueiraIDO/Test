package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.PeriodFrequency;
import br.com.idolink.api.model.enums.WeekDay;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wallet_withdraw_frequency")
@RequiredArgsConstructor
@Getter
@Setter
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE wallet_withdraw_frequency SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class WalletWithdrawFrequency extends BaseModel<WalletWithdrawFrequency>{

	private static final long serialVersionUID = -8573811279828362980L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "period_frequency")
	private PeriodFrequency periodFrequency;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "week_day")
	private WeekDay weekDay;

	@Column(name = "month_day")
	private Long day;
}
