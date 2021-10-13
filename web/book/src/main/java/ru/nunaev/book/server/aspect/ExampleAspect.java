package ru.nunaev.book.server.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {

    @Pointcut("execution(* ru.nunaev.book.server.ReadingListServiceImpl.*(..))")
    public void myServices() {

    }


    @Pointcut("within(ru.nunaev.book.server.*)")
    public void serviceLayer() {

    }


    @Around("serviceLayer()")
    public void example2() {
        System.out.println("Вызван метод сервиса serviceLayer");
    }

    @Around("myServices()")
    public void example() {
        System.out.println("Вызван метод сервиса");
    }
}
