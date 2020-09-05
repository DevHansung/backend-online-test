package com.hansung.web.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="image_folder")
public class ImageFolder extends DateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_folder_id")
    private int imageFolderId;
    
    @Column(name="image_folder_pid")
    @ColumnDefault("0")
    private int imageFolderParentId;
    
    @Column(name="image_folder_name")
    @ColumnDefault("'new_folder'")
    private String imageFolderName;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private User user;

    public ImageFolder(){
    }
}