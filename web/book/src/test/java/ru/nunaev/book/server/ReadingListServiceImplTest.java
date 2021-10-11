package ru.nunaev.book.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadingListServiceImplTest {

    @Test
    @DisplayName("Id = 1")
    void bookById_requestBookWithId1_idOfTheReturnedBookIs1() {
        ReadingListServiceImpl service = new ReadingListServiceImpl();
        Assertions.assertEquals(1, service.bookById(1).getId(), "Id должен быть равен 1");
    }
}
