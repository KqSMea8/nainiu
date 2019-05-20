package com.jeecg.weixin_pay.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 微信支付参数配置
 * @author onlineGenerator
 * @date 2018-08-08 15:31:55
 * @version V1.0   
 *
 */
@Entity
@Table(name = "weixin_pay", schema = "")
@SuppressWarnings("serial")
public class WeixinPayEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**备注信息*/
	private java.lang.String remarks;
	/**删除标记*/
	private java.lang.String delFlag;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**标题*/
	@Excel(name="标题")
	private java.lang.String title;
	/**开发者ID*/
	@Excel(name="开发者ID")
	private java.lang.String appid;
	/**开发者密码*/
	@Excel(name="开发者密码")
	private java.lang.String appsecret;
	/**商户号*/
	@Excel(name="商户号")
	private java.lang.String partner;
	/**微信商户平台api安全*/
	@Excel(name="微信商户平台api安全")
	private java.lang.String partnerkey;
	/**微信通知地址*/
	@Excel(name="微信通知地址")
	private java.lang.String notifyurl;
	/**是否生效*/
	@Excel(name="是否生效")
	private java.lang.String wxpaystatus;
	/**公众号或者app*/
	@Excel(name="公众号或者app")
	private java.lang.String wxpaytype;
	//奖励1 一级奖励
	private String rewardOne;
	//奖励2  二级奖励
	private String rewardTwo;
	//奖励3 三级奖励
	private String rewardThree;
	//预留字段  金牌会员浮动
	private String ext1;
	//预留字段  银牌会员浮动
	private String ext2;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注信息
	 */
	@Column(name ="REMARKS",nullable=true,length=255)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注信息
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  删除标记
	 */
	@Column(name ="DEL_FLAG",nullable=true,length=1)
	public java.lang.String getDelFlag(){
		return this.delFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  删除标记
	 */
	public void setDelFlag(java.lang.String delFlag){
		this.delFlag = delFlag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题
	 */
	@Column(name ="TITLE",nullable=true,length=300)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开发者ID
	 */
	@Column(name ="APPID",nullable=true,length=300)
	public java.lang.String getAppid(){
		return this.appid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开发者ID
	 */
	public void setAppid(java.lang.String appid){
		this.appid = appid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开发者密码
	 */
	@Column(name ="APPSECRET",nullable=true,length=300)
	public java.lang.String getAppsecret(){
		return this.appsecret;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开发者密码
	 */
	public void setAppsecret(java.lang.String appsecret){
		this.appsecret = appsecret;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户号
	 */
	@Column(name ="PARTNER",nullable=true,length=300)
	public java.lang.String getPartner(){
		return this.partner;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户号
	 */
	public void setPartner(java.lang.String partner){
		this.partner = partner;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信商户平台api安全
	 */
	@Column(name ="PARTNERKEY",nullable=true,length=300)
	public java.lang.String getPartnerkey(){
		return this.partnerkey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信商户平台api安全
	 */
	public void setPartnerkey(java.lang.String partnerkey){
		this.partnerkey = partnerkey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信通知地址
	 */
	@Column(name ="NOTIFYURL",nullable=true,length=300)
	public java.lang.String getNotifyurl(){
		return this.notifyurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信通知地址
	 */
	public void setNotifyurl(java.lang.String notifyurl){
		this.notifyurl = notifyurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否生效
	 */
	@Column(name ="WXPAYSTATUS",nullable=true,length=32)
	public java.lang.String getWxpaystatus(){
		return this.wxpaystatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否生效
	 */
	public void setWxpaystatus(java.lang.String wxpaystatus){
		this.wxpaystatus = wxpaystatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公众号或者app
	 */
	@Column(name ="WXPAYTYPE",nullable=true,length=32)
	public java.lang.String getWxpaytype(){
		return this.wxpaytype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公众号或者app
	 */
	public void setWxpaytype(java.lang.String wxpaytype){
		this.wxpaytype = wxpaytype;
	}
    @Column(name = "reward_one")
	public String getRewardOne() {
		return rewardOne;
	}

	public void setRewardOne(String rewardOne) {
		this.rewardOne = rewardOne;
	}
	@Column(name = "reward_two")
	public String getRewardTwo() {
		return rewardTwo;
	}

	public void setRewardTwo(String rewardTwo) {
		this.rewardTwo = rewardTwo;
	}
	@Column(name = "reward_three")
	public String getRewardThree() {
		return rewardThree;
	}

	public void setRewardThree(String rewardThree) {
		this.rewardThree = rewardThree;
	}

	@Column(name = "ext1")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	@Column(name = "ext2")
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
}
