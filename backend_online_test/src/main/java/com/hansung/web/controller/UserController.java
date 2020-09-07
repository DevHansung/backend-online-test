package com.hansung.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansung.web.dao.UserDao;
import com.hansung.web.dto.ApiRes;
import com.hansung.web.exception.UnauthorizedException;
import com.hansung.web.vo.User;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping("/user/{name}")
	public ResponseEntity<?> getCurrentUser(@PathVariable("name") String name) {
		userDao.findUserByName(name)
				.orElseThrow(() -> new UnauthorizedException(HttpStatus.BAD_REQUEST, "등록되지 않은 회원입니다."));
		return ResponseEntity.ok().body(new ApiRes(true, "등록된 회왼입니다."));
	}

	@PostMapping("/user/regist")
	public ResponseEntity<?> insertUser(@RequestBody User user) {
		String result = userDao.findName(user.getName());
		if (result != null) {
			return ResponseEntity.ok().body("이미 존재하는 name입니다..");
		}
		return ResponseEntity.ok().body(new ApiRes(true, userDao.save(user).getName() + "회원의 회원등록이 완료되었습니다."));
	}
}