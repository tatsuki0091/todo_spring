package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		// header部分の表示するリンクの設定
		model.addAttribute("link", "register");
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
	public String register(@RequestParam("title") String title, @RequestParam("description") String description,
			@Validated @ModelAttribute Task task, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "register";
		}
		// Register
		taskService.register(title, description);
		return "redirect:/";
	}

	/**
	 * Delete
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") Long id, Model model) {
		// Delete
		taskService.deleteTaskById(id);
		return "redirect:/";
	}

	/**
	 * Update
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(
			@Validated @ModelAttribute Task task, BindingResult result,
			@RequestParam("id") Long id, @RequestParam("title") String title,
			@RequestParam("description") String description, RedirectAttributes redirectAttributes, Model model) {
		
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "detail";
		}
		// Update
		taskService.update(id, title, description);

		// Redirect URL with variable
		// リダイレクト先に変数を渡すための設定をここでする

		redirectAttributes.addAttribute("id", id);
		return "redirect:/detail/{id}";
	}

}
