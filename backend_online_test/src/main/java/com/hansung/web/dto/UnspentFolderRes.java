package com.hansung.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface UnspentFolderRes {
	@JsonProperty
	int getImageFolderId();
	
	@JsonProperty
	String getImageFolderName();
}
