package com.hansung.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hansung.web.vo.Image;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {

}

