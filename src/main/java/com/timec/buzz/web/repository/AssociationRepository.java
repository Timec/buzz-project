package com.timec.buzz.web.repository;

import java.util.List;

import com.timec.buzz.web.domain.group.Association;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {
	@Query("select DISTINCT a from Association a join fetch a.members")
//	@EntityGraph(attributePaths = {"members"})
//	@Query("select DISTINCT a from Association a")
	List<Association> findAllByAssociation();
}
