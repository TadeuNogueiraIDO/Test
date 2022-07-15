package br.com.idolink.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.idolink.api.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notification")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Notification{


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "read")
	private Boolean read;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "icon")
	private String icon;
	
	@Column(name = "content_link")
	private String link;
	
	@Column(name = "value")
    private BigDecimal value;
	
	@Column(name = "type_notification")
	@Enumerated(EnumType.STRING)
	private NotificationType typeNotification;
	
	@Column(name = "creation_date")
	private LocalDate creationDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@PrePersist
	private void initializeReadfalse() {
		if (Objects.isNull(read)) {
			read = false;
		}

	}
}
