package com.recruitco.candidates.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.recruitco.candidates.model.Candidate;
import com.recruitco.candidates.repository.CandidateRepository;

public class CandidateServiceTest {

	@Mock
	private CandidateRepository candidateRepository;

	@InjectMocks
	private CandidateServiceImpl candidateService;

	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getCandidateByNameTest() {
		final Candidate candidate = new Candidate("tom", "baseball", null, null);
		when(candidateRepository.findByName(anyString())).thenReturn(candidate);
		Candidate candidate1 = candidateService.getCandidate("tom");
		assertThat(candidate1.getInterests().equals("baseball")).isTrue();		
	}
}
