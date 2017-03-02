package com.lee.demo.springmvc.common.model;

/**
 * 公司DO
 *
 * Created by hzlifan on 2017/2/17.
 */
public class CompanyDTO {

    private Long   cid;

    private String name;

    private String industry;

    public CompanyDTO() {
    }

    public CompanyDTO(Long cid, String name, String industry) {
        this.cid = cid;
        this.name = name;
        this.industry = industry;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
}
