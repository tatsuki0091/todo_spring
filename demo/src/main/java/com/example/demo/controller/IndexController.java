package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
     * ユーザー情報一覧画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面のHTML
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayList(Model model) {
        List<Task> tasks = taskService.selectAll();
        model.addAttribute("tasks", tasks);
        return "index";
    }

}
