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

	@Autowired
	StoryDao storyDao;

	@Autowired
	IterationDao iterationDao;

	@RequestMapping(value = "/api/storyList{id}")
	public List<Story> taskArrayList(@PathVariable int id) {
		List<Story> storyList = storyDao.getByIterationId(id);
		return storyList;
	}


	@RequestMapping(value = "/api/story{id}", method = RequestMethod.POST, consumes = {"application/json"})
	public ResponseEntity<Story> addStory(@RequestBody Story story, @PathVariable int id) {
		story.setIteration(iterationDao.getById(id));
		storyDao.saveOrUpdate(story);
		return new ResponseEntity<Story>(story, HttpStatus.OK);
	}

}
