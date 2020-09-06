package com.hansung.web.dto;

import java.util.Date;

public interface UserImageFolderRes {
	int getFolderId();

	String getName();

	String getFolderName();

	int getImageCount();

	Date getCreateAt();
}
