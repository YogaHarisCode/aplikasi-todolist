package programmer.zaman.now.todolist.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

    @Test
    void testConnection() {

        try (Connection connection = ConnectionUtil.getHikariDataSource().getConnection()) {

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
