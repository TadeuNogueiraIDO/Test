package br.com.idolink.api.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "type_notification")
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class PushNotificationType {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//delete table
	@Column(name = "title")
	private String title;
	
	@Column(name = "message")
	private String message;

	@Column(name = "icon")
	private Long icon;

}
