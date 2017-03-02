package com.lee.demo.springmvc.common.repo.impl;

import com.lee.demo.springmvc.common.dao.domain.CompanyDO;
import com.lee.demo.springmvc.common.repo.BaseRepo;
import com.lee.demo.springmvc.common.repo.CompanyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公司缓存实现类
 *
 * Created by hzlifan on 2017/2/17.
 */
@Repository
public class CompanyRepoImpl extends BaseRepo implements CompanyRepo {

    private static final Logger logger = LoggerFactory.getLogger(CompanyRepoImpl.class);

    private static final String TAG    = "Company_TAG";

    /**
     * 使用@Transactional实现事务
     *
     * @param companyDO
     */
    @Transactional
    @Override
    public void addCompany(CompanyDO companyDO) {
        try {
            // 增加company信息
            hset(TAG, companyDO.getCid().toString(), companyDO);
            // 计数器更新
            incr("Company_counter");
        } catch (Exception e) {
            logger.error("fail to add company info to redis, cid = {}", companyDO.getCid(), e);
        }
    }

    @Override
    public CompanyDO getCompanyByCid(Long cid) {
        try {
            return hget(TAG, cid.toString(), CompanyDO.class);
        } catch (Exception e) {
            logger.error("fail to get company info from redis, cid = {}", cid, e);
        }
        return null;
    }

    @Override
    public void updateCompany(CompanyDO companyDO) {
        try {
            hset(TAG, companyDO.getCid().toString(), companyDO);
        } catch (Exception e) {
            logger.error("fail to update company info in redis, cid = {}", companyDO.getCid(), e);
        }
    }

    @Override
    public void deleteCompany(Long cid) {
        try {
            hdel(TAG, cid.toString());
        } catch (Exception e) {
            logger.error("fail to delete company info from redis, cid = {}", cid, e);
        }
    }

}
