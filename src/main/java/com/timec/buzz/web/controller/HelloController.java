package com.timec.buzz.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Operation(summary = "test hello", description = "test api, return hello")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST"),
			@ApiResponse(responseCode = "500", description = "INTERNAL ERROR")
	})
	@GetMapping("/hello")
	public String hello(@Parameter(description = "이름", example = "park") String name) {
		return "hello";
	}
}
