package com.recruitco.candidates.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.recruitco.candidates.model.Candidate;
import com.recruitco.candidates.model.Skill;

import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CandidateRepositoryTest {

	@Autowired
	CandidateRepository candidateRepository;

	@Test
	public void givenCandidate_whenSave_thenGetOk() throws Exception {
		Candidate candidate = new Candidate("Stevex", "Cricket", "linked.co.uk/js",
				Arrays.asList(new Skill("java"), new Skill("React")));
		candidateRepository.save(candidate);
		Candidate candidate2 = candidateRepository.findByName("Stevex");

		assertAll("Candidate data", () -> assertEquals("Stevex", candidate2.getName()),
				() -> assertEquals("Cricket", candidate2.getInterests()),
				() -> assertEquals("linked.co.uk/js", candidate2.getLinkedinProfileURL()),
				() -> assertEquals("java", candidate2.getSkills().get(0).getName()));
		candidateRepository.deleteById(candidate2.getId());
	}
}
