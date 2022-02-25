package com.timec.buzz.web.controller;

import com.timec.buzz.web.domain.DingDongDomain;
import com.timec.buzz.web.domain.PingPongDomain;
import com.timec.buzz.web.service.DingDongService;
import com.timec.buzz.web.service.PingPongService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PingPongController {
	private PingPongService pingPongService;

	public PingPongController(PingPongService pingPongService) {
		this.pingPongService = pingPongService;
	}

	@PostMapping("/ping")
	public String ping(PingPongDomain domain) {
		return pingPongService.ping(domain);
	}

	@PostMapping("/pong")
	public String pong(PingPongDomain domain) {
		return pingPongService.pong(domain);
	}
}
