package com.lee.demo.springmvc.controller;

import com.lee.demo.springmvc.common.model.CompanyDTO;
import com.lee.demo.springmvc.service.CompanyService;
import com.lee.demo.springmvc.utils.BeanUtils;
import com.lee.demo.springmvc.utils.PackUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 公司相关控制类
 *
 * Created by hzlifan on 2017/2/17.
 */
@Controller
@RequestMapping(value = "/xhr/company")
public class CompanyController {

    private static Logger  logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    /**
     * 添加公司信息
     *
     * @param companyDTO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody Map<String, Object> addCompany(@RequestBody CompanyDTO companyDTO) {
        Long cid = companyDTO.getCid();
        logger.info("add company, cid = {}", cid);
        try {
            if (isParamLegal(companyDTO)) {
                PackUtils.genErrorResponse(310, "param error");
            }
            companyService.addCompany(companyDTO);
            return PackUtils.genSuccessResponse();
        } catch (Exception e) {
            logger.error("fail to add company, cid = {}", cid, e);
        }
        return PackUtils.genErrorResponse(300, "internal error");
    }

    /**
     * 修改公司信息
     *
     * @param companyDTO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody Map<String, Object> updateCompany(@RequestBody CompanyDTO companyDTO) {
        Long cid = companyDTO.getCid();
        logger.info("update company, cid = {}", cid);
        try {
            if (isParamLegal(companyDTO)) {
                PackUtils.genErrorResponse(310, "param error");
            }
            companyService.updateCompany(companyDTO);
            return PackUtils.genSuccessResponse();
        } catch (Exception e) {
            logger.error("fail to update company, cid = {}", cid, e);
        }
        return PackUtils.genErrorResponse(300, "internal error");
    }

    /**
     * 删除公司信息
     *
     * @param cid
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody Map<String, Object> deleteCompany(@RequestBody Long cid) {
        logger.info("delete company, cid = {}", cid);
        try {
            if (cid == null) {
                PackUtils.genErrorResponse(310, "param error");
            }
            companyService.deleteCompany(cid);
            return PackUtils.genSuccessResponse();
        } catch (Exception e) {
            logger.error("fail to delete company, cid = {} ", cid, e);
        }
        return PackUtils.genErrorResponse(300, "internal error");
    }

    /**
     * 获取公司信息
     *
     * @param cid
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody Map<String, Object> getCompany(@RequestBody Long cid) {
        logger.info("get company, cid = {}", cid);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (cid == null) {
                PackUtils.genErrorResponse(310, "param error");
            }
            CompanyDTO companyDTO = companyService.getCompany(cid);
            if (companyDTO != null) {
                map = BeanUtils.bean2Map(companyDTO);
            }
            return PackUtils.genSuccessResponse(map);
        } catch (Exception e) {
            logger.error("fail to get company, cid = {}", cid, e);
        }
        return PackUtils.genErrorResponse(300, "internal error");
    }

    /**
     * 检查参数合法性
     *
     * @param companyDTO
     * @return
     */
    private boolean isParamLegal(CompanyDTO companyDTO) {
        if (companyDTO.getCid() == null || StringUtils.isBlank(companyDTO.getName())
            || StringUtils.isBlank(companyDTO.getIndustry())) {
            return true;
        }
        return false;
    }

}
