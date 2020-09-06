package com.hansung.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansung.web.dto.ApiResponse;
import com.hansung.web.service.ImageService;
import com.hansung.web.vo.Image;
import com.hansung.web.vo.User;

@RestController
@RequestMapping("/api")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@SuppressWarnings("unchecked")
	@PostMapping("/image/{imageFolderId}")
	public ResponseEntity<?> insertImage(HttpServletRequest request, @PathVariable("imageFolderId") int imageFolderId, @RequestBody Map<String, Object> params) {
		List<Image> imageList = (List<Image>) params.get("images");
		User user = (User) request.getAttribute("user");
		imageService.insertImage(user, imageFolderId, imageList);
		return ResponseEntity.ok().body(new ApiResponse(true, "success"));
	}
}