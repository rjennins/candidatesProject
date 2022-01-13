package com.recruitco.candidates.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "candidates")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String interests;
	private String linkedinProfileURL;
	@ManyToMany(cascade = {CascadeType.ALL})
	List<Skill> skills;

	public Candidate() {
	};

	public Candidate(String name, String interests, String linkedinProfileURL, List<Skill> skills) {
		this.name = name;
		this.interests = interests;
		this.linkedinProfileURL = linkedinProfileURL;
		this.skills = skills;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getLinkedinProfileURL() {
		return linkedinProfileURL;
	}

	public void setLinkedinProfileURL(String linkedinProfileURL) {
		this.linkedinProfileURL = linkedinProfileURL;
	}

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
