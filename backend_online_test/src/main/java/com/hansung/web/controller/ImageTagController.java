package com.hansung.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansung.web.dto.ListApiRes;
import com.hansung.web.dto.ImageTagLankRes;
import com.hansung.web.service.ImageTagService;

@RestController
@RequestMapping("/api")
public class ImageTagController {

	@Autowired
	private ImageTagService imageTagService;
	
	@GetMapping("/imagetag/rank")
	public ResponseEntity<?> getImageTag() {
		List<ImageTagLankRes> imageTagRank = imageTagService.findImageTagRank();
		return ResponseEntity.ok().body(new ListApiRes(true, imageTagRank));
	}
}