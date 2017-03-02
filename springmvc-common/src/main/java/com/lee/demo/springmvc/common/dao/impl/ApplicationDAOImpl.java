package com.lee.demo.springmvc.common.dao.impl;

import com.lee.demo.springmvc.common.dao.domain.ApplicationDO;
import com.lee.demo.springmvc.common.model.ApplicationInfo;
import com.lee.demo.springmvc.common.dao.ApplicationDAO;
import com.lee.demo.springmvc.common.dao.BaseDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by hzlifan on 2017/2/10.
 */
@Repository
public class ApplicationDAOImpl extends BaseDAO<ApplicationDO> implements ApplicationDAO {

    private static final String NAMESPACE = "com.lee.demo.springmvc.common.dao.ApplicationDAO";

    @Override
    public void insertRecord(ApplicationInfo applicationInfo) {
        ApplicationDO applicationDO = new ApplicationDO();
        BeanUtils.copyProperties(applicationInfo, applicationDO);
        executeInsert(NAMESPACE + ".insertRecord", applicationDO);
    }
}
