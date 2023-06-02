package com.example.spring_trello.repository;


import com.example.spring_trello.model.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {

}
