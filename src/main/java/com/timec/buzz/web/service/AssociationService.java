package com.timec.buzz.web.service;

import java.util.List;

import com.timec.buzz.web.domain.group.Association;
import com.timec.buzz.web.domain.group.Member;
import com.timec.buzz.web.repository.AssociationRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AssociationService {
	private final AssociationRepository repository;

	@Transactional
	public void save() {
//		Association association = Association.builder().name("kim").build();
//		association.addMembers(Member.builder().name("jun1").age(10).build());
//		association.addMembers(Member.builder().name("jun2").age(11).build());
//		association.addMembers(Member.builder().name("jun3").age(12).build());
//
//		repository.save(association);

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

	@Transactional
	public void on1() {
		//given
		List<Association> result = repository.findAllByAssociation();

		for(Association ele : result) {
			ele.getMembers().stream()
					.map(Member::getName)
					.forEach(System.out::println);
		}
	}

}
