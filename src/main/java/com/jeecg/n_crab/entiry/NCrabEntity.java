package com.jeecg.n_crab.entiry;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName NCrabEntity
 * @Desprition 螃蟹属性实体类
 * @Author shishanshan
 * @Date 2018/11/21 11:29
 * @Version 1.0
 **/
@Entity
@Table(name = "n_crab_entity",schema = "")
public class NCrabEntity {
    private Integer id;
    private String userId;//用户id
    private String userName;//用户姓名
    private String userUrl;//用户url
    private Date createDate;//螃蟹创建时间
    private Integer crabNumber;//螃蟹数量
    private String carbEarnings;//螃蟹收益
    private String crabTotalEarnings;//螃蟹收益
    private Date collectDate;//收取时间
    private Date feedDate;//喂养时间
    private String earningsType;//收益类型(普通-会员) 0会员 1普通
    private String openClose;// 螃蟹收取喂养开关 openColse 0:表示已喂养  1:标识需要喂养
    private String deleteStatus;//删除状态
    private String randomPrice;//随机金额

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Column(name ="user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Column(name = "user_url")
    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "crab_number")
    public Integer getCrabNumber() {
        return crabNumber;
    }

    public void setCrabNumber(Integer crabNumber) {
        this.crabNumber = crabNumber;
    }
    @Column(name = "carb_earnings")
    public String getCarbEarnings() {
        return carbEarnings;
    }

    public void setCarbEarnings(String carbEarnings) {
        this.carbEarnings = carbEarnings;
    }
    @Column(name = "collect_date")
    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }
    @Column(name = "feed_date")
    public Date getFeedDate() {
        return feedDate;
    }

    public void setFeedDate(Date feedDate) {
        this.feedDate = feedDate;
    }
    @Column(name = "earnings_type")
    public String getEarningsType() {
        return earningsType;
    }

    public void setEarningsType(String earningsType) {
        this.earningsType = earningsType;
    }
    @Column(name = "crab_total_earnings")
    public String getCrabTotalEarnings() {
        return crabTotalEarnings;
    }

    public void setCrabTotalEarnings(String crabTotalEarnings) {
        this.crabTotalEarnings = crabTotalEarnings;
    }
    @Column(name = "open_close")
    public String getOpenClose() {
        return openClose;
    }

    public void setOpenClose(String openClose) {
        this.openClose = openClose;
    }
    @Column(name = "delete_status")
    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
    @Column(name = "random_price")
    public String getRandomPrice() {
        return randomPrice;
    }

    public void setRandomPrice(String randomPrice) {
        this.randomPrice = randomPrice;
    }
}
