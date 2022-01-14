package com.recruitco.candidates;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.recruitco.candidates.model.Candidate;
import com.recruitco.candidates.model.Skill;
import com.recruitco.candidates.repository.CandidateRepository;
import com.recruitco.candidates.repository.SkillRepository;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// http://localhost:8080/swagger-ui.html#

@Configuration
@EnableSwagger2
@SpringBootApplication
public class CandidatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandidatesApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo());
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Spring Boot REST API").description("recruitco Candidates REST API")
				.contact(new Contact("Production Support", "www.javaguides.net", "prodsupport@gmail.com"))
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
				.build();
	}

	@Bean
	CommandLineRunner init(CandidateRepository candidateRepository, SkillRepository skillRepository) {
		return args -> {
			Stream.of(
					new Candidate("Jon Smith", "Cricket", "linked.co.uk/js",
							Arrays.asList(new Skill("java"), new Skill("React"))),
					new Candidate("Jane Jones", "Tennis", "linked.co.uk/jj",
							Arrays.asList(new Skill("java"), new Skill("Angular"))),
					new Candidate("Susan White", "Badminton", "linked.co.uk/sw",
							Arrays.asList(new Skill("java"), new Skill("react"))),
					new Candidate("Harry Brown", "Cooking", "linked.co.uk/hb",
							Arrays.asList(new Skill("java"), new Skill("react"))),
					new Candidate("Steve Black", "Cycling", "linked.co.uk/sb",
							Arrays.asList(new Skill("java"), new Skill("react"))),
					new Candidate("Jeff Blue", "Running", "linked.co.uk/jb",
							Arrays.asList(new Skill("java"), new Skill("react"))),
					new Candidate("Mary Fields", "Swimming", "linked.co.uk/mf",
							Arrays.asList(new Skill("java"), new Skill("unix"))),
					new Candidate("Peter Streeter", "Skiing", "linked.co.uk/ps",
							Arrays.asList(new Skill("java"), new Skill("react"))),
					new Candidate("Emily Lane", "Climbing", "linked.co.uk/el",
							Arrays.asList(new Skill("java"), new Skill("react"))),
					new Candidate("Fred Green", "Painting", "linked.co.uk/fg",
							Arrays.asList(new Skill("java"), new Skill("react"))))
					.forEach(candidate -> {
						candidateRepository.save(candidate);
					});
		};
	}
}