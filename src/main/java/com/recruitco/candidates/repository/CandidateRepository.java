package com.recruitco.candidates.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.recruitco.candidates.model.Candidate;

public interface CandidateRepository extends PagingAndSortingRepository<Candidate, Long> {
	Candidate findByName(String name);
	void deleteByName(String name);
}
