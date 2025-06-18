// Define o pacote onde o controller está localizado
package br.edu.ifsp.spo.todos.controllers;

// Importa anotações e classes necessárias do Spring Framework
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// Importa classes utilitárias do Java
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

// Marca esta classe como um Controller do Spring MVC
@Controller
public class TodosController {

    // Lista de tarefas inicializada com três exemplos
    List<Todo> todos = new ArrayList<>(Arrays.asList(
            new Todo(1,"Tarefa 1", false),
            new Todo(2,"Tarefa 2", false),
            new Todo(3,"Tarefa 3", false)
    ));

    /**
     * Método que trata requisições GET para "/todos".
     * Esse método é chamado quando o usuário acessa a página de tarefas.
     *
     * @param model o objeto Model é usado para passar dados para a view (HTML)
     * @return o nome do template HTML que será renderizado (src/main/resources/templates/todos/index.html)
     */
    @GetMapping("/todos")
    public String index(Model model) {
        // Adiciona a lista de tarefas ao modelo, para ser exibida na view
        model.addAttribute("todoList", todos);

        // Adiciona um objeto vazio do formulário, necessário para o binding com o campo de texto
        model.addAttribute("todoForm", new TodoForm(""));

        return "todos/index.html"; // Nome do arquivo da view
    }

    /**
     * Método que trata requisições POST para "/todos".
     * Esse método é chamado quando o formulário é enviado para criar uma nova tarefa.
     *
     * @param model o objeto Model (não é necessário aqui, mas poderia ser usado para mensagens, por exemplo)
     * @param form o objeto com os dados enviados pelo formulário
     * @return redireciona o usuário de volta para a página de tarefas (GET /todos)
     */
    @PostMapping("/todos")
    public String create(Model model, TodoForm form) {
        // Adiciona uma nova tarefa à lista, com o texto vindo do formulário e concluída como 'false'
        todos.add(new Todo(form.texto(), false));

        // Redireciona para a mesma página (GET /todos) para exibir a lista atualizada
        return "redirect:/todos";
    }

    @PostMapping("/todos/{id}")
    public String update(@PathVariable("id") int id, Model model){
        throw new Exception((Integer(id).toString()));
//        return "";
    }
}
