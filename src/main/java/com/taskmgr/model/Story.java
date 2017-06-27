package com.taskmgr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Akai on 2017-04-09.
 */

@Entity
@Table(name = "STORY")
public class Story {

	@Id
	@Column(name = "STORY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITERATION_ID", nullable = true)
	private Iteration iteration;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "story", cascade = CascadeType.REMOVE)
	private Set<Task> tasks = new HashSet<Task>(0);

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

	public Iteration getIteration() {
		return iteration;
	}

	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}
