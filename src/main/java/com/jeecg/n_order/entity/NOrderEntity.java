package com.jeecg.n_order.entity;

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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 订单管理
 * @author onlineGenerator
 * @date 2018-01-20 10:46:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "n_order", schema = "")
@SuppressWarnings("serial")
public class NOrderEntity implements java.io.Serializable {
	/**主键*/
	@Excel(name="订单id")
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
	/**用户*/
	@Excel(name="用户")
	private java.lang.String userId;
	/**用户名称*/
	@Excel(name="用户名称")
	private java.lang.String realname;
	/**头像*/
//	@Excel(name="头像")
	private java.lang.String usernameurl;
	/**订单所属活动*/
//	@Excel(name="订单所属活动")
	private java.lang.String marketingOne;
	/**商品类型*/
//	@Excel(name="商品类型")
	private java.lang.String goodsDetaislType;
	/**商家*/
//	@Excel(name="商家")
	private java.lang.String merchantId;
	/**商品*/
//	@Excel(name="商品")
	private java.lang.String goodsId;
	/**商品名称*/
	@Excel(name="商品名称")
	private java.lang.String goodsname;
	/**订单状态*/
//	@Excel(name="订单状态")
	private java.lang.String orderStatus;
	/**订单类型*/
//	@Excel(name="订单类型")
	private java.lang.String orderType;
	/**售后状态*/
//	@Excel(name="售后状态")
	private java.lang.String aftersaleStatus;
	/**售后类型*/
//	@Excel(name="售后类型")
	private java.lang.String aftersaleType;
	/**商品总价*/
	@Excel(name="商品总价")
	private java.lang.String goodsSum;
	/**数量*/
	@Excel(name="数量")
	private java.lang.String numbers;
	/**支付金额*/
//	@Excel(name="支付金额")
	private java.lang.String paySum;

	/**售后原因*/
//	@Excel(name="售后原因")
	private java.lang.String details;
	/**商家回复*/
//	@Excel(name="商家回复")
	private java.lang.String merchantBack;
	/**商家名称*/
//	@Excel(name="商家名称")
	private java.lang.String merchantname;
	/**拼单类型*/
	@Excel(name="是否团长")
	private java.lang.String joinordertype;
	/**拼单状态*/
//	@Excel(name="拼单状态")
	private java.lang.String joinorderstatus;
	/**拼单id*/
	@Excel(name="团购ID")
	private java.lang.String orderid;
	/**拼单状态*/
//	@Excel(name="拼单状态")
	private java.lang.String ptstatus;
	/**结束时间*/
//	@Excel(name="结束时间",format = "yyyy-MM-dd")
	private java.util.Date endTime;
	/**支付类型*/
//	@Excel(name="支付类型")
	private java.lang.String payorderstatus;
	/**优惠券id*/
//	@Excel(name="优惠券id")
	private java.lang.String couponid;

	/**个人账户金额*/
//	@Excel(name="个人账户金额")
	private java.lang.String userprice;
	/**付款状态*/
//	@Excel(name="付款状态")
	private java.lang.String paystatus;
	/**规格id*/
//	@Excel(name="规格id")
	private java.lang.String standardid;
	/**规格名称*/
	@Excel(name="规格名称")
	private java.lang.String standardname;
	/**商品图片*/
//	@Excel(name="商品图片")
	private java.lang.String picurl;
	/**支付方式*/
//	@Excel(name="支付方式")
	private java.lang.String paymode;
	/**拼单成功时间*/
//	@Excel(name="拼单成功时间",format = "yyyy-MM-dd")
	private java.util.Date pdtime;
	/**申请售后时间*/
//	@Excel(name="申请售后时间",format = "yyyy-MM-dd")
	private java.util.Date aftersaletime;
	private java.util.Date applytime;
	/**发货时间*/
//	@Excel(name="发货时间",format = "yyyy-MM-dd")
	private java.util.Date sendtime;
	/**同意退款或者不同意的时间*/
//	@Excel(name="同意退款或者不同意的时间",format = "yyyy-MM-dd")
	private java.util.Date moneytime;
	/**确认收货时间*/
//	@Excel(name="确认收货时间",format = "yyyy-MM-dd")
	private java.util.Date acceptime;
	/**快递费用*/
	private java.lang.String expressmoney;
	/**商家收款状态*/
	private java.lang.String gatheringstatus;
	/**退款操作状态*/
	private java.lang.String exitmoneystatus;
	/**快递单号*/
	@Excel(name="快递单号")
	private java.lang.String expressNub;
	/**快递名称*/
	@Excel(name="快递名称")
	private java.lang.String expressName;
	/**邮寄地址id*/
//	@Excel(name="邮寄地址id")
	private java.lang.String addressid;
	/**手机号*/
	@Excel(name="收件人手机号")
	private java.lang.String phone;
	/**收件人*/
	@Excel(name="收件人")
	private java.lang.String acceptname;
	/**地区*/
	@Excel(name="邮寄地区")
	private java.lang.String area;
	/**编码*/
	private java.lang.String areacode;
	/**详细地址*/
	@Excel(name="详细地址")
	private java.lang.String address;
	
