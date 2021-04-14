package com.example.demo.service;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;


/**
 * Task Service
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class TaskService {
	
	/**
     * Task Repository
     */
    @Autowired
    TaskRepository taskRepository;

    public List<Task> selectAll() {
        // Select all
        return taskRepository.findAll();
    }

}