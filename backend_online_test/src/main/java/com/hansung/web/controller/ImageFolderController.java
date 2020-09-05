package com.hansung.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansung.web.dto.ApiResponse;
import com.hansung.web.service.ImageFolderService;
import com.hansung.web.vo.ImageFolder;
import com.hansung.web.vo.User;

@RestController
@RequestMapping("/api")
public class ImageFolderController {

	@Autowired
	private ImageFolderService imageFolderService;

	@PostMapping("/folder")
	public ResponseEntity<?> insertImageFolder(HttpServletRequest request, @RequestBody ImageFolder imageFolder) {
		User requestUser = (User) request.getAttribute("user");
		imageFolderService.insertImageFolder(requestUser.getName(), imageFolder);
		return ResponseEntity.ok().body(new ApiResponse(true, "success"));
	}
}