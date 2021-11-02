package com.heifan.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("usr_info")
public class UsrInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据是否有效 1 有效 0 无效
     */
    private Boolean isEnable;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 用户名称
     */
    private String usrName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户注册时间
     */
    private Long registerTime;

    /**
     * 用户最后登时间
     */
    private Long lastLoginTime;


}
