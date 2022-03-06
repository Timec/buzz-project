package com.timec.buzz.web.domain.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long member_id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int age;

	@ManyToOne
	@JoinColumn(name = "association_id")
	private Association association;

	@Builder
	public Member(String name, int age, Association association) {
		this.name = name;
		this.age = age;
		this.association = association;
	}
}
