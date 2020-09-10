package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansung.web.dao.ImageFolderDao;
import com.hansung.web.dao.UserPointDao;
import com.hansung.web.dto.ImageReq;
import com.hansung.web.exception.BadRequestException;
import com.hansung.web.vo.Image;
import com.hansung.web.vo.ImageFolder;
import com.hansung.web.vo.User;
import com.hansung.web.vo.UserPoint;

@Service
public class ImageService {

	@Autowired
	private ImageFolderDao imageFolderDao;

	@Autowired
	private UserPointDao userPointDao;

	@Transactional
	public void insertImage(User user, int imageFolderId, List<Image> imageList) {
		ImageFolder imageFolder = imageFolderDao.findImageFolderByImageFolderId(imageFolderId)
				.orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "존재하지 않는 폴더명 입니다."));
		if (!imageFolder.getUser().getName().equals(user.getName())) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST,
					"본인이 생성한 폴더만 업로드 가능. folderId와 requestHeader의 name을 확인해주세요.");
		}
		ObjectMapper mapper = new ObjectMapper();
		List<ImageReq> imageListConvert = mapper.convertValue(imageList, new TypeReference<List<ImageReq>>() {
		});
		int totalMinusPoint = imageListConvert.size() * 100;
		int userTotalAvailablePoint = (Integer)userPointDao.findUserTotalAvailablePointById(user.getId())
				.orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "포인트 테이블이 존재하지 않습니다."));
		if (userTotalAvailablePoint >= totalMinusPoint) {
			for (int i = 0; i < imageListConvert.size(); i++) {
				if (imageListConvert.get(i).getImage().getImageUrl().trim().length() == 0) {
					throw new BadRequestException(HttpStatus.BAD_REQUEST,
							i + 1 + "번 image url에 공백이 포함되어 있습니다. 공백을 허용하지 않습니다.");
				}
				imageListConvert.get(i).getImage().setImageFolder(imageFolder);
				imageFolder.getImage().add(imageListConvert.get(i).getImage());
				for (int j = 0; j < imageListConvert.get(i).getImageTags().size(); j++) {
					if (imageListConvert.get(i).getImageTags().get(j).getImageTagName().trim().length() == 0) {
						throw new BadRequestException(HttpStatus.BAD_REQUEST,
								i + 1 + "번 image url의 image tag중 공백이 포함된 값이 있습니다. 공백을 허용하지 않습니다.");
					}
					imageListConvert.get(i).getImageTags().get(j).setImage(imageListConvert.get(i).getImage());
					imageListConvert.get(i).getImage().getImageTag().add(imageListConvert.get(i).getImageTags().get(j));
				}
				imageFolderDao.save(imageFolder);
			}
			List<UserPoint> userMinusPoints= userPointDao.findUserMinusPoints(user.getId(), totalMinusPoint);
			for(int i=0 ; i<userMinusPoints.size(); i++) {
				int getUserSavePoint = userMinusPoints.get(i).getUserSavePoint();
				int getUserAvailablePoint = userMinusPoints.get(i).getUserAvailablePoint();
				int getUserMinusPoint = userMinusPoints.get(i).getUserMinusPoint();
				userMinusPoints.get(i).setUserAvailablePoint(getUserAvailablePoint-getUserMinusPoint);
				userMinusPoints.get(i).setUserMinusPoint(getUserSavePoint-getUserAvailablePoint+getUserMinusPoint);
				userPointDao.save(userMinusPoints.get(i));
			}
		} else
			throw new BadRequestException(HttpStatus.BAD_REQUEST, 
					"포인트가 부족합니다. 부족한 포인트:" + (userTotalAvailablePoint-totalMinusPoint)
					+ ", 보유포인트:" + userTotalAvailablePoint + ", 차감포인트:" + totalMinusPoint);
	}
}