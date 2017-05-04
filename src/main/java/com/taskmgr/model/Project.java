package com.taskmgr.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Akai on 2017-04-09.
 */
@Entity
@Table(name = "PROJECT")
public class Project {


	@Id
	@Column(name = "PROJECT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = true)
	private User user;

	private String name;

	private String description;

	private Date startDate;

	private Date completionDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<Iteration> iterations = new HashSet<Iteration>(0);


	public Project() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public Set<Iteration> getIterations() {
		return iterations;
	}

	public void setIterations(Set<Iteration> iterations) {
		this.iterations = iterations;
	}
}
