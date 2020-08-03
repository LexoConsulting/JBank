package com.jbank.olb.customerProfile.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbank.olb.customerProfile.model.TaskList;
import com.jbank.olb.customerProfile.service.TaskService;


@RestController
@RequestMapping("/api/task")
@Validated
public class GoogleTaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping("/tasklist")
	public List<TaskList> taskLists() {
		return this.taskService.taskLists();
	}

}
