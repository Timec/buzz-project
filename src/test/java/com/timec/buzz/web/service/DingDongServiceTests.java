package com.timec.buzz.web.service;

import java.time.LocalDateTime;

import com.timec.buzz.web.domain.DingDongDomain;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DingDongServiceTests {
	private DingDongService service;

	@BeforeEach
	public void init() {
		service = new DingDongService();
	}

	@Test
	public void dongDongTest() {
		DingDongDomain dingDongDomain = new DingDongDomain();
		dingDongDomain.setDingDongTime(LocalDateTime.now());
		dingDongDomain.setReqCode("1");
		assertNull(service.dingDong(dingDongDomain));
	}
}
