package com.lee.demo.springmvc.controller;

import com.google.common.collect.Maps;
import com.lee.demo.dubbo.service.facade.DemoFacade;
import com.lee.demo.springmvc.utils.PackUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 测试相关控制类
 *
 * Created by hzlifan on 2017/2/21.
 */
@Controller
@RequestMapping(value = "/test")
public class MiscController {

    private static final Logger logger = LoggerFactory.getLogger(MiscController.class);

    @Autowired
    private DemoFacade          demoFacade;

    /**
     * 测试Dubbo，URI类似127.0.0.1:8080/test/dubbo?value=kobe
     *
     * @param value
     * @return
     */
    @RequestMapping(value = "/dubbo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody Map<String, Object> testDubbo(@RequestParam("value") String value) {

        logger.info("test dubbo, value = " + value);

        Map<String, Object> map = Maps.newHashMap();
        try {
            String str = demoFacade.test(value);
            map.put("str", str);
            return PackUtils.genSuccessResponse(map);
        } catch (Exception e) {
            logger.error("fail to call remote dubbo service", e);
        }
        return PackUtils.genErrorResponse(300, "query error");
    }

}
