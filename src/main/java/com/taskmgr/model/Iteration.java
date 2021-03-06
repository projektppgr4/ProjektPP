package com.taskmgr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Akai on 2017-04-09.
 */

@Entity
@Table(name = "ITERATION")
public class Iteration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITERATION_ID", unique = true, nullable = true)
	private int id;

	//private Project project;

	private String name;

	//TODO walidacja daty albo nie
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID", nullable = false)
	private Project project;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "iteration", cascade = CascadeType.REMOVE)
	private Set<Story> stories = new HashSet<Story>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "iteration", cascade = CascadeType.REMOVE)
	private Set<TaskStatus> taskStatuses = new HashSet<TaskStatus>(0);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Set<Story> getStories() {
		return stories;
	}

	public void setStories(Set<Story> stories) {
		this.stories = stories;
	}

	public Set<TaskStatus> getTaskStatuses() {
		return taskStatuses;
	}

	public void setTaskStatuses(Set<TaskStatus> taskStatuses) {
		this.taskStatuses = taskStatuses;
	}
}
