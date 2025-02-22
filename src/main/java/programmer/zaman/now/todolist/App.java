package programmer.zaman.now.todolist;

import com.zaxxer.hikari.HikariDataSource;
import programmer.zaman.now.todolist.repository.TodolistRepository;
import programmer.zaman.now.todolist.repository.TodolistRepositoryImpl;
import programmer.zaman.now.todolist.service.TodolistService;
import programmer.zaman.now.todolist.service.TodolistServiceImpl;
import programmer.zaman.now.todolist.util.ConnectionUtil;
import programmer.zaman.now.todolist.view.TodolistView;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        HikariDataSource dataSource = ConnectionUtil.getHikariDataSource();
        TodolistRepository todolistRepository = new TodolistRepositoryImpl(dataSource);
        TodolistService todolistService = new TodolistServiceImpl(todolistRepository);
        TodolistView todolistView = new TodolistView(todolistService);

        todolistView.mainScreen();
    }
}
