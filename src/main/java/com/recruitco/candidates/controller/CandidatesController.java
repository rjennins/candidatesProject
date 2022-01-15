package com.recruitco.candidates.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recruitco.candidates.model.Candidate;
import com.recruitco.candidates.service.CandidateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "candidates")
@CrossOrigin(maxAge = 3600)
@RestController
public class CandidatesController {

	@Autowired
	private CandidateService candidateService;

	/**
	 * Get all the candidates data the URL is http:localhost:8080/candidates.
	 * 
	 * @return full set of candidate data
	 */
	@ApiOperation(value = "Get details for all candidates")
	@GetMapping("/candidates")
	public ResponseEntity<Iterable<Candidate>> candidates(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		try {
			Iterable<Candidate> candidates = candidateService.getAllCandidates(pageNo, pageSize, sortBy);
			if (candidates != null) {
				return ResponseEntity.status(HttpStatus.OK).body(candidates);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	/**
	 * Updates the candidate for the given id.
	 * 
	 * @param id
	 * @return candidate data
	 */
	@ApiOperation(value = "Gets candidate details for a given id")
	@PutMapping("/candidates/{id}")
	Candidate replaceCandidate(@RequestBody Candidate newCandidate, @PathVariable Long id) {
		return candidateService.update(newCandidate, id);
	}

	/**
	 * Creates a candidate.
	 * 
	 * @param name
	 * @return success or failure message
	 */
	@ApiOperation(value = "Create a candidate")
	@PostMapping("/candidates")
	Candidate createNewCandidate(@RequestBody Candidate candidate) {
		return candidateService.create(candidate);
	}

	/**
	 * Delete the candidate for a given id.
	 * 
	 * @param id
	 * @return success or failure message
	 */
	@ApiOperation(value = "Deletes a candidate for a given id")
	@DeleteMapping("/candidates/{id}")
	ResponseEntity<?> deleteCandidate(@PathVariable Long id) {
		try {
			candidateService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Data deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
		}
	}

	/**
	 * Gets the candidate data for the given candidate name.
	 * 
	 * @param name
	 * @return candidate data
	 */
	@ApiOperation(value = "Gets candidate derails for a given name")
	@RequestMapping(value = "/candidates/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Candidate> getCandidateData(@PathVariable String name) {

		try {
			Candidate candidate = candidateService.getCandidate(name);
			if (candidate != null) {
				return ResponseEntity.status(HttpStatus.OK).body(candidate);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}