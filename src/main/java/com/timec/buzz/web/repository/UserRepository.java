package com.timec.buzz.web.repository;

import java.util.Optional;

import com.timec.buzz.web.domain.user.BuzzUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<BuzzUser, Long> {
	Optional<BuzzUser> findByEmail(String email);
}
