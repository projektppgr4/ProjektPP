package com.taskmgr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taskmgr.model.serializer.TaskSerializer;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Created by Akai on 2017-03-28.
 */

@Entity
@Table(name = "TASK")
@JsonSerialize(using = TaskSerializer.class)
public class Task {

	@Id
	@Column(name = "TASK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@Min(0)
	private int duration;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORY_ID", nullable = true)
	private Story story;

	//TODO nie jestem pewien czy ignor czy inne rozwiazanie
	@ManyToOne
	@JsonIgnore
	private TaskStatus taskStatus;

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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}


}
