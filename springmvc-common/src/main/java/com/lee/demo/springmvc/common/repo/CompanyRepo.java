package com.lee.demo.springmvc.common.repo;

import com.lee.demo.springmvc.common.dao.domain.CompanyDO;

/**
 * 公司缓存接口
 *
 * Created by hzlifan on 2017/2/17.
 */
public interface CompanyRepo {

    void addCompany(CompanyDO companyDO);

    CompanyDO getCompanyByCid(Long cid);

    void updateCompany(CompanyDO companyDO);

    void deleteCompany(Long cid);

}
