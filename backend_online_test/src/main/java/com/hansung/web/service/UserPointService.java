package com.hansung.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hansung.web.dao.UserPointDao;
import com.hansung.web.dto.UnspentFolderRes;
import com.hansung.web.exception.BadRequestException;

@Service
public class UserPointService {

	@Autowired
	private UserPointDao userPointDao;

	public List<UnspentFolderRes> findUnspentFolderById(int id) {
		List<UnspentFolderRes> unspentFolder = userPointDao.findUnspentFolderById(id);
		if (unspentFolder.size() == 0) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "포인트를 모두 소모하였거나 혹은 조회하고자 하는 폴더가 존재하지 않습니다.");
		}
		return unspentFolder;
	}

}
