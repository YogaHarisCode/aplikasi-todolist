package programmer.zaman.now.todolist.repository;

import programmer.zaman.now.todolist.entity.Todolist;

import java.util.List;

public interface TodolistRepository {

    void insert(Todolist todolist);

    boolean delete(Integer id);

    List<Todolist> getAll();
}
