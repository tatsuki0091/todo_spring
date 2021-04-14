package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;


/**
 * Task Controller
 */
@Controller
public class IndexController {
	
	/**
     * Task Service
     */
    @Autowired
    TaskService taskService;
	
	/**
     * Get a task list
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<Task> tasks = taskService.selectAll();
        model.addAttribute("tasks", tasks);
        return "index";
    }
    
    /**
     * Get a task detail
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
    	Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "detail";
    }
    
    /**
     * Show register display
     */
    @GetMapping("/register/index")
    public String registerIndex(Model model) {
        return "register";
    }
    
    /**
     * Register
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
    		@RequestParam("title") String title,
    		@RequestParam("description") String description,
    		Model model
    		) 
    {
    	// Register
    	taskService.register(title, description);
    	return "redirect:index";
    }
    
    /**
     * Delete
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(
    		@RequestParam("id") Long id,
    		Model model
    		) 
    {
    	// Register
    	taskService.deleteTaskById(id);
    	return "redirect:index";
    }

}