	/**拼团人*/
	private java.lang.String ptPerson;
	/**是否虚拟*/
	private java.lang.String isvirtual;
	/**申请说明*/
	private java.lang.String aftersaleDetails;
	/**联系电话*/
	private java.lang.String aftersalePhone;
	/**申请图片*/
	private java.lang.String aftersalePic;
	/**申请平台介入*/
	private java.lang.String ptjoin;
	private java.lang.String islook;
	/**商品市场价*/
	private java.lang.String bazaarPrice;
	/**充值状态*/
	private java.lang.String gameState;
	/**发放红包状态*/
	private java.lang.String redResultCode;
	/**红包金额*/
	private java.lang.String totalAmount;
	//一级
	private String oneLevelUser;
	//二级
	private String twoLevelUser;
	//三级
	private String threeLevelUser;
	//是否选择推荐
	private String isReferral;
	//推荐金额
	private String orderReferralBonus;
	//是否是会员用户
	private String isMemberUser;
	//是否是会员商品
	private String isMemberGoods;
	/**团购订单剩余未退款人数*/
	@Excel(name="未退款人数")
	private java.lang.String openid;
	@Transient
	public java.lang.String getOpenid() {
		return openid;
	}

	public void setOpenid(java.lang.String openid) {
		this.openid = openid;
	}
	@Column(name ="red_result_code",nullable=true)
	public java.lang.String getRedResultCode() {
		return redResultCode;
	}

