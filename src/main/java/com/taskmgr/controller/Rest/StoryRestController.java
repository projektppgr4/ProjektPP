package com.taskmgr.controller.Rest;

import com.taskmgr.dao.StoryDao;
import com.taskmgr.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Akai on 2017-05-31.
 */

@RestController
public class StoryRestController {

	@Autowired
	StoryDao storyDao;

	@RequestMapping(value = "/api/storyList{id}")
	public List<Story> taskArrayList(@PathVariable int id) {
		List<Story> storyList = storyDao.getByIterationId(id);
		return storyList;
	}
}
