package com.timec.buzz.web.controller;

import com.timec.buzz.web.service.AssociationService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AssociationController {
	private final AssociationService associationService;

	@GetMapping("/api/asso/save")
	public void save() {
		associationService.save();
	}

	@GetMapping("/api/asso/on1")
	public void on1() {
		associationService.on1();
	}
}
