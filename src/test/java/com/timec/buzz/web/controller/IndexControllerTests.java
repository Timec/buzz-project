package com.timec.buzz.web.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void 메인페이지_로딩() {
		//when
		String body = restTemplate.getForObject("/", String.class);

		//then
		assertThat(body).contains("Hello World");
	}
}
