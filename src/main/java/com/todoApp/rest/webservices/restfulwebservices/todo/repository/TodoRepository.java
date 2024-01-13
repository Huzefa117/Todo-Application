package com.todoApp.rest.webservices.restfulwebservices.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoApp.rest.webservices.restfulwebservices.todo.Todo;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>{

  List<Todo> findByUsername(String username);

}