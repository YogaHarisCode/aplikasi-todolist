package programmer.zaman.now.todolist.service;

import programmer.zaman.now.todolist.entity.Todolist;
import programmer.zaman.now.todolist.repository.TodolistRepository;

import java.util.Collections;
import java.util.List;

public class TodolistServiceImpl implements TodolistService{

    private final TodolistRepository todolistRepository;

    public TodolistServiceImpl(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    @Override
    public Todolist add(String todo) {
        Todolist todolist = new Todolist(todo);
        todolistRepository.insert(todolist);
        return todolist;
    }

    @Override
    public String remove(Integer id) {
        boolean success = todolistRepository.delete(id);

        if (success){
            return "Berhasil menghapus todo dengan id : " + id;
        }else {
            return "Gagal menghapus todo dengan id : " + id;
        }
    }

    @Override
    public List<Todolist> showTodolist() {
        return Collections.unmodifiableList(todolistRepository.getAll());
    }
}
