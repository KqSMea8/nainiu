package com.jeecg.n_user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName NUserMemberEntity
 * @Desprition 会员树结构组织表
 * @Author shishanshan
 * @Date 2018/11/15 11:42
 * @Version 1.0
 **/
@Entity
@Table(name = "n_user_member", schema = "")
public class NUserMemberEntity implements Serializable{
    private Integer id;
    private String userId;//用户id
    private String pUserId;//父ID
    private String userName;//用户姓名
    private String userUrl;//用户头像链接
    private String pUserName;//父用户名称
    private String pUserUrl;//父用户头像链接
    private Date createDate;//创建时间
    private String type;//类型
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
    @Column(name = "p_user_id")
    public String getpUserId() {
        return pUserId;
    }

    public void setpUserId(String pUserId) {
        this.pUserId = pUserId;
    }
    @Column(name = "user_name")
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
    @Column(name = "p_user_name")
    public String getpUserName() {
        return pUserName;
    }

    public void setpUserName(String pUserName) {
        this.pUserName = pUserName;
    }
    @Column(name = "p_user_url")
    public String getpUserUrl() {
        return pUserUrl;
    }

    public void setpUserUrl(String pUserUrl) {
        this.pUserUrl = pUserUrl;
    }
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
