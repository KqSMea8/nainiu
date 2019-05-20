package com.jeecg.n_user.entity;

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
 * @Description: 用户信息
 * @author onlineGenerator
 * @date 2018-01-30 10:00:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "n_user", schema = "")
@SuppressWarnings("serial")
public class NUserEntity implements java.io.Serializable {
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
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**用户昵称微信*/
	@Excel(name="用户昵称微信")
	private java.lang.String realname;
	/**账号*/
	@Excel(name="账号")
	private java.lang.String username;
	/**手机号*/
	@Excel(name="手机号")
	private java.lang.String phone;
	/**性别*/
	@Excel(name="性别")
	private java.lang.String sex;
	/**常住地*/
	@Excel(name="常住地")
	private java.lang.String address;
	/**常住地编号*/
	@Excel(name="常住地编号")
	private java.lang.String addresscode;
	/**生日*/
	@Excel(name="生日",format = "yyyy-MM-dd")
	private java.util.Date birth;
	/**个性签名*/
	@Excel(name="个性签名")
	private java.lang.String details;
	/**头像*/
	@Excel(name="头像")
	private java.lang.String usernameurl;
	/**用户cid*/
	@Excel(name="用户cid")
	private java.lang.String cid;
	/**微信qq标识*/
	@Excel(name="微信唯一标识")
	private java.lang.String openid;
	/**用户账号金额*/
	@Excel(name="用户账号金额")
	private java.lang.String price;
	/**账号状态*/
	@Excel(name="账号状态")
	private java.lang.String accountStatus;
	/**微信唯一标识*/
	@Excel(name="微信唯一标识")
	private java.lang.String unionid;
	/** 用户是否是会员*/
	private String isMember;
	/** 会员注册时间*/
	private Date  memberStart;
	/** 会员到期时间*/
	private Date memberEnd;
	/** 推荐人数*/
	private Integer totalNumber;
	/** 预留字段1*/
	private String ext1;
	/** 预留字段2*/
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
//	@GeneratedValue(generator = "paymentableGenerator")
//	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
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
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
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
	 *@return: java.lang.String  用户昵称微信
	 */
	@Column(name ="REALNAME",nullable=true,length=100)
	public java.lang.String getRealname(){
		return this.realname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户昵称微信
	 */
	public void setRealname(java.lang.String realname){
		this.realname = realname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号
	 */
	@Column(name ="USERNAME",nullable=true,length=32)
	public java.lang.String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号
	 */
	public void setUsername(java.lang.String username){
		this.username = username;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号
	 */
	@Column(name ="PHONE",nullable=true,length=32)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="SEX",nullable=true,length=32)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  常住地
	 */
	@Column(name ="ADDRESS",nullable=true,length=200)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  常住地
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  常住地编号
	 */
	@Column(name ="ADDRESSCODE",nullable=true,length=200)
	public java.lang.String getAddresscode(){
		return this.addresscode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  常住地编号
	 */
	public void setAddresscode(java.lang.String addresscode){
		this.addresscode = addresscode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生日
	 */
	@Column(name ="BIRTH",nullable=true,length=32)
	public java.util.Date getBirth(){
		return this.birth;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生日
	 */
	public void setBirth(java.util.Date birth){
		this.birth = birth;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  个性签名
	 */
	@Column(name ="DETAILS",nullable=true,length=200)
	public java.lang.String getDetails(){
		return this.details;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  个性签名
	 */
	public void setDetails(java.lang.String details){
		this.details = details;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像
	 */
	@Column(name ="USERNAMEURL",nullable=true,length=1000)
	public java.lang.String getUsernameurl(){
		return this.usernameurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  头像
	 */
	public void setUsernameurl(java.lang.String usernameurl){
		this.usernameurl = usernameurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户cid
	 */
	@Column(name ="CID",nullable=true,length=100)
	public java.lang.String getCid(){
		return this.cid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户cid
	 */
	public void setCid(java.lang.String cid){
		this.cid = cid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信唯一标识
	 */
	@Column(name ="OPENID",nullable=true,length=100)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信唯一标识
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户账号金额
	 */
	@Column(name ="PRICE",nullable=true,length=32)
	public java.lang.String getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户账号金额
	 */
	public void setPrice(java.lang.String price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号状态
	 */
	@Column(name ="ACCOUNT_STATUS",nullable=true,length=32)
	public java.lang.String getAccountStatus(){
		return this.accountStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号状态
	 */
	public void setAccountStatus(java.lang.String accountStatus){
		this.accountStatus = accountStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信唯一标识
	 */
	@Column(name ="UNIONID",nullable=true,length=100)
	public java.lang.String getUnionid(){
		return this.unionid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信唯一标识
	 */
	public void setUnionid(java.lang.String unionid){
		this.unionid = unionid;
	}

	@Column(name = "is_member")
	public String getIsMember() {
		return isMember;
	}

	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}
	@Column(name = "member_start")
	public Date getMemberStart() {
		return memberStart;
	}

	public void setMemberStart(Date memberStart) {
		this.memberStart = memberStart;
	}
	@Column(name = "member_end")
	public Date getMemberEnd() {
		return memberEnd;
	}

	public void setMemberEnd(Date memberEnd) {
		this.memberEnd = memberEnd;
	}
	@Column(name = "total_number")
	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
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
	@Column(name = "ext3")
	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	@Column(name = "ext4")
	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	@Column(name = "ext5")
	public String getExt5() {
		return ext5;
	}

	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
}
