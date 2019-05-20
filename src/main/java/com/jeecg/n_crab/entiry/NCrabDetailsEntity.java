package com.jeecg.n_crab.entiry;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName NCrabDetailsEntity
 * @Desprition 螃蟹信息表
 * @Author shishanshan
 * @Date 2018/11/21 14:36
 * @Version 1.0
 **/
@Entity
@Table(name = "n_crab_details_entity")
public class NCrabDetailsEntity {
    private Integer id;
    private Integer crabId;//螃蟹id
    private Integer number;//数量
    private String crabSource;//螃蟹来源 "0" :系统赠送, "1" 购买会员赠送, 2:待定
    private Date createDate;//创建时间
    private String todayEarnings; //今日收益
    private String recordType;//记录类型 0: 螃蟹来源 1:螃蟹收益 2:螃蟹喂养
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "crab_id")
    public Integer getCrabId() {
        return crabId;
    }

    public void setCrabId(Integer crabId) {
        this.crabId = crabId;
    }
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    @Column(name = "crab_source")
    public String getCrabSource() {
        return crabSource;
    }

    public void setCrabSource(String crabSource) {
        this.crabSource = crabSource;
    }
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column(name = "today_earnings")
    public String getTodayEarnings() {
        return todayEarnings;
    }

    public void setTodayEarnings(String todayEarnings) {
        this.todayEarnings = todayEarnings;
    }
    @Column(name = "record_type")
    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}
