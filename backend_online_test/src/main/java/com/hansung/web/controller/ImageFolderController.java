package com.hansung.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansung.web.dto.ApiResponse;
import com.hansung.web.dto.UserImageFolderRes;
import com.hansung.web.service.ImageFolderService;
import com.hansung.web.vo.ImageFolder;
import com.hansung.web.vo.User;

@RestController
@RequestMapping("/api")
public class ImageFolderController {

	@Autowired
	private ImageFolderService imageFolderService;

	@GetMapping("/folder")
	public ResponseEntity<?> getImageFolder(HttpServletRequest request) {
		User requestUser = (User) request.getAttribute("user");
		List<UserImageFolderRes> result = imageFolderService.findImageFolderByName(requestUser.getName());
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("/folder")
	public ResponseEntity<?> insertImageFolder(HttpServletRequest request, @RequestBody ImageFolder imageFolder) {
		User requestUser = (User) request.getAttribute("user");
		imageFolderService.insertImageFolder(requestUser.getName(), imageFolder);
		return ResponseEntity.ok().body(new ApiResponse(true, "success"));
	}
}