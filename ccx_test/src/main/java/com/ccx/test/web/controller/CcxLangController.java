package com.ccx.test.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccx.common.lang.page.PageUtils;
import com.ccx.common.lang.page.PageVO;
import com.ccx.test.web.entiry.ProductVO;
import com.ccx.test.web.service.IProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/ccx-lang")
@Controller
public class CcxLangController extends BaseController {

	@Autowired
	private IProductService productService;

	@ResponseBody
	@GetMapping(value = "/getClientIP")
	public String getClientIP() {
		return super.getClientIP();
	}

	@ResponseBody
	@RequestMapping(value = "/getProductList/{currentPage}/{pageSize}")
	public PageVO<ProductVO> getProductList(@PathVariable(name = "currentPage") Long arg1, @PathVariable(name = "pageSize") Long arg2,
			ProductVO productVO) throws Exception {
		log.info("getProductList args:currentPage={},pageSize={},productVO={}", arg1, arg2, productVO);
		PageVO<ProductVO> pageVO = PageUtils.format0(arg1, arg2);
		pageVO.setTData(productVO);
		pageVO = productService.getByPage(pageVO);
		return pageVO;
	}

}
