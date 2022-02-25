package com.timec.buzz.web.service;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.timec.buzz.web.domain.PingPongDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PingPongServiceTests {
	private PingPongService service;

	@BeforeEach
	public void init() {
		service = new PingPongService();
	}

	@Test
	public void pingPongTest() {
		PingPongDomain domain = new PingPongDomain();
		domain.setReqCode("1");
		domain.setPingTime(LocalDateTime.now(ZoneOffset.UTC));
		assertNull(service.ping(domain));

		PingPongDomain domain2 = new PingPongDomain();
		domain.setReqCode("1");
		domain.setPongTime(LocalDateTime.now(ZoneOffset.UTC));
		assertNull(service.pong(domain2));
	}
}
