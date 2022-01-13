package com.recruitco.candidates.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.recruitco.candidates.model.Candidate;

@Validated
public interface CandidateService {

    @NotNull Iterable<Candidate> getAllCandidates();

    Candidate getCandidate(String name);
    
    Candidate create(@NotNull(message = "The candidate cannot be null.") @Valid Candidate candidate);

    Candidate update(@NotNull(message = "The candidate cannot be null.") @Valid Candidate candidate, Long id);

	void delete(@NotNull(message = "The candidate cannot be null.") @Valid Long id);

	void deleteByName(@NotNull(message = "The candidate cannot be null.") @Valid String name);
}
