package com.hansung.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiRes {
    private Boolean status;
    private String data;

    public ApiRes(Boolean status, String data) {
        this.status = status;
        this.data = data;
    }
}