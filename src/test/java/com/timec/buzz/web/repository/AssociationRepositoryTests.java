package com.timec.buzz.web.repository;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timec.buzz.web.domain.group.Association;
import com.timec.buzz.web.domain.group.Member;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AssociationRepositoryTests {
	@Autowired
	private AssociationRepository repository;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void before() {
		Association association1 = Association.builder().name("kim").build();
		association1.addMembers(Member.builder().name("kim1").age(10).build());
		association1.addMembers(Member.builder().name("kim2").age(11).build());
		association1.addMembers(Member.builder().name("kim3").age(12).build());

		repository.save(association1);

		Association association2 = Association.builder().name("lee").build();
		association2.addMembers(Member.builder().name("lee1").age(10).build());
		association2.addMembers(Member.builder().name("lee2").age(11).build());
		association2.addMembers(Member.builder().name("lee3").age(12).build());

		repository.save(association2);

		Association association3 = Association.builder().name("park").build();
		association3.addMembers(Member.builder().name("park1").age(10).build());
		association3.addMembers(Member.builder().name("park2").age(11).build());
		association3.addMembers(Member.builder().name("park3").age(12).build());

		repository.save(association3);

		Association association4 = Association.builder().name("choi").build();
		association4.addMembers(Member.builder().name("choi").age(10).build());
		association4.addMembers(Member.builder().name("choi").age(11).build());
		association4.addMembers(Member.builder().name("choi").age(12).build());

		repository.save(association4);
	}

	@AfterEach
	public void after() {
//		repository.deleteAll();
	}

	@Test
	@Transactional
	public void save() {
		Association association = Association.builder().name("kim").build();
		association.addMembers(Member.builder().name("jun1").age(10).build());
		association.addMembers(Member.builder().name("jun2").age(11).build());
		association.addMembers(Member.builder().name("jun3").age(12).build());

		repository.save(association);
	}

	@Test
	@Transactional
	public void selectAssociation() throws JsonProcessingException {
		//given
		List<Association> result = repository.findAll();

		//then
		assertEquals("kim", result.get(0).getName());

		//when
		Member firstMember = result.get(0).getMembers().get(0);
		System.out.println("1---------"+firstMember.getName());
		//then
		assertEquals("kim1", firstMember.getName());

		//when
		Member secMember = result.get(1).getMembers().get(0);
		System.out.println("2---------"+secMember.getName());
		//then
		assertEquals("lee1", secMember.getName());
	}
}
