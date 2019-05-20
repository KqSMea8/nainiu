package com.jeecg.n_evaluate.entity;

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
 * @Description: 评价管理
 * @author onlineGenerator
 * @date 2018-01-18 16:58:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "n_evaluate", schema = "")
@SuppressWarnings("serial")
public class NEvaluateEntity implements java.io.Serializable {
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
	/**商品*/
	@Excel(name="商品")
	private java.lang.String goodsId;
	/**商品名称*/
	@Excel(name="商品名称")
	private java.lang.String title;
	/**user_id*/
	@Excel(name="user_id")
	private java.lang.String userId;
	/**用户名称*/
	@Excel(name="用户名称")
	private java.lang.String realname;
	/**用户头像*/
	@Excel(name="用户头像")
	private java.lang.String usernameurl;
	/**评价内容*/
	@Excel(name="评价内容")
	private java.lang.String detaisl;
	/**描述相符*/
	@Excel(name="描述相符")
	private java.lang.String miaosu;
	/**物流服务*/
	@Excel(name="物流服务")
	private java.lang.String logistics;
	/**服务态度*/
	@Excel(name="服务态度")
	private java.lang.String serve;
	/**图片1*/
	@Excel(name="图片1")
	private java.lang.String evaluatepic;
	/**状态*/
	@Excel(name="状态")
	private java.lang.String evaluateStatus;
	/**关联id*/
	@Excel(name="关联id")
	private java.lang.String evaluateid;
	/**评价类型*/
	@Excel(name="评价类型")
	private java.lang.String evaluateType;

	/**是否匿名*/
	@Excel(name="是否匿名")
	private java.lang.Integer anonymous;



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
	@Column(name ="TITLE",nullable=true,length=500)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品名称
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  user_id
	 */
	@Column(name ="USER_ID",nullable=true,length=50)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  user_id
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
	 *@return: java.lang.String  用户头像
	 */
	@Column(name ="USERNAMEURL",nullable=true,length=500)
	public java.lang.String getUsernameurl(){
		return this.usernameurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户头像
	 */
	public void setUsernameurl(java.lang.String usernameurl){
		this.usernameurl = usernameurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评价内容
	 */
	@Column(name ="DETAISL",nullable=true,length=500)
	public java.lang.String getDetaisl(){
		return this.detaisl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评价内容
	 */
	public void setDetaisl(java.lang.String detaisl){
		this.detaisl = detaisl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述相符
	 */
	@Column(name ="MIAOSU",nullable=true,length=32)
	public java.lang.String getMiaosu(){
		return this.miaosu;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述相符
	 */
	public void setMiaosu(java.lang.String miaosu){
		this.miaosu = miaosu;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流服务
	 */
	@Column(name ="LOGISTICS",nullable=true,length=32)
	public java.lang.String getLogistics(){
		return this.logistics;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流服务
	 */
	public void setLogistics(java.lang.String logistics){
		this.logistics = logistics;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务态度
	 */
	@Column(name ="SERVE",nullable=true,length=32)
	public java.lang.String getServe(){
		return this.serve;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务态度
	 */
	public void setServe(java.lang.String serve){
		this.serve = serve;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片1
	 */
	@Column(name ="EVALUATEPIC",nullable=true,length=500)
	public java.lang.String getEvaluatepic(){
		return this.evaluatepic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片1
	 */
	public void setEvaluatepic(java.lang.String evaluatepic){
		this.evaluatepic = evaluatepic;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="EVALUATE_STATUS",nullable=true,length=32)
	public java.lang.String getEvaluateStatus(){
		return this.evaluateStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setEvaluateStatus(java.lang.String evaluateStatus){
		this.evaluateStatus = evaluateStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关联id
	 */
	@Column(name ="EVALUATEID",nullable=true,length=50)
	public java.lang.String getEvaluateid(){
		return this.evaluateid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关联id
	 */
	public void setEvaluateid(java.lang.String evaluateid){
		this.evaluateid = evaluateid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评价类型
	 */
	@Column(name ="EVALUATE_TYPE",nullable=true,length=32)
	public java.lang.String getEvaluateType(){
		return this.evaluateType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评价类型
	 */
	public void setEvaluateType(java.lang.String evaluateType){
		this.evaluateType = evaluateType;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否匿名 0,为匿名，1为非匿名
	 */
	@Column(name ="anonymous",length=1)
	public Integer getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(Integer anonymous) {
		this.anonymous = anonymous;
	}
}
