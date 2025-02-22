package programmer.zaman.now.todolist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import programmer.zaman.now.todolist.entity.Todolist;
import programmer.zaman.now.todolist.repository.TodolistRepository;

import java.util.List;

@Extensions({
        @ExtendWith(MockitoExtension.class)
})
public class TodolistServiceTest {

    @Mock
    private TodolistRepository todolistRepository;

    private TodolistService todolistService;

    @BeforeEach
    void setUp() {
        todolistService = new TodolistServiceImpl(todolistRepository);
    }

    @Test
    void testAdd() {
        Todolist todolist = todolistService.add("Yoga");

        Assertions.assertNotNull(todolist);
        Assertions.assertNull(todolist.getId());
        Assertions.assertEquals("Yoga", todolist.getTodo());

        Mockito.verify(todolistRepository, Mockito.times(1))
                .insert(new Todolist(todolist.getId(), todolist.getTodo()));
    }

    @Test
    void testRemoveFailed() {
        Integer idGagal = 5;
        Mockito.when(todolistRepository.delete(idGagal))
                .thenReturn(false);

        Assertions.assertEquals("Gagal menghapus todo dengan id : " + idGagal,
                todolistService.remove(idGagal));

        Mockito.verify(todolistRepository, Mockito.times(1))
                .delete(idGagal);
    }

    @Test
    void testRemoveSucces() {
        Integer idBerhasil = 1;
        Mockito.when(todolistRepository.delete(idBerhasil))
                .thenReturn(true);

        Assertions.assertEquals("Berhasil menghapus todo dengan id : " + idBerhasil,
                todolistService.remove(idBerhasil));

        Mockito.verify(todolistRepository, Mockito.times(1))
                .delete(idBerhasil);
    }

    @Test
    void testShowTodolist() {

        Mockito.when(todolistRepository.getAll())
                .thenReturn(List.of(new Todolist(1, "Yoga"),
                        new Todolist(2, "Haris")));
        List<Todolist> list = todolistService.showTodolist();

        Assertions.assertNotNull(list);
        Assertions.assertEquals(new Todolist(1, "Yoga"), list.getFirst());
        Assertions.assertEquals(new Todolist(2, "Haris"), list.get(1));
        Assertions.assertEquals(2, list.size());

        list.forEach(System.out::println);

        Mockito.verify(todolistRepository, Mockito.times(1))
                .getAll();

    }
}
