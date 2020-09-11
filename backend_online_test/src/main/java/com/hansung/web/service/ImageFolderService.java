package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.ImageFolderDao;
import com.hansung.web.dao.UserDao;
import com.hansung.web.dto.ImageRes;
import com.hansung.web.dto.UserImageFolderRes;
import com.hansung.web.exception.BadRequestException;
import com.hansung.web.vo.ImageFolder;
import com.hansung.web.vo.User;
import com.hansung.web.vo.UserPoint;

@Service
public class ImageFolderService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ImageFolderDao imageFolderDao;

	public void insertImageFolder(User requestUser, ImageFolder imageFolder) {
		if (imageFolder.getImageFolderName().trim().length() == 0) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "폴더명에 공백을 허용하지 않습니다..");
		}
		User user = userDao.findByName(requestUser.getName());
		UserPoint userPoint = new UserPoint();
		userPoint.setUserSavePoint(1000);
		userPoint.setUserAvailablePoint(userPoint.getUserSavePoint());
		userPoint.setUser(user);
		userPoint.setImageFolder(imageFolder);
		imageFolder.setUser(user);
		imageFolder.setUserPoint(userPoint);
		user.getImageFolder().add(imageFolder);
		userDao.save(user);
	}

	public List<UserImageFolderRes> findImageFoldersByName(String name) {
		return imageFolderDao.findImageFoldersByName(name);
	}

	public List<ImageRes> findImagesByImageFolderId(int imageFolderId) {
		return imageFolderDao.findImagesByImageFolderId(imageFolderId);
	}

}
