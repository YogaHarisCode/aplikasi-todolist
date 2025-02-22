package programmer.zaman.now.todolist.entity;

import java.util.Objects;

public class Todolist {

    private Integer id;

    private String todo;

    public Todolist() {
    }

    public Todolist(String todo) {
        this.todo = todo;
    }

    public Todolist(Integer id, String todo) {
        this.id = id;
        this.todo = todo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todolist todolist = (Todolist) o;
        return Objects.equals(id, todolist.id) && Objects.equals(todo, todolist.todo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(todo);
        return result;
    }

    @Override
    public String toString() {
        return "Todolist{" +
                "id=" + id +
                ", todo='" + todo + '\'' +
                '}';
    }
}
