package com.hansung.web.dto;

import java.util.Date;

public interface ImageRes {
	int getFolderId();

	String getFolderName();

	int getImageId();

	String getImageUrl();

	Date getCreateAt();
}
