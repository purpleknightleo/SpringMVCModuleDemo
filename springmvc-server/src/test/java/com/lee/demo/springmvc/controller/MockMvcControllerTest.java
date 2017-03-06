package com.lee.demo.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.lee.demo.springmvc.common.model.CompanyDTO;
import com.lee.demo.springmvc.common.model.UserDTO;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MockMvcController对应的测试类(同时支持JUnit和TestNG，TestNG相关代码暂时注释了)
 * 需要注意的是：@ActiveProfiles(profiles = "dev")需要放在前面，不然不生效
 *
 * Created by hzlifan on 2017/2/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@Test
@ActiveProfiles(profiles = "dev")
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:application.xml")
@Transactional(transactionManager = "transactionManager")
//@ContextHierarchy({
//        @ContextConfiguration(name = "parent", locations = "classpath:application.xml"),
//        @ContextConfiguration(name = "child", locations = "classpath:mvc.xml")
//})
public class MockMvcControllerTest { //extends AbstractTransactionalTestNGSpringContextTests {

    private static final Logger   logger = LoggerFactory.getLogger(MockMvcControllerTest.class);

    private MockMvc               mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    //@BeforeMethod
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 注解@Time表明了最大的执行时间，超时则失败
     *
     * @throws Exception
     */
    @org.junit.Test
    @Timed(millis = 1000)
    public void getName() throws Exception {
        mvc.perform(
            get("/mockmvc/name").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id",
                "-1")).andExpect(status().isOk()).andDo(print());
    }

    @org.junit.Test
    public void getCompany() throws Exception {
        mvc.perform(
            get("/mockmvc/company").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("cid",
                "8")).andExpect(status().isOk());
    }

    @org.junit.Test
    public void getUser() throws Exception {
        Long uid = 2L;

        String jsonRet = mvc
            .perform(
                post("/mockmvc/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                    uid.toString())).andExpect(status().isOk()).andReturn().getResponse()
            .getContentAsString();

        // 解析返回的json
        JSONObject ret = JSONObject.parseObject(jsonRet);
        int code = ret.getInteger("code");
        if (code == 200) {
            logger.info("user info : "
                        + JSONObject.parseObject(ret.getString("ret"), UserDTO.class));
        } else {
            logger.error("error : " + ret.getString("msg"));
        }
    }

    /**
     * 注解@Rollback表明该方法结束后数据库操作会回滚（该注解也可以放在Class前面，这样每个方法都会回滚）
     * 必须在类前面加上@Transactional
     *
     * @throws Exception
     */
    @org.junit.Test
    @Rollback
    public void addCompany() throws Exception {
        CompanyDTO companyDTO = new CompanyDTO(1999L, "ali", "DT");
        String jsonStr = JSONObject.toJSONString(companyDTO);

        // 验证返回的json中code是否为200，如果是多层级的json字段提取采用如$.ret.uid
        mvc.perform(
            post("/mockmvc/company/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                jsonStr)).andExpect(status().isOk()).andExpect(jsonPath("$.code").value(200));
    }

    @org.junit.Test
    public void updateUser() throws Exception {
        Long uid = 4L;
        RequestBuilder request = put("/mockmvc/user/" + uid.toString())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("uid", uid.toString())
            .param("name", "Jason");
        mvc.perform(request).andDo(print()).andExpect(status().isOk());
    }

    @org.junit.Test
    public void delUser() throws Exception {
        Long uid = 4L;
        mvc.perform(
            delete("/mockmvc/user/" + uid.toString()).contentType(
                MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
    }

}
