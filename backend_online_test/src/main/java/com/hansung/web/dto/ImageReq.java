package com.hansung.web.dto;

import java.util.List;

import com.hansung.web.vo.Image;
import com.hansung.web.vo.ImageTag;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageReq {
	private Image image;
	private List<ImageTag> imageTags;
}
