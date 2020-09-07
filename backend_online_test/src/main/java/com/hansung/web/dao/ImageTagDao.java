package com.hansung.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hansung.web.dto.ImageTagLankRes;
import com.hansung.web.vo.ImageTag;

@Repository
public interface ImageTagDao extends JpaRepository<ImageTag, Long> {

	@Query(value = "SELECT \n" +
			"tagName, tagCount, tagRank \n" +
			"FROM\n" + 
			"(\n" + 
			"SELECT \n" + 
			"image_tag_name as tagName, \n" + 
			"count(image_tag_name) AS tagCount, \n" + 
			"DENSE_RANK() OVER(ORDER BY count(image_tag_name) DESC) AS tagRank\n" + 
			"FROM image_tag \n" + 
			"GROUP BY image_tag_name\n" + 
			") AS image_tag_rank WHERE image_tag_rank.tagRank <= 10", nativeQuery = true)
	List<ImageTagLankRes> findImageTagRank();

}