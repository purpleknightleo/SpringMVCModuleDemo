package com.lee.demo.springmvc.service;

import com.lee.demo.springmvc.common.model.CompanyDTO;

/**
 * 公司服务接口
 *
 * Created by hzlifan on 2017/2/17.
 */
public interface CompanyService {

    void addCompany(CompanyDTO companyDTO);

    CompanyDTO getCompany(Long cid);

    void updateCompany(CompanyDTO companyDTO);

    void deleteCompany(Long cid);

}
