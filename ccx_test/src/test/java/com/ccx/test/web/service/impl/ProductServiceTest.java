package com.ccx.test.web.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.ccx.common.lang.page.PageVO;
import com.ccx.test.web.entiry.ProductVO;
import com.ccx.test.web.service.IProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class ProductServiceTest {

	@Autowired
	private IProductService productService;

	@Test
	public void testGetByPage() throws Exception {
		Long value = productService.getTotal(null);
		log.info("getTotal {}", value);
	}

	@Test
	public void testGetTotal() throws Exception {
		PageVO<ProductVO> value = productService.getByPage(null);
		log.info("getByPage {}", JSON.toJSONString(value));
	}

}
