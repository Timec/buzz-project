package com.timec.buzz.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timec.buzz.web.domain.DingDongDomain;
import com.timec.buzz.web.service.DingDongService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.time.LocalDateTime;

//@WebMvcTest(controllers = {DingDongController.class})
public class DingDongControllerTests {
	@Autowired
	private MockMvc mockMvc;

//	@Test
	public void dingDong() throws Exception {
		DingDongDomain domain = new DingDongDomain();
		domain.setDingDongTime(LocalDateTime.now());
		domain.setReqCode("1");
		MvcResult result = mockMvc.perform(get("/api/dingDong")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(log())
				.andReturn();

		Assertions.assertEquals("", result.getResponse().getContentAsString());
	}
}
