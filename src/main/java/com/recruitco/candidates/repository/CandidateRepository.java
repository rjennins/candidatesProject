package com.recruitco.candidates.repository;

import org.springframework.data.repository.CrudRepository;

import com.recruitco.candidates.model.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {
	Candidate findByName(String name);
	void deleteByName(String name);
}