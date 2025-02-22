package programmer.zaman.now.todolist.repository;

import com.zaxxer.hikari.HikariDataSource;
import programmer.zaman.now.todolist.entity.Todolist;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TodolistRepositoryImpl implements TodolistRepository{

    private final HikariDataSource dataSource;

    public TodolistRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Todolist todolist) {
        String sql = """
                INSERT INTO todolist (todo) VALUES (?)
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, todolist.getTodo());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if (resultSet.next()){
                    todolist.setId(resultSet.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isExist(Integer number){
        String sql = """
                SELECT * FROM todolist WHERE id = ?
                """;

        try (Connection connection= dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (isExist(id)){
            String sql = """
                    DELETE FROM todolist WHERE id = ?
                    """;
            try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            return false;
        }
    }

    @Override
    public List<Todolist> getAll() {
        String sql = """
                SELECT * FROM todolist
                """;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Todolist> list = new LinkedList<>();
            while (resultSet.next()){
                list.add(new Todolist(resultSet.getInt("id"),
                                resultSet.getString("todo")));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
