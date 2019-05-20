package com.jeecg.n_merchant.entity;

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
 * @Description: 商家信息表
 * @author onlineGenerator
 * @date 2017-12-31 10:42:05
 * @version V1.0   
 *
 */
@Entity
@Table(name = "n_merchant", schema = "")
@SuppressWarnings("serial")
public class NMerchantEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
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
	/**公司名称*/
	@Excel(name="公司名称")
	private java.lang.String company;
	/**开店类别*/
	@Excel(name="开店类别")
	private java.lang.String shoptype;
	/**入驻姓名*/
	@Excel(name="入驻姓名")
	private java.lang.String joinname;
	/**入驻邮箱*/
	@Excel(name="入驻邮箱")
	private java.lang.String email;
	/**手机号*/
	@Excel(name="手机号")
	private java.lang.String phone;
	/**身份证号*/
	@Excel(name="身份证号")
	private java.lang.String card;
	/**紧急联系人姓名*/
	@Excel(name="紧急联系人姓名")
	private java.lang.String urgencyName;
	/**紧急联系人手机*/
	@Excel(name="紧急联系人手机")
	private java.lang.String urgencyPhone;
	/**开店性质*/
	@Excel(name="开店性质")
	private java.lang.String startshoptype;
	/**是否存在第三方平台*/
	@Excel(name="是否存在第三方平台")
	private java.lang.String shifou;
	/**身份证正面*/
	@Excel(name="身份证正面")
	private java.lang.String cardZUrl;
	/**身份证反面*/
	@Excel(name="身份证反面")
	private java.lang.String cardFUrl;
	/**手持身份证半身照片*/
	@Excel(name="手持身份证半身照片")
	private java.lang.String cardBUrl;
	/**法人手机号*/
	@Excel(name="法人手机号")
	private java.lang.String bossPhone;
	/**法人邮箱*/
	@Excel(name="法人邮箱")
	private java.lang.String bossEmail;
	/**统一社会信用代码*/
	@Excel(name="统一社会信用代码")
	private java.lang.String org;
	/**营业执照*/
	@Excel(name="营业执照")
	private java.lang.String orgUrl;
	/**开户行许可证*/
	@Excel(name="开户行许可证")
	private java.lang.String bankUrl;
	/**缴费*/
	@Excel(name="缴费")
	private java.lang.String ispay;
	/**账号类型*/
	@Excel(name="账号类型")
	private java.lang.String accountType;
	/**父类id*/
	@Excel(name="父类id")
	private java.lang.String pid;
	/**角色*/
	@Excel(name="角色")
	private java.lang.String role;
	/**可提现余额*/
	@Excel(name="可提现余额")
	private java.lang.String actionMoney;
	/**冻结余额*/
	@Excel(name="冻结余额")
	private java.lang.String freezeMoney;
	/**账号状态*/
	@Excel(name="账号状态")
	private java.lang.String accountStatus;
	/**审核信息*/
	@Excel(name="审核信息")
	private java.lang.String auditType;
	/**创建日期*/
	private java.util.Date createDate;
	/**主营类目*/
	@Excel(name="主营类目")
	private java.lang.String jingying;
	/**主营类目名称*/
	@Excel(name="主营类目名称")
	private java.lang.String jingyingname;
	/**开店详情*/
	@Excel(name="开店详情")
	private java.lang.String details;
	/**店铺logo*/
	@Excel(name="店铺logo")
	private java.lang.String merchantlogo;
	/**审核内容*/
	@Excel(name="审核内容")
	private java.lang.String auditContent;
	/**保证金金额*/
	private java.lang.String depositMoney;
	/**客服电话*/
	private java.lang.String tel;
	/**客服编码*/
	private java.lang.String sysNum;
	
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
	 *@return: java.lang.String  公司名称
	 */
	@Column(name ="COMPANY",nullable=true,length=500)
	public java.lang.String getCompany(){
		return this.company;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司名称
	 */
	public void setCompany(java.lang.String company){
		this.company = company;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开店类别
	 */
	@Column(name ="SHOPTYPE",nullable=true,length=32)
	public java.lang.String getShoptype(){
		return this.shoptype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开店类别
	 */
	public void setShoptype(java.lang.String shoptype){
		this.shoptype = shoptype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入驻姓名
	 */
	@Column(name ="JOINNAME",nullable=true,length=32)
	public java.lang.String getJoinname(){
		return this.joinname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入驻姓名
	 */
	public void setJoinname(java.lang.String joinname){
		this.joinname = joinname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入驻邮箱
	 */
	@Column(name ="EMAIL",nullable=true,length=100)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入驻邮箱
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
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
	 *@return: java.lang.String  身份证号
	 */
	@Column(name ="CARD",nullable=true,length=32)
	public java.lang.String getCard(){
		return this.card;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号
	 */
	public void setCard(java.lang.String card){
		this.card = card;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  紧急联系人姓名
	 */
	@Column(name ="URGENCY_NAME",nullable=true,length=32)
	public java.lang.String getUrgencyName(){
		return this.urgencyName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  紧急联系人姓名
	 */
	public void setUrgencyName(java.lang.String urgencyName){
		this.urgencyName = urgencyName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  紧急联系人手机
	 */
	@Column(name ="URGENCY_PHONE",nullable=true,length=32)
	public java.lang.String getUrgencyPhone(){
		return this.urgencyPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  紧急联系人手机
	 */
	public void setUrgencyPhone(java.lang.String urgencyPhone){
		this.urgencyPhone = urgencyPhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开店性质
	 */
	@Column(name ="STARTSHOPTYPE",nullable=true,length=32)
	public java.lang.String getStartshoptype(){
		return this.startshoptype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开店性质
	 */
	public void setStartshoptype(java.lang.String startshoptype){
		this.startshoptype = startshoptype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否存在第三方平台
	 */
	@Column(name ="SHIFOU",nullable=true,length=32)
	public java.lang.String getShifou(){
		return this.shifou;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否存在第三方平台
	 */
	public void setShifou(java.lang.String shifou){
		this.shifou = shifou;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证正面
	 */
	@Column(name ="CARD_Z_URL",nullable=true,length=500)
	public java.lang.String getCardZUrl(){
		return this.cardZUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证正面
	 */
	public void setCardZUrl(java.lang.String cardZUrl){
		this.cardZUrl = cardZUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证反面
	 */
	@Column(name ="CARD_F_URL",nullable=true,length=500)
	public java.lang.String getCardFUrl(){
		return this.cardFUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证反面
	 */
	public void setCardFUrl(java.lang.String cardFUrl){
		this.cardFUrl = cardFUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手持身份证半身照片
	 */
	@Column(name ="CARD_B_URL",nullable=true,length=500)
	public java.lang.String getCardBUrl(){
		return this.cardBUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手持身份证半身照片
	 */
	public void setCardBUrl(java.lang.String cardBUrl){
		this.cardBUrl = cardBUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法人手机号
	 */
	@Column(name ="BOSS_PHONE",nullable=true,length=32)
	public java.lang.String getBossPhone(){
		return this.bossPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法人手机号
	 */
	public void setBossPhone(java.lang.String bossPhone){
		this.bossPhone = bossPhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法人邮箱
	 */
	@Column(name ="BOSS_EMAIL",nullable=true,length=100)
	public java.lang.String getBossEmail(){
		return this.bossEmail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法人邮箱
	 */
	public void setBossEmail(java.lang.String bossEmail){
		this.bossEmail = bossEmail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  统一社会信用代码
	 */
	@Column(name ="ORG",nullable=true,length=100)
	public java.lang.String getOrg(){
		return this.org;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  统一社会信用代码
	 */
	public void setOrg(java.lang.String org){
		this.org = org;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  营业执照
	 */
	@Column(name ="ORG_URL",nullable=true,length=500)
	public java.lang.String getOrgUrl(){
		return this.orgUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  营业执照
	 */
	public void setOrgUrl(java.lang.String orgUrl){
		this.orgUrl = orgUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户行许可证
	 */
	@Column(name ="BANK_URL",nullable=true,length=500)
	public java.lang.String getBankUrl(){
		return this.bankUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户行许可证
	 */
	public void setBankUrl(java.lang.String bankUrl){
		this.bankUrl = bankUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缴费
	 */
	@Column(name ="ISPAY",nullable=true,length=32)
	public java.lang.String getIspay(){
		return this.ispay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缴费
	 */
	public void setIspay(java.lang.String ispay){
		this.ispay = ispay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号类型
	 */
	@Column(name ="ACCOUNT_TYPE",nullable=true,length=32)
	public java.lang.String getAccountType(){
		return this.accountType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号类型
	 */
	public void setAccountType(java.lang.String accountType){
		this.accountType = accountType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  父类id
	 */
	@Column(name ="PID",nullable=true,length=32)
	public java.lang.String getPid(){
		return this.pid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父类id
	 */
	public void setPid(java.lang.String pid){
		this.pid = pid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  角色
	 */
	@Column(name ="ROLE",nullable=true,length=32)
	public java.lang.String getRole(){
		return this.role;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  角色
	 */
	public void setRole(java.lang.String role){
		this.role = role;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  可提现余额
	 */
	@Column(name ="ACTION_MONEY",nullable=true,length=32)
	public java.lang.String getActionMoney(){
		return this.actionMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  可提现余额
	 */
	public void setActionMoney(java.lang.String actionMoney){
		this.actionMoney = actionMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  冻结余额
	 */
	@Column(name ="FREEZE_MONEY",nullable=true,length=32)
	public java.lang.String getFreezeMoney(){
		return this.freezeMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  冻结余额
	 */
	public void setFreezeMoney(java.lang.String freezeMoney){
		this.freezeMoney = freezeMoney;
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
	 *@return: java.lang.String  审核信息
	 */
	@Column(name ="AUDIT_TYPE",nullable=true,length=32)
	public java.lang.String getAuditType(){
		return this.auditType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核信息
	 */
	public void setAuditType(java.lang.String auditType){
		this.auditType = auditType;
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
	 *@return: java.lang.String  主营类目
	 */
	@Column(name ="JINGYING",nullable=true,length=50)
	public java.lang.String getJingying(){
		return this.jingying;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主营类目
	 */
	public void setJingying(java.lang.String jingying){
		this.jingying = jingying;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主营类目名称
	 */
	@Column(name ="JINGYINGNAME",nullable=true,length=200)
	public java.lang.String getJingyingname(){
		return this.jingyingname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主营类目名称
	 */
	public void setJingyingname(java.lang.String jingyingname){
		this.jingyingname = jingyingname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开店详情
	 */
	@Column(name ="DETAILS",nullable=true,length=500)
	public java.lang.String getDetails(){
		return this.details;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开店详情
	 */
	public void setDetails(java.lang.String details){
		this.details = details;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  店铺logo
	 */
	@Column(name ="MERCHANTLOGO",nullable=true,length=200)
	public java.lang.String getMerchantlogo(){
		return this.merchantlogo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  店铺logo
	 */
	public void setMerchantlogo(java.lang.String merchantlogo){
		this.merchantlogo = merchantlogo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核内容
	 */
	@Column(name ="AUDIT_CONTENT",nullable=true,length=1000)
	public java.lang.String getAuditContent(){
		return this.auditContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核内容
	 */
	public void setAuditContent(java.lang.String auditContent){
		this.auditContent = auditContent;
	}
	@Column(name ="deposit_money",nullable=true)
	public java.lang.String getDepositMoney() {
		return depositMoney;
	}

	public void setDepositMoney(java.lang.String depositMoney) {
		this.depositMoney = depositMoney;
	}
	@Column(name ="tel",nullable=true)
	public java.lang.String getTel() {
		return tel;
	}

	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}

	@Column(name ="sysNum",nullable=true)
	public java.lang.String getSysNum() {
		return sysNum;
	}

	public void setSysNum(java.lang.String sysNum) {
		this.sysNum = sysNum;
	}
	
}
