package com.heifan.demo.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.heifan.demo.domain.vo.HitokotoVO;
import com.heifan.demo.entity.HitokotoBean;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 获取每日一言
 */
@Slf4j
public class GetHitokotoUtil {

    public static HitokotoVO getHitokoto() {
        HitokotoBean hitokotoBean = JsonTool.toBean(HttpUtil.get("v1.hitokoto.cn"), HitokotoBean.class);
        HitokotoVO hitokotoVO = new HitokotoVO();
        BeanUtil.copyProperties(hitokotoBean, hitokotoVO);
        return hitokotoVO;
    }
}
