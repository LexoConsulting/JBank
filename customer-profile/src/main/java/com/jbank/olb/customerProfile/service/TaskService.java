package com.jbank.olb.customerProfile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jbank.olb.customerProfile.model.TaskList;

@Service
public class TaskService {
	public List<TaskList> taskLists() {
		List<TaskList> lists = new ArrayList<TaskList>();
		
		// Call Google Task API: https://www.googleapis.com/tasks/v1/users/@me/lists
		
		return lists;
	}
}
