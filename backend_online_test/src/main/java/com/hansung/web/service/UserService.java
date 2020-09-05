package com.hansung.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.UserDao;
import com.hansung.web.exception.UnauthorizedException;
import com.hansung.web.vo.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User authenticateUser(String name) {
		User user = userDao.findUserByName(name)
				.orElseThrow(() -> new UnauthorizedException(HttpStatus.BAD_REQUEST, "등록되지 않은 회원입니다."));
		return user;
	}

}
