package com.hansung.web.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListApiRes {
    private Boolean status;
    private List<?> data;

    public ListApiRes(Boolean status, List<?> data) {
        this.status = status;
        this.data = data;
    }
}