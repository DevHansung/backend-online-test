package com.hansung.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private Boolean status;
    private String message;

    public ApiResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}