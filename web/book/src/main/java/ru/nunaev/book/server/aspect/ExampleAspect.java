package ru.nunaev.book.server.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {

    @Pointcut("execution(* ru.nunaev.book.server.controller.ReadingListControllerImpl.*(..))")
    public void execute() {

    }

    @After("execute()")
    public void afterExecute() {
        System.out.println("Вызван метод контроллера");
    }
}
