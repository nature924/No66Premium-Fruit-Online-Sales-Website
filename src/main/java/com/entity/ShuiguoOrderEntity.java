package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 水果订单
 *
 * @author 
 * @email
 */
@TableName("shuiguo_order")
public class ShuiguoOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShuiguoOrderEntity() {

	}

	public ShuiguoOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 订单号
     */
    @TableField(value = "shuiguo_order_uuid_number")

    private String shuiguoOrderUuidNumber;


    /**
     * 收货地址
     */
    @TableField(value = "address_id")

    private Integer addressId;


    /**
     * 水果
     */
    @TableField(value = "shuiguo_id")

    private Integer shuiguoId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 购买数量
     */
    @TableField(value = "buy_number")

    private Integer buyNumber;


    /**
     * 实付价格
     */
    @TableField(value = "shuiguo_order_true_price")

    private Double shuiguoOrderTruePrice;


    /**
     * 快递公司
     */
    @TableField(value = "shuiguo_order_courier_name")

    private String shuiguoOrderCourierName;


    /**
     * 订单快递单号
     */
    @TableField(value = "shuiguo_order_courier_number")

    private String shuiguoOrderCourierNumber;


    /**
     * 订单类型
     */
    @TableField(value = "shuiguo_order_types")

    private Integer shuiguoOrderTypes;


    /**
     * 支付类型
     */
    @TableField(value = "shuiguo_order_payment_types")

    private Integer shuiguoOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getShuiguoOrderUuidNumber() {
        return shuiguoOrderUuidNumber;
    }
    /**
	 * 获取：订单号
	 */

    public void setShuiguoOrderUuidNumber(String shuiguoOrderUuidNumber) {
        this.shuiguoOrderUuidNumber = shuiguoOrderUuidNumber;
    }
    /**
	 * 设置：收货地址
	 */
    public Integer getAddressId() {
        return addressId;
    }
    /**
	 * 获取：收货地址
	 */

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
	 * 设置：水果
	 */
    public Integer getShuiguoId() {
        return shuiguoId;
    }
    /**
	 * 获取：水果
	 */

    public void setShuiguoId(Integer shuiguoId) {
        this.shuiguoId = shuiguoId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }
    /**
	 * 获取：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getShuiguoOrderTruePrice() {
        return shuiguoOrderTruePrice;
    }
    /**
	 * 获取：实付价格
	 */

    public void setShuiguoOrderTruePrice(Double shuiguoOrderTruePrice) {
        this.shuiguoOrderTruePrice = shuiguoOrderTruePrice;
    }
    /**
	 * 设置：快递公司
	 */
    public String getShuiguoOrderCourierName() {
        return shuiguoOrderCourierName;
    }
    /**
	 * 获取：快递公司
	 */

    public void setShuiguoOrderCourierName(String shuiguoOrderCourierName) {
        this.shuiguoOrderCourierName = shuiguoOrderCourierName;
    }
    /**
	 * 设置：订单快递单号
	 */
    public String getShuiguoOrderCourierNumber() {
        return shuiguoOrderCourierNumber;
    }
    /**
	 * 获取：订单快递单号
	 */

    public void setShuiguoOrderCourierNumber(String shuiguoOrderCourierNumber) {
        this.shuiguoOrderCourierNumber = shuiguoOrderCourierNumber;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getShuiguoOrderTypes() {
        return shuiguoOrderTypes;
    }
    /**
	 * 获取：订单类型
	 */

    public void setShuiguoOrderTypes(Integer shuiguoOrderTypes) {
        this.shuiguoOrderTypes = shuiguoOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getShuiguoOrderPaymentTypes() {
        return shuiguoOrderPaymentTypes;
    }
    /**
	 * 获取：支付类型
	 */

    public void setShuiguoOrderPaymentTypes(Integer shuiguoOrderPaymentTypes) {
        this.shuiguoOrderPaymentTypes = shuiguoOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ShuiguoOrder{" +
            "id=" + id +
            ", shuiguoOrderUuidNumber=" + shuiguoOrderUuidNumber +
            ", addressId=" + addressId +
            ", shuiguoId=" + shuiguoId +
            ", yonghuId=" + yonghuId +
            ", buyNumber=" + buyNumber +
            ", shuiguoOrderTruePrice=" + shuiguoOrderTruePrice +
            ", shuiguoOrderCourierName=" + shuiguoOrderCourierName +
            ", shuiguoOrderCourierNumber=" + shuiguoOrderCourierNumber +
            ", shuiguoOrderTypes=" + shuiguoOrderTypes +
            ", shuiguoOrderPaymentTypes=" + shuiguoOrderPaymentTypes +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
