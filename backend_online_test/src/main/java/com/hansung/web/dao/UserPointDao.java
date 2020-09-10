package com.hansung.web.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hansung.web.dto.UnspentFolderRes;
import com.hansung.web.vo.UserPoint;

@Repository
public interface UserPointDao extends JpaRepository<UserPoint, Long> {

	Optional<UserPoint> findUserPointByUserId(int id);
	
	@Query(value = "SELECT sum(user_available_point) as userTotalAvailablePoint FROM user_point WHERE id = ?1", nativeQuery = true)
	Optional<Integer> findUserTotalAvailablePointById(int id);

	@Query(value = "WITH user_point\n" + 
			"AS\n" + 
			"(\n" + 
			"SELECT \n" + 
			"user_point_id, \n" + 
			"user_save_point, \n" + 
			"user_available_point, \n" + 
			"created_at,\n" + 
			"updated_at,\n" + 
			"image_folder_id,\n" + 
			"id \n" + 
			"FROM user_point WHERE id = :id\n" + 
			")\n" + 
			"SELECT \n" + 
			"	A.user_point_id,\n" + 
			"	A.user_save_point,\n" + 
			"	A.user_available_point,\n" + 
			"   A.created_at,\n" + 
			"   A.updated_at,\n" + 
			"   A.image_folder_id,\n" + 
			"	A.id,\n" + 
			"	CASE \n" + 
			"        WHEN SUM(B.user_available_point)-:totalMinusPoint<0 THEN CAST(A.user_available_point AS SIGNED)\n" + 
			"        ELSE CAST(A.user_available_point-(SUM(B.user_available_point)-:totalMinusPoint) AS SIGNED)\n" + 
			"	END AS user_minus_point \n" + 
			"FROM user_point A\n" + 
			"LEFT OUTER JOIN  user_point B\n" + 
			"ON A.user_point_id >= B.user_point_id\n" + 
			"WHERE A.user_available_point>0\n" + 
			"GROUP BY \n" + 
			"	A.user_point_id ,\n" + 
			"	A.user_save_point ,\n" + 
			"	A.user_available_point\n" + 
			"HAVING  A.user_available_point-SUM(B.user_available_point)>:totalMinusPoint*(-1);", nativeQuery = true)
	List<UserPoint> findUserMinusPoints(@Param("id") int id, @Param("totalMinusPoint") int totalMinusPoint);
	
	@Query(value = "SELECT  \n" + 
			"u.image_folder_id as imageFolderId, \n" + 
			"i.image_folder_name as imageFolderName \n" + 
			"FROM user_point u\n" + 
			"LEFT OUTER JOIN  image_folder i\n" + 
			"ON u.image_folder_id >= i.image_folder_id\n" + 
			"WHERE u.id = ?1 and u.user_save_point = 1000 and u.user_available_point = 1000\n" + 
			"GROUP BY u.image_folder_id", nativeQuery = true)
	List<UnspentFolderRes> findUnspentFolderById(int id);
}

