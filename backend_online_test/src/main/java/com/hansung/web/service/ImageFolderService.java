package com.hansung.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.ImageFolderDao;
import com.hansung.web.dao.UserDao;
import com.hansung.web.dao.UserPointDao;
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

	@Autowired
	private UserPointDao userPointDao;

	public void insertImageFolder(User requestName, ImageFolder imageFolder) {
		if (imageFolder.getImageFolderName().trim().length() == 0) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "폴더명에 공백을 허용하지 않습니다..");
		}
		Optional<UserPoint> getUserPoint = userPointDao.findUserPointByUserId(requestName.getId());
		User user = userDao.findByName(requestName.getName());

		getUserPoint.ifPresentOrElse(updateUserPoint -> {
			updateUserPoint.setUserPointValue(updateUserPoint.getUserPointValue() + 1000);
			updateUserPoint.setUser(user);
			imageFolder.setUser(user);
			user.getImageFolder().add(imageFolder);
			user.setUserPoint(updateUserPoint);
			userDao.save(user);
		}, () -> {
			UserPoint insertUserPoint = new UserPoint();
			insertUserPoint.setUserPointValue(1000);
			insertUserPoint.setUser(user);
			imageFolder.setUser(user);
			user.getImageFolder().add(imageFolder);
			user.setUserPoint(insertUserPoint);
			userDao.save(user);
		});
	}

	public List<UserImageFolderRes> findImageFoldersByName(String name) {
		return imageFolderDao.findImageFoldersByName(name);
	}

	public List<ImageRes> findImagesByImageFolderId(int imageFolderId) {
		return imageFolderDao.findImagesByImageFolderId(imageFolderId);
	}

}
