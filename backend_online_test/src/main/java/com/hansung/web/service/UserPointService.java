package com.hansung.web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.UserPointDao;
import com.hansung.web.vo.UserPoint;

@Service
public class UserPointService {
	
	@Autowired
	private UserPointDao userPointDao;

	public Optional<UserPoint> findUserPointByUserId(int id) {
		return userPointDao.findUserPointByUserId(id);
	}
}
