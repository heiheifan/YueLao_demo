package com.heifan.demo.entity;

import lombok.Data;

@Data
public class HitokotoBean {

    private Integer id;

    private String uuid;

    private String hitokoto;

    private String type;

    private String from;

    private String from_who;
}
