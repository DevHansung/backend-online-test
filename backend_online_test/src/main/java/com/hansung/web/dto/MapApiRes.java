package com.hansung.web.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapApiRes {
    private Boolean status;
    private Map<String,Object> data;

    public MapApiRes(Boolean status, Map<String, Object> data) {
        this.status = status;
        this.data = data;
    }
}