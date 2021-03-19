package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

public interface TodosService
{
    void markComplete(long todoid);

    Todos findTodoById(long id);

    Todos save(Todos todos);
}
