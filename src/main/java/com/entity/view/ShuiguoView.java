package com.entity.view;

import com.entity.ShuiguoEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 水果
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("shuiguo")
public class ShuiguoView extends ShuiguoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 一级分类的值
		*/
		private String shuiguoValue;
		/**
		* 二级分类的值
		*/
		private String shuiguoErjiValue;
		/**
		* 是否推荐的值
		*/
		private String tuijianValue;
		/**
		* 是否特价的值
		*/
		private String tejiaValue;
		/**
		* 是否进口的值
		*/
		private String jinkouValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



		//级联表 shangjia
			/**
			* 商家名称
			*/
			private String shangjiaName;
			/**
			* 联系方式
			*/
			private String shangjiaPhone;
			/**
			* 邮箱
			*/
			private String shangjiaEmail;
			/**
			* 营业执照展示
			*/
			private String shangjiaPhoto;
			/**
			* 商家信用类型
			*/
			private Integer shangjiaXingjiTypes;
				/**
				* 商家信用类型的值
				*/
				private String shangjiaXingjiValue;
			/**
			* 现有余额
			*/
			private Double newMoney;
			/**
			* 商家简介
			*/
			private String shangjiaContent;
			/**
			* 审核状态
			*/
			private Integer shangjiaYesnoTypes;
				/**
				* 审核状态的值
				*/
				private String shangjiaYesnoValue;
			/**
			* 审核意见
			*/
			private String shangjiaYesnoText;
			/**
			* 审核时间
			*/
			@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
			@DateTimeFormat
			private Date shangjiaShenheTime;
			/**
			* 逻辑删除
			*/
			private Integer shangjiaDelete;

	public ShuiguoView() {

	}

	public ShuiguoView(ShuiguoEntity shuiguoEntity) {
		try {
			BeanUtils.copyProperties(this, shuiguoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 一级分类的值
			*/
			public String getShuiguoValue() {
				return shuiguoValue;
			}
			/**
			* 设置： 一级分类的值
			*/
			public void setShuiguoValue(String shuiguoValue) {
				this.shuiguoValue = shuiguoValue;
			}
			/**
			* 获取： 二级分类的值
			*/
			public String getShuiguoErjiValue() {
				return shuiguoErjiValue;
			}
			/**
			* 设置： 二级分类的值
			*/
			public void setShuiguoErjiValue(String shuiguoErjiValue) {
				this.shuiguoErjiValue = shuiguoErjiValue;
			}
			/**
			* 获取： 是否推荐的值
			*/
			public String getTuijianValue() {
				return tuijianValue;
			}
			/**
			* 设置： 是否推荐的值
			*/
			public void setTuijianValue(String tuijianValue) {
				this.tuijianValue = tuijianValue;
			}
			/**
			* 获取： 是否特价的值
			*/
			public String getTejiaValue() {
				return tejiaValue;
			}
			/**
			* 设置： 是否特价的值
			*/
			public void setTejiaValue(String tejiaValue) {
				this.tejiaValue = tejiaValue;
			}
			/**
			* 获取： 是否进口的值
			*/
			public String getJinkouValue() {
				return jinkouValue;
			}
			/**
			* 设置： 是否进口的值
			*/
			public void setJinkouValue(String jinkouValue) {
				this.jinkouValue = jinkouValue;
			}
			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}
























				//级联表的get和set shangjia

					/**
					* 获取： 商家名称
					*/
					public String getShangjiaName() {
						return shangjiaName;
					}
					/**
					* 设置： 商家名称
					*/
					public void setShangjiaName(String shangjiaName) {
						this.shangjiaName = shangjiaName;
					}

					/**
					* 获取： 联系方式
					*/
					public String getShangjiaPhone() {
						return shangjiaPhone;
					}
					/**
					* 设置： 联系方式
					*/
					public void setShangjiaPhone(String shangjiaPhone) {
						this.shangjiaPhone = shangjiaPhone;
					}

					/**
					* 获取： 邮箱
					*/
					public String getShangjiaEmail() {
						return shangjiaEmail;
					}
					/**
					* 设置： 邮箱
					*/
					public void setShangjiaEmail(String shangjiaEmail) {
						this.shangjiaEmail = shangjiaEmail;
					}

					/**
					* 获取： 营业执照展示
					*/
					public String getShangjiaPhoto() {
						return shangjiaPhoto;
					}
					/**
					* 设置： 营业执照展示
					*/
					public void setShangjiaPhoto(String shangjiaPhoto) {
						this.shangjiaPhoto = shangjiaPhoto;
					}

					/**
					* 获取： 商家信用类型
					*/
					public Integer getShangjiaXingjiTypes() {
						return shangjiaXingjiTypes;
					}
					/**
					* 设置： 商家信用类型
					*/
					public void setShangjiaXingjiTypes(Integer shangjiaXingjiTypes) {
						this.shangjiaXingjiTypes = shangjiaXingjiTypes;
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
					* 获取： 现有余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 现有余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}

					/**
					* 获取： 商家简介
					*/
					public String getShangjiaContent() {
						return shangjiaContent;
					}
					/**
					* 设置： 商家简介
					*/
					public void setShangjiaContent(String shangjiaContent) {
						this.shangjiaContent = shangjiaContent;
					}

					/**
					* 获取： 审核状态
					*/
					public Integer getShangjiaYesnoTypes() {
						return shangjiaYesnoTypes;
					}
					/**
					* 设置： 审核状态
					*/
					public void setShangjiaYesnoTypes(Integer shangjiaYesnoTypes) {
						this.shangjiaYesnoTypes = shangjiaYesnoTypes;
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

					/**
					* 获取： 审核意见
					*/
					public String getShangjiaYesnoText() {
						return shangjiaYesnoText;
					}
					/**
					* 设置： 审核意见
					*/
					public void setShangjiaYesnoText(String shangjiaYesnoText) {
						this.shangjiaYesnoText = shangjiaYesnoText;
					}

					/**
					* 获取： 审核时间
					*/
					public Date getShangjiaShenheTime() {
						return shangjiaShenheTime;
					}
					/**
					* 设置： 审核时间
					*/
					public void setShangjiaShenheTime(Date shangjiaShenheTime) {
						this.shangjiaShenheTime = shangjiaShenheTime;
					}

					/**
					* 获取： 逻辑删除
					*/
					public Integer getShangjiaDelete() {
						return shangjiaDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setShangjiaDelete(Integer shangjiaDelete) {
						this.shangjiaDelete = shangjiaDelete;
					}


}
