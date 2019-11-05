package com.ccx.common.lang.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO implements Serializable {
	private static final long serialVersionUID = -5819264666985098239L;

	/**
	 * 创建日期
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date createDate;

	/**
	 * 更新日期
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date updateDate;

	/**
	 * 创建者
	 */
	@JSONField(serialize = false)
	private String createBy;

	/**
	 * 更新者
	 */
	@JSONField(serialize = false)
	private String updateBy;

	/**
	 * 自定义字符标识，方便增加CRUD条件
	 */
	private String customized;

	/**
	 * 版本号
	 */
	private Long version;

}
