package com.heifan.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heifan.demo.entity.UsrInfo;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *UsrInfoDao.xml
 * @author HiF
 * @since 2021-10-27
 */
@Repository
public interface UsrInfoDao extends BaseMapper<UsrInfo> {

    /**
     * 登录校验
     * @Param usrName
     * @Return java.lang.Boolean
     * @Author HiF
     * @Date 2021/10/28 9:14
     */
    String login(String usrName);
}
