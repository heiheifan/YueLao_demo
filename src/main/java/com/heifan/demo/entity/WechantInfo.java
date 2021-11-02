package com.heifan.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("wechant_info")
public class WechantInfo implements Serializable {

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
     * 存入者
     */
    private String pushName;

    /**
     * 微信号
     */
    private String wechantNumber;

    /**
     * 性别：0女、1男、8未知
     */
    private Boolean sex;

    /**
     * 查看过此微信号的用户列表
     */
    private String showedList;


}
