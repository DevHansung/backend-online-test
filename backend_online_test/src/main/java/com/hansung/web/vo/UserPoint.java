package com.hansung.web.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hansung.web.vo.audit.DateAudit;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name="user_point")
public class UserPoint extends DateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_point_id")
    private int userPointId;
    
    @Column(name="user_point_value")
    @ColumnDefault("1000")
    private int userPointValue;
    
    
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private User user;


    public UserPoint(){
    }
}