package com.entity.model;

import com.entity.ShuiguoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 水果
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShuiguoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 商家
     */
    private Integer shangjiaId;


    /**
     * 水果名称
     */
    private String shuiguoName;


    /**
     * 水果编号
     */
    private String shuiguoUuidNumber;


    /**
     * 水果照片
     */
    private String shuiguoPhoto;


    /**
     * 一级分类
     */
    private Integer shuiguoTypes;


    /**
     * 二级分类
     */
    private Integer shuiguoErjiTypes;


    /**
     * 是否推荐
     */
    private Integer tuijianTypes;


    /**
     * 是否特价
     */
    private Integer tejiaTypes;


    /**
     * 是否进口
     */
    private Integer jinkouTypes;


    /**
     * 水果库存
     */
    private Integer shuiguoKucunNumber;


    /**
     * 水果原价
     */
    private Double shuiguoOldMoney;


    /**
     * 现价
     */
    private Double shuiguoNewMoney;


    /**
     * 点击次数
     */
    private Integer shuiguoClicknum;


    /**
     * 水果介绍
     */
    private String shuiguoContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer shuiguoDelete;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：商家
	 */
    public Integer getShangjiaId() {
        return shangjiaId;
    }


    /**
	 * 设置：商家
	 */
    public void setShangjiaId(Integer shangjiaId) {
        this.shangjiaId = shangjiaId;
    }
    /**
	 * 获取：水果名称
	 */
    public String getShuiguoName() {
        return shuiguoName;
    }


    /**
	 * 设置：水果名称
	 */
    public void setShuiguoName(String shuiguoName) {
        this.shuiguoName = shuiguoName;
    }
    /**
	 * 获取：水果编号
	 */
    public String getShuiguoUuidNumber() {
        return shuiguoUuidNumber;
    }


    /**
	 * 设置：水果编号
	 */
    public void setShuiguoUuidNumber(String shuiguoUuidNumber) {
        this.shuiguoUuidNumber = shuiguoUuidNumber;
    }
    /**
	 * 获取：水果照片
	 */
    public String getShuiguoPhoto() {
        return shuiguoPhoto;
    }


    /**
	 * 设置：水果照片
	 */
    public void setShuiguoPhoto(String shuiguoPhoto) {
        this.shuiguoPhoto = shuiguoPhoto;
    }
    /**
	 * 获取：一级分类
	 */
    public Integer getShuiguoTypes() {
        return shuiguoTypes;
    }


    /**
	 * 设置：一级分类
	 */
    public void setShuiguoTypes(Integer shuiguoTypes) {
        this.shuiguoTypes = shuiguoTypes;
    }
    /**
	 * 获取：二级分类
	 */
    public Integer getShuiguoErjiTypes() {
        return shuiguoErjiTypes;
    }


    /**
	 * 设置：二级分类
	 */
    public void setShuiguoErjiTypes(Integer shuiguoErjiTypes) {
        this.shuiguoErjiTypes = shuiguoErjiTypes;
    }
    /**
	 * 获取：是否推荐
	 */
    public Integer getTuijianTypes() {
        return tuijianTypes;
    }


    /**
	 * 设置：是否推荐
	 */
    public void setTuijianTypes(Integer tuijianTypes) {
        this.tuijianTypes = tuijianTypes;
    }
    /**
	 * 获取：是否特价
	 */
    public Integer getTejiaTypes() {
        return tejiaTypes;
    }


    /**
	 * 设置：是否特价
	 */
    public void setTejiaTypes(Integer tejiaTypes) {
        this.tejiaTypes = tejiaTypes;
    }
    /**
	 * 获取：是否进口
	 */
    public Integer getJinkouTypes() {
        return jinkouTypes;
    }


    /**
	 * 设置：是否进口
	 */
    public void setJinkouTypes(Integer jinkouTypes) {
        this.jinkouTypes = jinkouTypes;
    }
    /**
	 * 获取：水果库存
	 */
    public Integer getShuiguoKucunNumber() {
        return shuiguoKucunNumber;
    }


    /**
	 * 设置：水果库存
	 */
    public void setShuiguoKucunNumber(Integer shuiguoKucunNumber) {
        this.shuiguoKucunNumber = shuiguoKucunNumber;
    }
    /**
	 * 获取：水果原价
	 */
    public Double getShuiguoOldMoney() {
        return shuiguoOldMoney;
    }


    /**
	 * 设置：水果原价
	 */
    public void setShuiguoOldMoney(Double shuiguoOldMoney) {
        this.shuiguoOldMoney = shuiguoOldMoney;
    }
    /**
	 * 获取：现价
	 */
    public Double getShuiguoNewMoney() {
        return shuiguoNewMoney;
    }


    /**
	 * 设置：现价
	 */
    public void setShuiguoNewMoney(Double shuiguoNewMoney) {
        this.shuiguoNewMoney = shuiguoNewMoney;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getShuiguoClicknum() {
        return shuiguoClicknum;
    }


    /**
	 * 设置：点击次数
	 */
    public void setShuiguoClicknum(Integer shuiguoClicknum) {
        this.shuiguoClicknum = shuiguoClicknum;
    }
    /**
	 * 获取：水果介绍
	 */
    public String getShuiguoContent() {
        return shuiguoContent;
    }


    /**
	 * 设置：水果介绍
	 */
    public void setShuiguoContent(String shuiguoContent) {
        this.shuiguoContent = shuiguoContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getShuiguoDelete() {
        return shuiguoDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setShuiguoDelete(Integer shuiguoDelete) {
        this.shuiguoDelete = shuiguoDelete;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
