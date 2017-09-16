package com.taskmgr.controller.Rest;

import com.taskmgr.dao.IterationDao;
import com.taskmgr.dao.StoryDao;
import com.taskmgr.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Akai on 2017-05-31.
 */

@RestController
public class StoryRestController {

	private StoryDao storyDao;
	private IterationDao iterationDao;

	@Autowired
	public StoryRestController(StoryDao storyDao, IterationDao iterationDao) {
		this.storyDao = storyDao;
		this.iterationDao = iterationDao;
	}

	/**
	 * Get list of tasks in iteration
	 *
	 * @param iterationId id of iteration
	 * @return list of tasks in iteration
	 */
	@RequestMapping(value = "/api/storyList{iterationId}")
	public List<Story> taskList(@PathVariable int iterationId) {
		return storyDao.getByIterationId(iterationId);
	}

	/**
	 * Add Story to iteration
	 *
	 * @param story       to add
	 * @param iterationId iteration id
	 * @return story and server status response
	 */
	@PostMapping(value = "/api/story{iterationId}", consumes = {"application/json"})
	public ResponseEntity<Story> addStory(@RequestBody Story story, @PathVariable int iterationId) {
		story.setIteration(iterationDao.getById(iterationId));
		storyDao.saveOrUpdate(story);
		return new ResponseEntity<Story>(story, HttpStatus.OK);
	}

	/**
	 * Edit story without change it id
	 * @param story value to edit
	 * @return	story and server status response
	 */
	@PutMapping(value = "/api/story", consumes = {"application/json"})
	public ResponseEntity<Story> editStory(@RequestBody Story story) {
		//TODO check task to exist in database
		storyDao.edit(story);
		return new ResponseEntity<Story>(story, HttpStatus.OK);
	}

	/**
	 * Delete story
	 *
	 * @param storyId id of story to delete
	 * @return server status
	 */
	@DeleteMapping(value = "/api/story{storyId}", consumes = {"application/json"})
	public ResponseEntity deleteStory(@PathVariable int storyId) {
		storyDao.delete(storyDao.getById(storyId));
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
}
