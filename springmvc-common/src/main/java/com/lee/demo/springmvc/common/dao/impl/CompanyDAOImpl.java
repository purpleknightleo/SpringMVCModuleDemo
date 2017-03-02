package com.lee.demo.springmvc.common.dao.impl;

import com.lee.demo.springmvc.common.dao.CompanyDAO;
import com.lee.demo.springmvc.common.dao.BaseDAO;
import com.lee.demo.springmvc.common.dao.domain.CompanyDO;
import org.springframework.stereotype.Repository;

/**
 * 公司DAO实现类
 *
 * Created by hzlifan on 2017/2/17.
 */
@Repository
public class CompanyDAOImpl extends BaseDAO<CompanyDO> implements CompanyDAO {

    private static final String NAMESPACE = "com.lee.demo.springmvc.common.dao.CompanyDAO";

    @Override
    public void addCompany(CompanyDO companyDO) {
        executeInsert(NAMESPACE + ".addCompany", companyDO);
    }

    @Override
    public CompanyDO getCompanyByCid(Long cid) {
        return queryForObject(NAMESPACE + ".getCompanyByCid", cid);
    }

    @Override
    public void updateCompany(CompanyDO companyDO) {
        executeUpdate(NAMESPACE + ".updateCompany", companyDO);
    }

    @Override
    public void deleteCompany(Long cid) {
        executeDelete(NAMESPACE + ".deleteCompany", cid);
    }

}
