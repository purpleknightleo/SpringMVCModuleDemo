package com.lee.demo.springmvc.common.dao;

import com.lee.demo.springmvc.common.dao.domain.CompanyDO;

/**
 * 公司DAO接口
 *
 * Created by hzlifan on 2017/2/17.
 */
public interface CompanyDAO {

    void addCompany(CompanyDO companyDO);

    CompanyDO getCompanyByCid(Long cid);

    void updateCompany(CompanyDO companyDO);

    void deleteCompany(Long cid);

}
