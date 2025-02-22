package programmer.zaman.now.todolist.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programmer.zaman.now.todolist.entity.Todolist;
import programmer.zaman.now.todolist.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TodolistRepositoryTest {

    private HikariDataSource dataSource;

    private TodolistRepository todolistRepository;

    @BeforeEach
    void setUp() {
        dataSource = ConnectionUtil.getHikariDataSource();
        todolistRepository = new TodolistRepositoryImpl(dataSource);
    }

    @Test
    void testInsert() {
        Todolist todolist = new Todolist("Yoga");
        todolistRepository.insert(todolist);

        Assertions.assertEquals(1, todolist.getId());
        Assertions.assertEquals("Yoga", todolist.getTodo());

        String select = """
                SELECT * FROM todolist
                """;

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select)) {
            Todolist todolist1 = new Todolist();
            while (resultSet.next()){
                todolist1.setId(resultSet.getInt("id"));
                todolist1.setTodo(resultSet.getString("todo"));
            }
            Assertions.assertNotNull(todolist1);
            Assertions.assertEquals(1, todolist1.getId());
            Assertions.assertEquals("Yoga", todolist1.getTodo());
            System.out.println(todolist1);
        } catch (SQLException e) {
            Assertions.fail(e);
        }

        String truncate = """
                TRUNCATE todolist
                """;

        try(Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {

            statement.executeUpdate(truncate);
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testRemove() {
        todolistRepository.insert(new Todolist("Yoga"));
        todolistRepository.insert(new Todolist("Haris"));

        Assertions.assertTrue(todolistRepository.delete(1));
        Assertions.assertTrue(todolistRepository.delete(2));
        Assertions.assertFalse(todolistRepository.delete(3));
        Assertions.assertFalse(todolistRepository.delete(4));

        String truncate = """
                TRUNCATE todolist
                """;

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(truncate);
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testGetAll() {
        todolistRepository.insert(new Todolist("Yoga"));
        todolistRepository.insert(new Todolist("Haris"));
        todolistRepository.insert(new Todolist("Fina"));
        todolistRepository.insert(new Todolist("Zulman"));

        List<Todolist> all = todolistRepository.getAll();

        Assertions.assertNotNull(all);
        Assertions.assertEquals(4, all.size());

        all.forEach(System.out::println);

        String truncate = """
                TRUNCATE todolist
                """;

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(truncate);
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
