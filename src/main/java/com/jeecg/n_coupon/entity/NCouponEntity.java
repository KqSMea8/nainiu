package com.jeecg.n_coupon.entity;

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
 * @Description: 平台发放活动劵
 * @author onlineGenerator
 * @date 2017-12-19 10:58:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "n_coupon", schema = "")
@SuppressWarnings("serial")
public class NCouponEntity implements java.io.Serializable {
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
	/**优惠劵名称*/
	@Excel(name="优惠劵名称")
	private java.lang.String couponName;
	/**优惠金额*/
	@Excel(name="优惠金额")
	private java.lang.String money;
	/**开始时间*/
	@Excel(name="开始时间",format = "yyyy-MM-dd")
	private java.util.Date startTime;
	/**结束时间*/
	@Excel(name="结束时间",format = "yyyy-MM-dd")
	private java.util.Date endTime;
	/**使用规则*/
	@Excel(name="使用规则")
	private java.lang.String details;
	/**优惠劵类型*/
	@Excel(name="优惠劵类型")
	private java.lang.String couponType;
	/**满多少*/
	@Excel(name="满多少")
	private java.lang.String fullMoney;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String couponStatus;
	/**数量*/
	private java.lang.String numbers;
	
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
	 *@return: java.lang.String  优惠劵名称
	 */
	@Column(name ="COUPON_NAME",nullable=true,length=100)
	public java.lang.String getCouponName(){
		return this.couponName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  优惠劵名称
	 */
	public void setCouponName(java.lang.String couponName){
		this.couponName = couponName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  优惠金额
	 */
	@Column(name ="MONEY",nullable=true,length=32)
	public java.lang.String getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  优惠金额
	 */
	public void setMoney(java.lang.String money){
		this.money = money;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="START_TIME",nullable=true,length=32)
	public java.util.Date getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="END_TIME",nullable=true,length=32)
	public java.util.Date getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用规则
	 */
	@Column(name ="DETAILS",nullable=true,length=200)
	public java.lang.String getDetails(){
		return this.details;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用规则
	 */
	public void setDetails(java.lang.String details){
		this.details = details;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  优惠劵类型
	 */
	@Column(name ="COUPON_TYPE",nullable=true,length=32)
	public java.lang.String getCouponType(){
		return this.couponType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  优惠劵类型
	 */
	public void setCouponType(java.lang.String couponType){
		this.couponType = couponType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  满多少
	 */
	@Column(name ="FULL_MONEY",nullable=true,length=50)
	public java.lang.String getFullMoney(){
		return this.fullMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  满多少
	 */
	public void setFullMoney(java.lang.String fullMoney){
		this.fullMoney = fullMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="COUPON_STATUS",nullable=true,length=50)
	public java.lang.String getCouponStatus(){
		return this.couponStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setCouponStatus(java.lang.String couponStatus){
		this.couponStatus = couponStatus;
	}
	@Column(name ="numbers",nullable=true,length=50)
	public java.lang.String getNumbers() {
		return numbers;
	}

	public void setNumbers(java.lang.String numbers) {
		this.numbers = numbers;
	}
	
}
