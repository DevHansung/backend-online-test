package com.hansung.web.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hansung.web.vo.audit.DateAudit;

import lombok.Getter;
import lombok.Setter;

//@DynamicUpdate 
@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "user_point")
public class UserPoint extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_point_id")
	private int userPointId;

	@Column(name = "user_save_point")
	@ColumnDefault("1000")
	private int userSavePoint;

	@Column(name = "user_available_point")
	@ColumnDefault("1000")
	private int userAvailablePoint;
	
	@Column(name = "user_minus_point")
	@ColumnDefault("0")
	private int userMinusPoint;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private User user;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_folder_id")
	private ImageFolder imageFolder;

	public UserPoint() {
	}
}