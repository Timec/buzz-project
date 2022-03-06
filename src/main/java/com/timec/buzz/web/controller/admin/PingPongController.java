package com.timec.buzz.web.controller.admin;

import com.timec.buzz.web.domain.PingPongDomain;
import com.timec.buzz.web.service.PingPongService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PingPongController {
	private final PingPongService pingPongService;

	@PostMapping("/ping")
	public String ping(PingPongDomain domain) {
		return pingPongService.ping(domain);
	}

	@PostMapping("/pong")
	public String pong(PingPongDomain domain) {
		return pingPongService.pong(domain);
	}
}
