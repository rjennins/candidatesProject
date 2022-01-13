package com.recruitco.candidates.repository;

import org.springframework.data.repository.CrudRepository;

import com.recruitco.candidates.model.Candidate;
import com.recruitco.candidates.model.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {
	Candidate findByName(String name);
	void deleteByName(String name);
}