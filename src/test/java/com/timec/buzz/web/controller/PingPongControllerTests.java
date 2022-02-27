package com.timec.buzz.web.controller;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

//@WebMvcTest(controllers = {PingPongController.class})
public class PingPongControllerTests {
	@Autowired
	private MockMvc mockMvc;

	public void ping() {

	}

	public void pong() {

	}
}
