package programmer.zaman.now.todolist.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class InputUtilTest {

    @Test
    @Disabled
    void testInput() {
        String input = InputUtil.input("Todo");
        Assertions.assertNull(input);
    }
}
