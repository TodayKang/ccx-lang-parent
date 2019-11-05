package com.ccx.test.web.entiry;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.ccx.common.lang.base.BaseVO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductVO extends BaseVO {
	private static final long serialVersionUID = 7658212839155542775L;

	private Long id;
	private Long saleId;
	private String name;
	private Long stock;
	private Long rest;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date startTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date endTime;

}
