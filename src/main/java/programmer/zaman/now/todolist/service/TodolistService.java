package programmer.zaman.now.todolist.service;

import programmer.zaman.now.todolist.entity.Todolist;

import java.util.List;

public interface TodolistService {

    Todolist add(String todo);

    String remove(Integer id);

    List<Todolist> showTodolist();
}
