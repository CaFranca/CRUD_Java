package br.edu.ifsp.spo.todos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// Define um "record" chamado Todo, que representa uma tarefa com texto e status de conclusão
final class Todo {
    private final int id;
    private final String texto;
    private final boolean concluido;

    Todo(int id, String texto, boolean concluido) {
        this.id = id;
        this.texto = texto;
        this.concluido = concluido;
    }

    public int id() {
        return id;
    }

    public String texto() {
        return texto;
    }

    public boolean concluido() {
        return concluido;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Todo) obj;
        return this.id == that.id &&
                Objects.equals(this.texto, that.texto) &&
                this.concluido == that.concluido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, concluido);
    }

    @Override
    public String toString() {
        return "Todo[" +
                "id=" + id + ", " +
                "texto=" + texto + ", " +
                "concluido=" + concluido + ']';
    }
}

// Define um "record" chamado TodoForm, usado para capturar dados do formulário (apenas o texto da tarefa)
record TodoForm(String texto) {}

@Controller
public class TodosController {

    List<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(1, "Tarefa 1", false),
            new Todo(2, "Tarefa 2", false),
            new Todo(3, "Tarefa 3", false)
    ));

    @GetMapping("/todos")
    public String index(Model model) {
        model.addAttribute("todoList", todos);
        model.addAttribute("todoForm", new TodoForm(""));
        return "todos/index.html";
    }

    @PostMapping("/todos")
    public String create(Model model, TodoForm form) {
        int novoId = todos.stream()
                .mapToInt(Todo::id)
                .max()
                .orElse(0) + 1;

        todos.add(new Todo(novoId, form.texto(), false));
        return "redirect:/todos";
    }

    @PostMapping("/todos/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        for (int i = 0; i < todos.size(); i++) {
            Todo atual = todos.get(i);
            if (atual.id() == id) {
                todos.set(i, new Todo(atual.id(), atual.texto(), !atual.concluido()));
                break;
            }
        }
        return "redirect:/todos";
    }
}
