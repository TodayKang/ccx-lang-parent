package com.ccx.test.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ccx.common.lang.net.ClientWebUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class CcxLangControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private CcxLangController ccxLangController;

	private MockMvc mockMvc;

	private MockMvc mockStandaloneMvc;

	@Before
	public void initMokcMvc() {
		log.info("begin to init mockMvc !");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Before
	public void initMockStandaloneMvc() {
		log.info("begin to init mockStandaloneMvc !");
		mockStandaloneMvc = MockMvcBuilders.standaloneSetup(ccxLangController).build();
	}

	@Test
	public void testGetClientIPMockHeader() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ccx-lang/getClientIP");
		requestBuilder.header("x-forwarded-for", "unknown,4.2.2.2,10.96.112.230");
		requestBuilder.header("Proxy-Client-IP", "10.47.103.13");
		requestBuilder.header("WL-Proxy-Client-IP", "10.47.103.13");
		requestBuilder.header("HTTP_CLIENT_IP", "10.47.103.13");
		requestBuilder.header("HTTP_X_FORWARDED_FOR", "10.47.103.13");
		requestBuilder.header("X-Real-IP", "10.47.103.13");

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletRequest mockRequest = mvcResult.getRequest();

		String ip = ClientWebUtils.getClientIP(mockRequest);
		log.info("the client IP address is:{}", ip);
	}

	@Test
	public void testGetClientIPDefaultHeader() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ccx-lang/getClientIP");

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletRequest mockRequest = mvcResult.getRequest();

		String ip = ClientWebUtils.getClientIP(mockRequest);
		log.info("the client IP address is:{}", ip);
	}

	@Test
	public void testGetProductList() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ccx-lang/getProductList/2/10");
		requestBuilder.param("name", "74396100");

		MvcResult mvcResult = mockStandaloneMvc.perform(requestBuilder)//
				.andExpect(MockMvcResultMatchers.status().is(200))//
				.andDo(MockMvcResultHandlers.print())//
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		String result = mvcResult.getResponse().getContentAsString();

		log.info("testGetProductList:返回状态码:{},返回值:{}", status, result);
	}

}
