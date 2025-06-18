package br.edu.ifsp.spo.todos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

record Todo(String texto, boolean concluido){}

@Controller
public class TodosController {
    List<Todo> todos = Arrays.asList(
            new Todo("Tarefa 1", false),
            new Todo("Tarefa 2", false),
            new Todo("Tarefa 3", false)
    );
    @GetMapping("/todos")
    public String index(Model model){
        model.addAttribute("todoList", todos);
        return "todos/index.html";
    }
}
