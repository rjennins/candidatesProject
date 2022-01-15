package com.recruitco.candidates.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitco.candidates.CandidatesApplication;
import com.recruitco.candidates.model.Candidate;
import com.recruitco.candidates.model.Skill;
import com.recruitco.candidates.repository.CandidateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CandidatesApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class CandidatesControllerTest {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mvc;

	@Autowired
	private CandidateRepository repository;

	@Test
	public void whenValidInput_thenCreateCandidate() throws IOException, Exception {
		Candidate bob = new Candidate("bob", "Fishing", "linked.co.uk/bob", null);
		mvc.perform(
				post("/candidates").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bob)));
		Candidate found = repository.findByName("bob");
		assertThat(found.getName().equals(bob.getName())).isTrue();
	}

	@Test
	public void whenCandidateExists_thenDeleteCandidate() throws IOException, Exception {
		Candidate fred = new Candidate("fred", "Fishing", "linked.co.uk/fred",
				Arrays.asList(new Skill("java"), new Skill("Angular")));
		mvc.perform(
				post("/candidates").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(fred)));
		Candidate found = repository.findByName("fred");
		assertThat(found.getName().equals(fred.getName())).isTrue();
		mvc.perform(delete("/candidates/{id}", found.getId()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		mvc.perform(get("/candidates/{name}", "fred").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void whenNotFound_thenStatusNotFound() throws Exception {
	    mvc.perform(get("/candidates/{name}", "xxxx")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isNotFound());
	}
}
