package com.ccx.test.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ccx.common.lang.net.ClientWebUtils;

@Controller
public class BaseController {

	@Autowired
	protected HttpServletRequest httpServletRequest;

	protected String getClientIP() {
		return ClientWebUtils.getClientIP(httpServletRequest);
	}

	protected String getClientIP(HttpServletRequest httpServletRequest) {
		return ClientWebUtils.getClientIP(httpServletRequest);
	}

}
