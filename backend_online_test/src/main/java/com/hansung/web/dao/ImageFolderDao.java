package com.hansung.web.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hansung.web.dto.ImageRes;
import com.hansung.web.dto.UserImageFolderRes;
import com.hansung.web.vo.ImageFolder;

@Repository
public interface ImageFolderDao extends JpaRepository<ImageFolder, Long> {

	Optional<ImageFolder> findImageFolderByImageFolderId(int imageFolderId);

	@Query(value = "SELECT\n" + 
			"f.image_folder_id AS folderId,\n" + 
			"u.name AS name,\n" + 
			"f.image_folder_name AS folderName,\n" + 
			"count(i.image_folder_id) AS imageCount,\n" + 
			"f.created_at AS createAt\n" + 
			"FROM user u\n" + 
			"LEFT OUTER JOIN image_folder f \n" + 
			"ON u.id = f.id\n" + 
			"LEFT OUTER JOIN image i \n" + 
			"ON f.image_folder_id = i.image_folder_id\n" + 
			"WHERE u.name = ?1\n" + 
			"GROUP BY i.image_folder_id\n" + 
			"ORDER BY f.created_at ASC", nativeQuery = true)
	List<UserImageFolderRes> findImageFoldersByName(String name);

	@Query(value = "SELECT\n" + 
			"f.image_folder_name AS folderName,\n" + 
			"f.image_folder_id AS folderId,\n" + 
			"i.image_id AS imageId,\n" + 
			"i.image_url AS imageUrl,\n" + 
			"i.created_at AS createAt\n" + 
			"FROM image_folder f \n" + 
			"LEFT OUTER JOIN image i\n" + 
			"ON f.image_folder_id = i.image_folder_id\n" + 
			"WHERE i.image_folder_id = ?1\n" + 
			"ORDER BY i.created_at ASC", nativeQuery = true)
	List<ImageRes> findImagesByImageFolderId(int imageFolderId); 
}

