package com.lee.demo.springmvc.utils;

import com.lee.demo.springmvc.common.dao.domain.UserDO;
import com.lee.demo.springmvc.common.model.UserDTO;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hzlifan on 2017/2/10.
 */
@Test
public class BeanUtilsTest {

    public void test1() {
        Assert.assertEquals(1, 1);
    }

    public void testSpringBeanCopy() {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(new UserDO(123L, "kb", "abc"), userDTO,
            new String[] { "password" });
        System.out.println("uid = " + userDTO.getUid() + ", name = " + userDTO.getName());
    }

}
