package com.timec.buzz.web.service;

import com.timec.buzz.web.domain.BuzzDomain;

public interface BuzzService<T extends BuzzDomain> {
	T save(T t);
}
