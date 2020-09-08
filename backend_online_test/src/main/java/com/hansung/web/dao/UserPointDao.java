package com.hansung.web.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hansung.web.vo.UserPoint;

@Repository
public interface UserPointDao extends JpaRepository<UserPoint, Long> {

	Optional<UserPoint> findUserPointByUserId(int id);

}

