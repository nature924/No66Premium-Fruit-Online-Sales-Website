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
 * 水果
 *
 * @author 
 * @email
 */
@TableName("shuiguo")
public class ShuiguoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShuiguoEntity() {

	}

	public ShuiguoEntity(T t) {
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
     * 商家
     */
    @TableField(value = "shangjia_id")

    private Integer shangjiaId;


    /**
     * 水果名称
     */
    @TableField(value = "shuiguo_name")

    private String shuiguoName;


    /**
     * 水果编号
     */
    @TableField(value = "shuiguo_uuid_number")

    private String shuiguoUuidNumber;


    /**
     * 水果照片
     */
    @TableField(value = "shuiguo_photo")

    private String shuiguoPhoto;


    /**
     * 一级分类
     */
    @TableField(value = "shuiguo_types")

    private Integer shuiguoTypes;


    /**
     * 二级分类
     */
    @TableField(value = "shuiguo_erji_types")

    private Integer shuiguoErjiTypes;


    /**
     * 是否推荐
     */
    @TableField(value = "tuijian_types")

    private Integer tuijianTypes;


    /**
     * 是否特价
     */
    @TableField(value = "tejia_types")

    private Integer tejiaTypes;


    /**
     * 是否进口
     */
    @TableField(value = "jinkou_types")

    private Integer jinkouTypes;


    /**
     * 水果库存
     */
    @TableField(value = "shuiguo_kucun_number")

    private Integer shuiguoKucunNumber;


    /**
     * 水果原价
     */
    @TableField(value = "shuiguo_old_money")

    private Double shuiguoOldMoney;


    /**
     * 现价
     */
    @TableField(value = "shuiguo_new_money")

    private Double shuiguoNewMoney;


    /**
     * 点击次数
     */
    @TableField(value = "shuiguo_clicknum")

    private Integer shuiguoClicknum;


    /**
     * 水果介绍
     */
    @TableField(value = "shuiguo_content")

    private String shuiguoContent;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "shuiguo_delete")

    private Integer shuiguoDelete;


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
	 * 设置：商家
	 */
    public Integer getShangjiaId() {
        return shangjiaId;
    }
    /**
	 * 获取：商家
	 */

    public void setShangjiaId(Integer shangjiaId) {
        this.shangjiaId = shangjiaId;
    }
    /**
	 * 设置：水果名称
	 */
    public String getShuiguoName() {
        return shuiguoName;
    }
    /**
	 * 获取：水果名称
	 */

    public void setShuiguoName(String shuiguoName) {
        this.shuiguoName = shuiguoName;
    }
    /**
	 * 设置：水果编号
	 */
    public String getShuiguoUuidNumber() {
        return shuiguoUuidNumber;
    }
    /**
	 * 获取：水果编号
	 */

    public void setShuiguoUuidNumber(String shuiguoUuidNumber) {
        this.shuiguoUuidNumber = shuiguoUuidNumber;
    }
    /**
	 * 设置：水果照片
	 */
    public String getShuiguoPhoto() {
        return shuiguoPhoto;
    }
    /**
	 * 获取：水果照片
	 */

    public void setShuiguoPhoto(String shuiguoPhoto) {
        this.shuiguoPhoto = shuiguoPhoto;
    }
    /**
	 * 设置：一级分类
	 */
    public Integer getShuiguoTypes() {
        return shuiguoTypes;
    }
    /**
	 * 获取：一级分类
	 */

    public void setShuiguoTypes(Integer shuiguoTypes) {
        this.shuiguoTypes = shuiguoTypes;
    }
    /**
	 * 设置：二级分类
	 */
    public Integer getShuiguoErjiTypes() {
        return shuiguoErjiTypes;
    }
    /**
	 * 获取：二级分类
	 */

    public void setShuiguoErjiTypes(Integer shuiguoErjiTypes) {
        this.shuiguoErjiTypes = shuiguoErjiTypes;
    }
    /**
	 * 设置：是否推荐
	 */
    public Integer getTuijianTypes() {
        return tuijianTypes;
    }
    /**
	 * 获取：是否推荐
	 */

    public void setTuijianTypes(Integer tuijianTypes) {
        this.tuijianTypes = tuijianTypes;
    }
    /**
	 * 设置：是否特价
	 */
    public Integer getTejiaTypes() {
        return tejiaTypes;
    }
    /**
	 * 获取：是否特价
	 */

    public void setTejiaTypes(Integer tejiaTypes) {
        this.tejiaTypes = tejiaTypes;
    }
    /**
	 * 设置：是否进口
	 */
    public Integer getJinkouTypes() {
        return jinkouTypes;
    }
    /**
	 * 获取：是否进口
	 */

    public void setJinkouTypes(Integer jinkouTypes) {
        this.jinkouTypes = jinkouTypes;
    }
    /**
	 * 设置：水果库存
	 */
    public Integer getShuiguoKucunNumber() {
        return shuiguoKucunNumber;
    }
    /**
	 * 获取：水果库存
	 */

    public void setShuiguoKucunNumber(Integer shuiguoKucunNumber) {
        this.shuiguoKucunNumber = shuiguoKucunNumber;
    }
    /**
	 * 设置：水果原价
	 */
    public Double getShuiguoOldMoney() {
        return shuiguoOldMoney;
    }
    /**
	 * 获取：水果原价
	 */

    public void setShuiguoOldMoney(Double shuiguoOldMoney) {
        this.shuiguoOldMoney = shuiguoOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getShuiguoNewMoney() {
        return shuiguoNewMoney;
    }
    /**
	 * 获取：现价
	 */

    public void setShuiguoNewMoney(Double shuiguoNewMoney) {
        this.shuiguoNewMoney = shuiguoNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getShuiguoClicknum() {
        return shuiguoClicknum;
    }
    /**
	 * 获取：点击次数
	 */

    public void setShuiguoClicknum(Integer shuiguoClicknum) {
        this.shuiguoClicknum = shuiguoClicknum;
    }
    /**
	 * 设置：水果介绍
	 */
    public String getShuiguoContent() {
        return shuiguoContent;
    }
    /**
	 * 获取：水果介绍
	 */

    public void setShuiguoContent(String shuiguoContent) {
        this.shuiguoContent = shuiguoContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getShuiguoDelete() {
        return shuiguoDelete;
    }
    /**
	 * 获取：逻辑删除
	 */

    public void setShuiguoDelete(Integer shuiguoDelete) {
        this.shuiguoDelete = shuiguoDelete;
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
        return "Shuiguo{" +
            "id=" + id +
            ", shangjiaId=" + shangjiaId +
            ", shuiguoName=" + shuiguoName +
            ", shuiguoUuidNumber=" + shuiguoUuidNumber +
            ", shuiguoPhoto=" + shuiguoPhoto +
            ", shuiguoTypes=" + shuiguoTypes +
            ", shuiguoErjiTypes=" + shuiguoErjiTypes +
            ", tuijianTypes=" + tuijianTypes +
            ", tejiaTypes=" + tejiaTypes +
            ", jinkouTypes=" + jinkouTypes +
            ", shuiguoKucunNumber=" + shuiguoKucunNumber +
            ", shuiguoOldMoney=" + shuiguoOldMoney +
            ", shuiguoNewMoney=" + shuiguoNewMoney +
            ", shuiguoClicknum=" + shuiguoClicknum +
            ", shuiguoContent=" + shuiguoContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", shuiguoDelete=" + shuiguoDelete +
            ", createTime=" + createTime +
        "}";
    }
}
