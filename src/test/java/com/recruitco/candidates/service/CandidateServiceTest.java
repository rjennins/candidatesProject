package com.recruitco.candidates.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
	public void getAllCandidatesTest() {
		List<Candidate> candidates = Arrays.asList(new Candidate("b", "baseball", null, null), 
				new Candidate("f", "baseball", null, null),
				new Candidate("h", "baseball", null, null),
				new Candidate("m", "baseball", null, null));
		
		Page<Candidate> page = new PageImpl<>(candidates, Pageable.unpaged(), candidates.size());
		
		when(candidateRepository.findAll(any(Pageable.class))).thenReturn(page);		
		Iterable<Candidate> retPage = candidateService.getAllCandidates(new Integer(0), new Integer(5), "name");	
		List<Candidate> resList = StreamSupport
				  .stream(retPage.spliterator(), false)
				  .collect(Collectors.toList());
		assertThat(resList.get(0).getName().equals("b")).isTrue();		
		assertThat(resList.get(1).getName().equals("f")).isTrue();		
		assertThat(resList.get(2).getName().equals("h")).isTrue();		
		assertThat(resList.get(3).getName().equals("m")).isTrue();		
	}
	
	@Test
	public void getCandidateByNameTest() {
		final Candidate candidate = new Candidate("tom", "baseball", null, null);
		when(candidateRepository.findByName(anyString())).thenReturn(candidate);
		Candidate candidate1 = candidateService.getCandidate("tom");
		assertThat(candidate1.getInterests().equals("baseball")).isTrue();		
	}
}
