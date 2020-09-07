package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.ImageTagDao;
import com.hansung.web.dto.ImageTagLankRes;

@Service
public class ImageTagService {

	@Autowired
	private ImageTagDao imageTagDao;

	public List<ImageTagLankRes> findImageTagRank() {
		return imageTagDao.findImageTagRank();
	}
}