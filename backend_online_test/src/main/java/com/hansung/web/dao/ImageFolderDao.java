package com.hansung.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hansung.web.vo.User;

@Repository
public interface ImageFolderDao extends JpaRepository<User, Long> {

	User findUserByName(String name);

}