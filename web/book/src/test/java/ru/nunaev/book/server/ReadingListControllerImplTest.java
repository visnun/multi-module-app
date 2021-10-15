package ru.nunaev.book.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nunaev.book.server.controller.ReadingListControllerImpl;

public class ReadingListControllerImplTest {

    @Test
    @DisplayName("Id = 1")
    void bookById_requestBookWithId1_idOfTheReturnedBookIs1() {
        ReadingListControllerImpl service = new ReadingListControllerImpl();
        Assertions.assertEquals(1, service.byId(1).getId(), "Id должен быть равен 1");
    }
}
