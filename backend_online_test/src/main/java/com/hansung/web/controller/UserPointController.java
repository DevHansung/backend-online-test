package com.hansung.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansung.web.dto.ListApiRes;
import com.hansung.web.dto.UnspentFolderRes;
import com.hansung.web.service.UserPointService;

@RestController
@RequestMapping("/api")
public class UserPointController {

	@Autowired
	private UserPointService userPointService;

	@PostMapping("/point/unspent/{id}")
	public ResponseEntity<?> insertImage(@PathVariable("id") int id) {
		List<UnspentFolderRes> unspentFolder = userPointService.findUnspentFolderById(id);
		return ResponseEntity.ok().body(new ListApiRes(true, unspentFolder));
	}
}