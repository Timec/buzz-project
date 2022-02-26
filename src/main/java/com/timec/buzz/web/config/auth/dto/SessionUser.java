package com.timec.buzz.web.config.auth.dto;

import java.io.Serializable;

import com.timec.buzz.web.domain.user.BuzzUser;
import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
	private String name;
	private String email;
	private String picture;

	public SessionUser(BuzzUser user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
}
