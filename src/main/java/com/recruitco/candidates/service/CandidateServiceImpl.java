package com.recruitco.candidates.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recruitco.candidates.model.Candidate;
import com.recruitco.candidates.repository.CandidateRepository;

@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {

	private CandidateRepository candidateRepository;

	public CandidateServiceImpl(CandidateRepository orderRepository) {
		this.candidateRepository = orderRepository;
	}

	@Override
	public Iterable<Candidate> getAllCandidates(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Candidate> pagedResult = this.candidateRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Candidate>();
		}
	}

	@Override
	public Candidate create(Candidate candidate) {
		return this.candidateRepository.save(candidate);
	}

	@Override
	public Candidate update(Candidate updatedCandidate, Long id) {
		return candidateRepository.findById(id).map(candidate -> {
			candidate.setName(updatedCandidate.getName());
			candidate.setInterests(updatedCandidate.getInterests());
			candidate.setLinkedinProfileURL(updatedCandidate.getLinkedinProfileURL());
			candidate.setSkills(updatedCandidate.getSkills());
			return candidateRepository.save(candidate);
		}).orElseGet(() -> {
			return candidateRepository.save(updatedCandidate);
		});
	}

	@Override
	public Candidate getCandidate(String name) {
		return this.candidateRepository.findByName(name);
	}

	@Override
	public void delete(Long id) {
		this.candidateRepository.deleteById(id);
	}

	@Override
	public void deleteByName(String name) {
		this.candidateRepository.deleteByName(name);
	}
}
