package com.hansung.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringApiRes {
    private Boolean status;
    private String data;

    public StringApiRes(Boolean status, String data) {
        this.status = status;
        this.data = data;
    }
}