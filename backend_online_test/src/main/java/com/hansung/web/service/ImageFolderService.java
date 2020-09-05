package com.hansung.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.UserDao;
import com.hansung.web.exception.BadRequestException;
import com.hansung.web.vo.ImageFolder;
import com.hansung.web.vo.User;

@Service
public class ImageFolderService {

	@Autowired
	private UserDao userDao;

	public void insertImageFolder(String requestName, ImageFolder imageFolder) {

		if (imageFolder.getImageFolderName().trim().length() == 0) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "폴더명에 공백을 허용하지 않습니다..");
		}
		User user = userDao.findByName(requestName);
		imageFolder.setUser(user);
		user.getImageFolder().add(imageFolder);
		userDao.save(user);
	}

}
