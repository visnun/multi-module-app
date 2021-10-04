package ru.nunaev.common.client.events;

import ru.brainworm.factory.context.client.annotation.Url;

public class BookEvents {

    @Url(value = "books", primary = true)
    public static class Show {}

    @Url(value = "book")
    public static class Edit {

        public Integer id;

        public Edit() {
        }

        public Edit(Integer id) {
            this.id = id;
        }
    }

    @Url(value = "bookcreate")
    public static class Create {
        public Create() {
        }
    }
}
