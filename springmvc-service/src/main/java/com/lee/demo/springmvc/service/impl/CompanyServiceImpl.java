package com.lee.demo.springmvc.service.impl;

import com.lee.demo.springmvc.common.dao.CompanyDAO;
import com.lee.demo.springmvc.common.dao.domain.CompanyDO;
import com.lee.demo.springmvc.common.model.CompanyDTO;
import com.lee.demo.springmvc.common.repo.CompanyRepo;
import com.lee.demo.springmvc.service.CompanyService;
import com.lee.demo.springmvc.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公司服务实现类
 *
 * Created by hzlifan on 2017/2/17.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger =  LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepo         companyRepo;

    @Autowired
    private CompanyDAO          companyDAO;

    @Override
    public void addCompany(CompanyDTO companyDTO) {
        CompanyDO companyDO = new CompanyDO();
        BeanUtils.copyProperties(companyDTO, companyDO);

        /** 先插数据库 */
        companyDAO.addCompany(companyDO);
        /** 再插缓存 */
        companyRepo.addCompany(companyDO);
    }

    @Override
    public CompanyDTO getCompany(Long cid) {
        CompanyDO companyDO = null;
        /** 先从缓存取 */
        companyDO = companyRepo.getCompanyByCid(cid);
        /** 再从数据库取 */
        if (companyDO == null) {
            logger.debug("company info not exist in redis, get from db, cid = " + cid);
            companyDO = companyDAO.getCompanyByCid(cid);
            if (companyDO != null) {
                companyRepo.addCompany(companyDO); // put it into redis
            }
        }
        if (companyDO == null) {
            return null;
        }

        CompanyDTO companyDTO = new CompanyDTO();
        BeanUtils.copyProperties(companyDO, companyDTO);
        return companyDTO;
    }

    @Override
    public void updateCompany(CompanyDTO companyDTO) {
        CompanyDO companyDO = new CompanyDO();
        BeanUtils.copyProperties(companyDTO, companyDO);

        /** 先修改数据库 */
        companyDAO.updateCompany(companyDO);
        /** 再修改缓存 */
        companyRepo.updateCompany(companyDO);
    }

    @Override
    public void deleteCompany(Long cid) {
        /** 先删除数据库 */
        companyDAO.deleteCompany(cid);
        /** 再删除缓存 */
        companyRepo.deleteCompany(cid);
    }

}
