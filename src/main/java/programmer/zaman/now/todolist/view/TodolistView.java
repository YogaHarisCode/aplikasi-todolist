package programmer.zaman.now.todolist.view;

import programmer.zaman.now.todolist.entity.Todolist;
import programmer.zaman.now.todolist.service.TodolistService;
import programmer.zaman.now.todolist.util.InputUtil;

import java.util.List;

public class TodolistView {

    private final TodolistService todolistService;

    public TodolistView(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    public void mainScreen(){
        while (true){
            System.out.println("Todo List");

            List<Todolist> list = todolistService.showTodolist();
            for (var todo : list){
                System.out.println(todo.getId() + ". " + todo.getTodo());
            }

            System.out.println("Menu :");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("x. Keluar");

            String input = InputUtil.input("Pilih");

            if (input.equals("1")){
                addTodolist();
            } else if (input.equals("2")) {
                removeTodolist();
            } else if (input.equals("x")) {
                break;
            }else {
                System.out.println("Pilihan tidak dimengerti");
            }
        }
    }

    public void addTodolist(){
        System.out.println("Tambah Todo List");

        String input = InputUtil.input("Todo ( x jika batal )");

        if (input.equals("x")){

        }else {
            Todolist todolist = todolistService.add(input);
            System.out.println("Berhasil memasukan " + todolist.getTodo());
        }

    }

    public void removeTodolist(){
        System.out.println("Hapus Todo");

        String input = InputUtil.input("No yang dihapus ( x jika batal )");

        if (input.equals("x")){

        }else {
            System.out.println(todolistService.remove(Integer.valueOf(input)));
        }
    }
}
