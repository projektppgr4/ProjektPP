package com.taskmgr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Akai on 2017-05-13.
 */
@Entity
@Table(name = "TASK_STATUS")
public class TaskStatus implements Serializable {

	@Id
	@Column(name = "STATUS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITERATION_ID", nullable = true)
	@JsonIgnore
	private Iteration iteration;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Iteration getIteration() {
		return iteration;
	}

	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