	public void setRedResultCode(java.lang.String redResultCode) {
		this.redResultCode = redResultCode;
	}
	@Column(name ="total_amount",nullable=true)
	public java.lang.String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(java.lang.String totalAmount) {
		this.totalAmount = totalAmount;
	}

	
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
	 *@return: java.lang.String  用户
	 */
	@Column(name ="USER_ID",nullable=true,length=50)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户名称
	 */
	@Column(name ="REALNAME",nullable=true,length=100)
	public java.lang.String getRealname(){
		return this.realname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名称
	 */
	public void setRealname(java.lang.String realname){
		this.realname = realname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像
	 */
	@Column(name ="USERNAMEURL",nullable=true,length=200)
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
	 *@return: java.lang.String  订单所属活动
	 */
	@Column(name ="MARKETING_ONE",nullable=true,length=32)
	public java.lang.String getMarketingOne(){
		return this.marketingOne;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单所属活动
	 */
	public void setMarketingOne(java.lang.String marketingOne){
		this.marketingOne = marketingOne;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品类型
	 */
	@Column(name ="GOODS_DETAISL_TYPE",nullable=true,length=32)
	public java.lang.String getGoodsDetaislType(){
		return this.goodsDetaislType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品类型
	 */
	public void setGoodsDetaislType(java.lang.String goodsDetaislType){
		this.goodsDetaislType = goodsDetaislType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商家
	 */
	@Column(name ="MERCHANT_ID",nullable=true,length=50)
	public java.lang.String getMerchantId(){
		return this.merchantId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家
	 */
	public void setMerchantId(java.lang.String merchantId){
		this.merchantId = merchantId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品
	 */
	@Column(name ="GOODS_ID",nullable=true,length=50)
	public java.lang.String getGoodsId(){
		return this.goodsId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品
	 */
	public void setGoodsId(java.lang.String goodsId){
		this.goodsId = goodsId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品名称
	 */
	@Column(name ="GOODSNAME",nullable=true,length=200)
	public java.lang.String getGoodsname(){
		return this.goodsname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品名称
	 */
	public void setGoodsname(java.lang.String goodsname){
		this.goodsname = goodsname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单状态
	 */
	@Column(name ="ORDER_STATUS",nullable=true,length=32)
	public java.lang.String getOrderStatus(){
		return this.orderStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单状态
	 */
	public void setOrderStatus(java.lang.String orderStatus){
		this.orderStatus = orderStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单类型
	 */
	@Column(name ="ORDER_TYPE",nullable=true,length=32)
	public java.lang.String getOrderType(){
		return this.orderType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单类型
	 */
	public void setOrderType(java.lang.String orderType){
		this.orderType = orderType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  售后状态
	 */
	@Column(name ="AFTERSALE_STATUS",nullable=true,length=32)
	public java.lang.String getAftersaleStatus(){
		return this.aftersaleStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  售后状态
	 */
	public void setAftersaleStatus(java.lang.String aftersaleStatus){
		this.aftersaleStatus = aftersaleStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  售后类型
	 */
	@Column(name ="AFTERSALE_TYPE",nullable=true,length=32)
	public java.lang.String getAftersaleType(){
		return this.aftersaleType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  售后类型
	 */
	public void setAftersaleType(java.lang.String aftersaleType){
		this.aftersaleType = aftersaleType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品总价
	 */
	@Column(name ="GOODS_SUM",nullable=true,length=32)
	public java.lang.String getGoodsSum(){
		return this.goodsSum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品总价
	 */
	public void setGoodsSum(java.lang.String goodsSum){
		this.goodsSum = goodsSum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数量
	 */
	@Column(name ="NUMBERS",nullable=true,length=32)
	public java.lang.String getNumbers(){
		return this.numbers;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数量
	 */
	public void setNumbers(java.lang.String numbers){
		this.numbers = numbers;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付金额
	 */
	@Column(name ="PAY_SUM",nullable=true,length=32)
	public java.lang.String getPaySum(){
		return this.paySum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付金额
	 */
	public void setPaySum(java.lang.String paySum){
		this.paySum = paySum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  快递单号
	 */
	@Column(name ="EXPRESS_NUB",nullable=true,length=100)
	public java.lang.String getExpressNub(){
		return this.expressNub;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  快递单号
	 */
	public void setExpressNub(java.lang.String expressNub){
		this.expressNub = expressNub;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  快递名称
	 */
	@Column(name ="EXPRESS_NAME",nullable=true,length=1000)
	public java.lang.String getExpressName(){
		return this.expressName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  快递名称
	 */
	public void setExpressName(java.lang.String expressName){
		this.expressName = expressName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  售后原因
	 */
	@Column(name ="DETAILS",nullable=true,length=1000)
	public java.lang.String getDetails(){
		return this.details;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  售后原因
	 */
	public void setDetails(java.lang.String details){
		this.details = details;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商家回复
	 */
	@Column(name ="MERCHANT_BACK",nullable=true,length=1000)
	public java.lang.String getMerchantBack(){
		return this.merchantBack;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家回复
	 */
	public void setMerchantBack(java.lang.String merchantBack){
		this.merchantBack = merchantBack;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商家名称
	 */
	@Column(name ="MERCHANTNAME",nullable=true,length=200)
	public java.lang.String getMerchantname(){
		return this.merchantname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家名称
	 */
	public void setMerchantname(java.lang.String merchantname){
		this.merchantname = merchantname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拼单类型
	 */
	@Column(name ="JOINORDERTYPE",nullable=true,length=36)
	public java.lang.String getJoinordertype(){
		return this.joinordertype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拼单类型
	 */
	public void setJoinordertype(java.lang.String joinordertype){
		this.joinordertype = joinordertype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拼单状态
	 */
	@Column(name ="JOINORDERSTATUS",nullable=true,length=36)
	public java.lang.String getJoinorderstatus(){
		return this.joinorderstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拼单状态
	 */
	public void setJoinorderstatus(java.lang.String joinorderstatus){
		this.joinorderstatus = joinorderstatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拼单id
	 */
	@Column(name ="ORDERID",nullable=true,length=36)
	public java.lang.String getOrderid(){
		return this.orderid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拼单id
	 */
	public void setOrderid(java.lang.String orderid){
		this.orderid = orderid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拼单状态
	 */
	@Column(name ="PTSTATUS",nullable=true,length=36)
	public java.lang.String getPtstatus(){
		return this.ptstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拼单状态
	 */
	public void setPtstatus(java.lang.String ptstatus){
		this.ptstatus = ptstatus;
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
	 *@return: java.lang.String  支付类型
	 */
	@Column(name ="PAYORDERSTATUS",nullable=true,length=32)
	public java.lang.String getPayorderstatus(){
		return this.payorderstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付类型
	 */
	public void setPayorderstatus(java.lang.String payorderstatus){
		this.payorderstatus = payorderstatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  优惠券id
	 */
	@Column(name ="COUPONID",nullable=true,length=36)
	public java.lang.String getCouponid(){
		return this.couponid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  优惠券id
	 */
	public void setCouponid(java.lang.String couponid){
		this.couponid = couponid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮寄地址id
	 */
	@Column(name ="ADDRESSID",nullable=true,length=36)
	public java.lang.String getAddressid(){
		return this.addressid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮寄地址id
	 */
	public void setAddressid(java.lang.String addressid){
		this.addressid = addressid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  个人账户金额
	 */
	@Column(name ="USERPRICE",nullable=true,length=32)
	public java.lang.String getUserprice(){
		return this.userprice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  个人账户金额
	 */
	public void setUserprice(java.lang.String userprice){
		this.userprice = userprice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款状态
	 */
	@Column(name ="PAYSTATUS",nullable=true,length=32)
	public java.lang.String getPaystatus(){
		return this.paystatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款状态
	 */
	public void setPaystatus(java.lang.String paystatus){
		this.paystatus = paystatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格id
	 */
	@Column(name ="STANDARDID",nullable=true,length=50)
	public java.lang.String getStandardid(){
		return this.standardid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格id
	 */
	public void setStandardid(java.lang.String standardid){
		this.standardid = standardid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格名称
	 */
	@Column(name ="STANDARDNAME",nullable=true,length=500)
	public java.lang.String getStandardname(){
		return this.standardname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格名称
	 */
	public void setStandardname(java.lang.String standardname){
		this.standardname = standardname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品图片
	 */
	@Column(name ="PICURL",nullable=true,length=500)
	public java.lang.String getPicurl(){
		return this.picurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品图片
	 */
	public void setPicurl(java.lang.String picurl){
		this.picurl = picurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付方式
	 */
	@Column(name ="PAYMODE",nullable=true,length=36)
	public java.lang.String getPaymode(){
		return this.paymode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付方式
	 */
	public void setPaymode(java.lang.String paymode){
		this.paymode = paymode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  拼单成功时间
	 */
	@Column(name ="PDTIME",nullable=true,length=32)
	public java.util.Date getPdtime(){
		return this.pdtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  拼单成功时间
	 */
	public void setPdtime(java.util.Date pdtime){
		this.pdtime = pdtime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date 下订单时间
	 */
	@Column(name ="APPLYTIME",nullable=true,length=32)
	public java.util.Date getApplytime(){
		return this.applytime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下订单时间
	 */
	public void setApplytime(java.util.Date applytime){
		this.applytime = applytime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  申请售后时间
	 */
	@Column(name ="aftersaletime",nullable=true,length=32)
	public java.util.Date getAftersaletime(){
		return this.aftersaletime;
	}
	
	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申请售后时间
	 */
	public void setAftersaletime(java.util.Date aftersaletime){
		this.aftersaletime = aftersaletime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发货时间
	 */
	@Column(name ="SENDTIME",nullable=true,length=32)
	public java.util.Date getSendtime(){
		return this.sendtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发货时间
	 */
	public void setSendtime(java.util.Date sendtime){
		this.sendtime = sendtime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  同意退款或者不同意的时间
	 */
	@Column(name ="MONEYTIME",nullable=true,length=32)
	public java.util.Date getMoneytime(){
		return this.moneytime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  同意退款或者不同意的时间
	 */
	public void setMoneytime(java.util.Date moneytime){
		this.moneytime = moneytime;
	}
	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  确认收货时间
	 */
	@Column(name ="acceptime",nullable=true,length=32)
	public java.util.Date getAcceptime() {
		return acceptime;
	}

	public void setAcceptime(java.util.Date acceptime) {
		this.acceptime = acceptime;
	}
	@Column(name ="expressmoney",nullable=true)
	public java.lang.String getExpressmoney() {
		return expressmoney;
	}

	public void setExpressmoney(java.lang.String expressmoney) {
		this.expressmoney = expressmoney;
	}
	/**商家收款状态*/
	@Column(name ="gatheringstatus",nullable=true)
	public java.lang.String getGatheringstatus() {
		return gatheringstatus;
	}

	public void setGatheringstatus(java.lang.String gatheringstatus) {
		this.gatheringstatus = gatheringstatus;
	}
	/**退款操作状态*/
	@Column(name ="exitmoneystatus",nullable=true)
	public java.lang.String getExitmoneystatus() {
		return exitmoneystatus;
	}

	public void setExitmoneystatus(java.lang.String exitmoneystatus) {
		this.exitmoneystatus = exitmoneystatus;
	}
	/**拼团人*/
	@Column(name ="pt_person",nullable=true)
	public java.lang.String getPtPerson() {
		return ptPerson;
	}

	public void setPtPerson(java.lang.String ptPerson) {
		this.ptPerson = ptPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地区
	 */
	@Column(name ="AREA",nullable=true,length=200)
	public java.lang.String getArea(){
		return this.area;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地区
	 */
	public void setArea(java.lang.String area){
		this.area = area;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编码
	 */
	@Column(name ="AREACODE",nullable=true,length=200)
	public java.lang.String getAreacode(){
		return this.areacode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编码
	 */
	public void setAreacode(java.lang.String areacode){
		this.areacode = areacode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  详细地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=200)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  详细地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
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
	 *@return: java.lang.String  收件人
	 */
	@Column(name ="ACCEPTNAME",nullable=true,length=100)
	public java.lang.String getAcceptname(){
		return this.acceptname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收件人
	 */
	public void setAcceptname(java.lang.String acceptname){
		this.acceptname = acceptname;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String 是否虚拟
	 */
	@Column(name ="isvirtual",nullable=true,length=32)
	public java.lang.String getIsvirtual() {
		return isvirtual;
	}

	public void setIsvirtual(java.lang.String isvirtual) {
		this.isvirtual = isvirtual;
	}
	@Column(name ="aftersale_details",nullable=true)
	public java.lang.String getAftersaleDetails() {
		return aftersaleDetails;
	}

	public void setAftersaleDetails(java.lang.String aftersaleDetails) {
		this.aftersaleDetails = aftersaleDetails;
	}
	@Column(name ="aftersale_phone",nullable=true)
	public java.lang.String getAftersalePhone() {
		return aftersalePhone;
	}

	public void setAftersalePhone(java.lang.String aftersalePhone) {
		this.aftersalePhone = aftersalePhone;
	}
	@Column(name ="aftersale_pic",nullable=true)
	public java.lang.String getAftersalePic() {
		return aftersalePic;
	}

	public void setAftersalePic(java.lang.String aftersalePic) {
		this.aftersalePic = aftersalePic;
	}

	@Column(name ="ptjoin",nullable=true)
	public java.lang.String getPtjoin() {
		return ptjoin;
	}

	public void setPtjoin(java.lang.String ptjoin) {
		this.ptjoin = ptjoin;
	}
	@Column(name ="islook",nullable=true)
	public java.lang.String getIslook() {
		return islook;
	}

	public void setIslook(java.lang.String islook) {
		this.islook = islook;
	}
	@Column(name ="bazaar_price",nullable=true)
	public java.lang.String getBazaarPrice() {
		return bazaarPrice;
	}

	public void setBazaarPrice(java.lang.String bazaarPrice) {
		this.bazaarPrice = bazaarPrice;
	}
	@Column(name ="game_state",nullable=true)
	public java.lang.String getGameState() {
		return gameState;
	}

	public void setGameState(java.lang.String gameState) {
		this.gameState = gameState;
	}
	@Column(name = "one_level_user")
	public String getOneLevelUser() {
		return oneLevelUser;
	}

	public void setOneLevelUser(String oneLevelUser) {
		this.oneLevelUser = oneLevelUser;
	}
	@Column(name = "two_level_user")
	public String getTwoLevelUser() {
		return twoLevelUser;
	}

	public void setTwoLevelUser(String twoLevelUser) {
		this.twoLevelUser = twoLevelUser;
	}
	@Column(name = "three_level_user")
	public String getThreeLevelUser() {
		return threeLevelUser;
	}

	public void setThreeLevelUser(String threeLevelUser) {
		this.threeLevelUser = threeLevelUser;
	}
	@Column(name = "is_referral")
	public String getIsReferral() {
		return isReferral;
	}

	public void setIsReferral(String isReferral) {
		this.isReferral = isReferral;
	}
	@Column(name = "order_referral_bonus")
	public String getOrderReferralBonus() {
		return orderReferralBonus;
	}

	public void setOrderReferralBonus(String orderReferralBonus) {
		this.orderReferralBonus = orderReferralBonus;
	}
	@Column(name = "is_member_user")
	public String getIsMemberUser() {
		return isMemberUser;
	}

	public void setIsMemberUser(String isMemberUser) {
		this.isMemberUser = isMemberUser;
	}
	@Column(name = "is_member_goods")
	public String getIsMemberGoods() {
		return isMemberGoods;
	}

	public void setIsMemberGoods(String isMemberGoods) {
		this.isMemberGoods = isMemberGoods;
	}

	@Override
	public String toString() {
		return "NOrderEntity{" +
				"id='" + id + '\'' +
				", createName='" + createName + '\'' +
				", createBy='" + createBy + '\'' +
				", createDate=" + createDate +
				", updateName='" + updateName + '\'' +
				", updateBy='" + updateBy + '\'' +
				", updateDate=" + updateDate +
				", sysOrgCode='" + sysOrgCode + '\'' +
				", remarks='" + remarks + '\'' +
				", delFlag='" + delFlag + '\'' +
				", bpmStatus='" + bpmStatus + '\'' +
				", userId='" + userId + '\'' +
				", realname='" + realname + '\'' +
				", usernameurl='" + usernameurl + '\'' +
				", marketingOne='" + marketingOne + '\'' +
				", goodsDetaislType='" + goodsDetaislType + '\'' +
				", merchantId='" + merchantId + '\'' +
				", goodsId='" + goodsId + '\'' +
				", goodsname='" + goodsname + '\'' +
				", orderStatus='" + orderStatus + '\'' +
				", orderType='" + orderType + '\'' +
				", aftersaleStatus='" + aftersaleStatus + '\'' +
				", aftersaleType='" + aftersaleType + '\'' +
				", goodsSum='" + goodsSum + '\'' +
				", numbers='" + numbers + '\'' +
				", paySum='" + paySum + '\'' +
				", details='" + details + '\'' +
				", merchantBack='" + merchantBack + '\'' +
				", merchantname='" + merchantname + '\'' +
				", joinordertype='" + joinordertype + '\'' +
				", joinorderstatus='" + joinorderstatus + '\'' +
				", orderid='" + orderid + '\'' +
				", ptstatus='" + ptstatus + '\'' +
				", endTime=" + endTime +
				", payorderstatus='" + payorderstatus + '\'' +
				", couponid='" + couponid + '\'' +
				", userprice='" + userprice + '\'' +
				", paystatus='" + paystatus + '\'' +
				", standardid='" + standardid + '\'' +
				", standardname='" + standardname + '\'' +
				", picurl='" + picurl + '\'' +
				", paymode='" + paymode + '\'' +
				", pdtime=" + pdtime +
				", aftersaletime=" + aftersaletime +
				", applytime=" + applytime +
				", sendtime=" + sendtime +
				", moneytime=" + moneytime +
				", acceptime=" + acceptime +
				", expressmoney='" + expressmoney + '\'' +
				", gatheringstatus='" + gatheringstatus + '\'' +
				", exitmoneystatus='" + exitmoneystatus + '\'' +
				", expressNub='" + expressNub + '\'' +
				", expressName='" + expressName + '\'' +
				", addressid='" + addressid + '\'' +
				", phone='" + phone + '\'' +
				", acceptname='" + acceptname + '\'' +
				", area='" + area + '\'' +
				", areacode='" + areacode + '\'' +
				", address='" + address + '\'' +
				", ptPerson='" + ptPerson + '\'' +
				", isvirtual='" + isvirtual + '\'' +
				", aftersaleDetails='" + aftersaleDetails + '\'' +
				", aftersalePhone='" + aftersalePhone + '\'' +
				", aftersalePic='" + aftersalePic + '\'' +
				", ptjoin='" + ptjoin + '\'' +
				", islook='" + islook + '\'' +
				", bazaarPrice='" + bazaarPrice + '\'' +
				", gameState='" + gameState + '\'' +
				", redResultCode='" + redResultCode + '\'' +
				", totalAmount='" + totalAmount + '\'' +
				", oneLevelUser='" + oneLevelUser + '\'' +
				", twoLevelUser='" + twoLevelUser + '\'' +
				", threeLevelUser='" + threeLevelUser + '\'' +
				'}';
	}
}
