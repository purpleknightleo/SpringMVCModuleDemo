package com.lee.demo.springmvc.common.dao.impl;

import com.lee.demo.springmvc.common.dao.BaseDAO;
import com.lee.demo.springmvc.common.dao.UserDAO;
import com.lee.demo.springmvc.common.dao.domain.UserDO;
import com.lee.demo.springmvc.common.model.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO实现类
 *
 * Created by hzlifan on 2017/2/7.
 */
@Repository
public class UserDAOImpl extends BaseDAO<UserDO> implements UserDAO {

    private static final String NAMESPACE = "com.lee.demo.springmvc.common.dao.UserDAO";

    @Override
    public UserDO getUserByUid(Long uid) {
        return queryForObject(NAMESPACE + ".getUserByUid", uid);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userInfo, userDO);
        executeInsert(NAMESPACE + ".addUser", userDO);
    }

}
