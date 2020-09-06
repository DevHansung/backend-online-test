package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansung.web.dao.ImageFolderDao;
import com.hansung.web.exception.BadRequestException;
import com.hansung.web.vo.Image;
import com.hansung.web.vo.ImageFolder;
import com.hansung.web.vo.User;

@Service
public class ImageService {

	@Autowired
	private ImageFolderDao imageFolderDao;

	@Transactional
	public void insertImage(User user, int imageFolderId, List<Image> imageList) {
        ObjectMapper mapper = new ObjectMapper();
        List<Image> imageListConvert = mapper.convertValue(imageList, new TypeReference<List<Image>>(){});
		ImageFolder imageFolder = imageFolderDao.findImageFolderByImageFolderId(imageFolderId)
				.orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "존재하지 않는 폴더명 입니다."));
		if (!imageFolder.getUser().getName().equals(user.getName())) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST,
					"본인이 생성한 폴더만 업로드 가능. folderId와 requestHeader의 name을 확인해주세요.");
		}
		for (int i = 0; i < imageListConvert.size(); i++) {
			if (imageListConvert.get(i).getImageUrl().trim().length() == 0) {
				System.out.println(imageListConvert.get(i).getImageUrl().trim().length());
				throw new BadRequestException(HttpStatus.BAD_REQUEST, "image url에 공백을 허용하지 않습니다.");
			}
			imageListConvert.get(i).setImageFolder(imageFolder);
			imageFolder.getImage().add(imageListConvert.get(i));
			imageFolderDao.save(imageFolder);
		}
	}
}
