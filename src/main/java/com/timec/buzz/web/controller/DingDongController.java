package com.timec.buzz.web.controller;

import com.timec.buzz.web.domain.DingDongDomain;
import com.timec.buzz.web.service.DingDongService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DingDongController {
	private DingDongService dingDongService;

	public DingDongController(DingDongService dingDongService) {
		this.dingDongService = dingDongService;
	}

	@GetMapping("/dingDong")
	public DingDongDomain dingdong(DingDongDomain domain) {
		return dingDongService.dingDong(domain);
	}

}
