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
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * 快递信息
 *
 */

public class ExpressEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**快递单号*/
	@Excel(name="快递编号")
	private java.lang.String expressNub;
	/**快递名称*/
	@Excel(name="快递名称")
	private java.lang.String expressName;
	
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getExpressNub() {
		return expressNub;
	}
	public void setExpressNub(java.lang.String expressNub) {
		this.expressNub = expressNub;
	}
	public java.lang.String getExpressName() {
		return expressName;
	}
	public void setExpressName(java.lang.String expressName) {
		this.expressName = expressName;
	}
	
	
	
}
