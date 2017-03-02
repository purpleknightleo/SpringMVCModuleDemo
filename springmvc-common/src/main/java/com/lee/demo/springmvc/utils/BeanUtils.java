package com.lee.demo.springmvc.utils;

import com.google.common.collect.Maps;
import net.sf.cglib.beans.BeanMap;

import java.util.Map;

/**
 * Bean工具类
 *
 * Created by hzlifan on 2017/2/20.
 */
public class BeanUtils {

    /**
     * 利用org.springframework.beans.BeanUtils完成bean之间的拷贝
     *
     * @param source
     * @param target
     * @param ignores 忽略的字段数组
     */
    public static void copyProperties(Object source, Object target, String[] ignores) {
        if (source == null || target == null) {
            return;
        }
        org.springframework.beans.BeanUtils.copyProperties(source, target, ignores);
    }

    /**
     * 全字段拷贝
     *
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, null);
    }

    /**
     * 将bean转换为map
     * @param bean
     * @return
     */
    public static Map<String, Object> bean2Map(Object bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key.toString(), beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为bean
     *
     * @param map
     * @param bean
     * @return
     */
    public static Object map2Bean(Map<String, Object> map, Object bean) {
        if (map != null) {
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(map);
        }
        return bean;
    }

}
