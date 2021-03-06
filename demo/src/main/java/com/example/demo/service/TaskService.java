package com.example.demo.service;
import java.util.Date;
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
    
    /**
     * Select task information by id
     * @return task
     */
    public Task findById(Long id) {
      return taskRepository.findById(id).get();
    }
    
    /**
     * Register Task
     */
    public void register(String title, String description) {
      Date currentDate = new Date();
      Task task = new Task();
      task.setTitle(title);
      task.setDescription(description);
      task.setCreatedOn(currentDate);
      taskRepository.save(task);
    }
    
    /**
     * Delete Task by id
     */
    public void deleteTaskById(Long id) {
      Task task = new Task();
      task.setId(id);
      taskRepository.deleteById(id);
    }
    
    /**
     * Update a Task detail by id
     */
    public void update(Long id, String title, String description) {
      	
      Date currentDate = new Date();
      Task task = taskRepository.findById(id).get();;
      task.setTitle(title);
      task.setDescription(description);
      task.setUpdatedOn(currentDate);
      taskRepository.save(task);
    }

}
