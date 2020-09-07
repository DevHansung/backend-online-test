package com.hansung.web.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetApiRes {
    private Boolean status;
    private List<?> data;

    public GetApiRes(Boolean status, List<?> data) {
        this.status = status;
        this.data = data;
    }
}