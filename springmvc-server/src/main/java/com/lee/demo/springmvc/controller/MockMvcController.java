package com.lee.demo.springmvc.controller;

import com.lee.demo.springmvc.common.model.CompanyDTO;
import com.lee.demo.springmvc.common.model.UserDTO;
import com.lee.demo.springmvc.service.CompanyService;
import com.lee.demo.springmvc.service.UserService;
import com.lee.demo.springmvc.utils.BeanUtils;
import com.lee.demo.springmvc.utils.PackUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * MockMvc相关控制器，对应测试类为MockMvcControllerTest
 * @EnableWebMvc必须加，不然POST请求会报415
 *
 * Created by hzlifan on 2017/2/28.
 */
@Controller
@EnableWebMvc
@RequestMapping(value = "/mockmvc")
public class MockMvcController {

    private static final Logger logger = LoggerFactory.getLogger(MockMvcController.class);

    @Autowired
    private UserService         userService;

    @Autowired
    private CompanyService      companyService;

    /**
     * 通过id查询name
     * 测试内容：GET请求，直接获取参数，并返回String
     *
     * @return
     */
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public @ResponseBody String getName(@RequestParam("id") Long id) {
        if (id > 0) {
            return "kobe";
        }
        return "bryant";
    }

    /**
     * 通过cid查询company
     * 测试内容：GET请求，通过request获取参数，并返回String
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public @ResponseBody String getCompany(HttpServletRequest request) {
        Long cid = Long.valueOf(request.getParameter("cid"));
        if (cid > 0) {
            return "51";
        }
        return "NE";
    }

    /**
     * 通过uid获取用户信息
     * 测试内容：POST查询请求，参数只有一个基本类型，并返回json
     *
     * @param uid
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody Map<String, Object> getUser(@RequestBody Long uid) {
        logger.info("get user, uid = {}", uid);

        UserDTO userDTO = null;
        Map<String, Object> map = null;
        try {
            if (uid == null) {
                PackUtils.genErrorResponse(310, "param error");
            }
            userDTO = userService.getUserInfo(uid);
            if (userDTO != null) {
                map = BeanUtils.bean2Map(userDTO);
            } else {
                return PackUtils.genErrorResponse(400, "user not exist");
            }
            return PackUtils.genSuccessResponse(map);
        } catch (Exception e) {
            logger.error("fail to get user, uid = {}", uid, e);
        }
        return PackUtils.genErrorResponse(300, "query error");
    }

    /**
     * 添加公司
     * 测试内容：POST写请求，参数为bean，并返回json
     *
     * @param companyDTO
     * @return
     */
    @RequestMapping(value = "/company/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody Map<String, Object> addCompany(@RequestBody CompanyDTO companyDTO) {
        Long cid = companyDTO.getCid();
        logger.info("add company, cid = {}", cid);
        try {
            companyService.addCompany(companyDTO);
            return PackUtils.genSuccessResponse();
        } catch (Exception e) {
            logger.error("fail to add company, cid = {}", cid, e);
        }
        return PackUtils.genErrorResponse(300, "internal error");
    }

}
