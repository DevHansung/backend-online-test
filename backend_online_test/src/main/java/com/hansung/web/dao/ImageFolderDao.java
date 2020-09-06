package com.hansung.web.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hansung.web.vo.ImageFolder;

@Repository
public interface ImageFolderDao extends JpaRepository<ImageFolder, Long> {

	Optional<ImageFolder> findImageFolderByImageFolderId(int imageFolderId);

}