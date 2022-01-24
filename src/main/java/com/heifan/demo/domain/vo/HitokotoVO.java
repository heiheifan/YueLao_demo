package com.heifan.demo.domain.vo;

import lombok.Data;

@Data
public class HitokotoVO {

    private Integer id;

    private String uuid;

    private String hitokoto;

    private String type;

    private String from;

    private String from_who;
}
