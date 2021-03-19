package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service (value = "todosService")
public class TodosServiceImpl implements TodosService {
    @Autowired
    private TodosRepository todosrepository;

    @Autowired
    private TodosService todosService;

    @Override
    public Todos findTodoById(long id)
    {
        return todosrepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo with id " + id + " Not Found!"));
    }

    @Override
    public void markComplete(long todoid)
    {
        if (todosrepository.findById(todoid).isPresent())
        {
            Todos todosComplete = findTodoById(todoid);
            todosComplete.setCompleted(true);
        } else {
            throw new EntityNotFoundException("Todo is " + todoid + ("Not found to show completed task."));
        }
    }

    @Transactional
    @Override
    public Todos save(Todos todos)
    {
        Todos newTodo = new Todos();

        newTodo.setDescription(todos.getDescription());
        newTodo.setUser(todos.getUser());

        return todosrepository.save(newTodo);
    }
}

