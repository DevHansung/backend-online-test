package com.hansung.web.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hansung.web.vo.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	@Query(value = "Select u.name from user u where u.name = ?1", nativeQuery = true)
	String findName(String name);

	Optional<User> findUserByName(String name);

	User findByName(String name);

	User save(String name);
}