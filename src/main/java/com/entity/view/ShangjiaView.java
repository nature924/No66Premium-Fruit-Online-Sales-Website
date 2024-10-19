package com.entity.view;

import com.entity.ShangjiaEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 商家
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("shangjia")
public class ShangjiaView extends ShangjiaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 商家信用类型的值
		*/
		private String shangjiaXingjiValue;
		/**
		* 审核状态的值
		*/
		private String shangjiaYesnoValue;



	public ShangjiaView() {

	}

	public ShangjiaView(ShangjiaEntity shangjiaEntity) {
		try {
			BeanUtils.copyProperties(this, shangjiaEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 商家信用类型的值
			*/
			public String getShangjiaXingjiValue() {
				return shangjiaXingjiValue;
			}
			/**
			* 设置： 商家信用类型的值
			*/
			public void setShangjiaXingjiValue(String shangjiaXingjiValue) {
				this.shangjiaXingjiValue = shangjiaXingjiValue;
			}
			/**
			* 获取： 审核状态的值
			*/
			public String getShangjiaYesnoValue() {
				return shangjiaYesnoValue;
			}
			/**
			* 设置： 审核状态的值
			*/
			public void setShangjiaYesnoValue(String shangjiaYesnoValue) {
				this.shangjiaYesnoValue = shangjiaYesnoValue;
			}














}
