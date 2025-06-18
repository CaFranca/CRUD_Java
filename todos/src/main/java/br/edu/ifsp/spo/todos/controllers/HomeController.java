// Define o pacote onde a classe está localizada
package br.edu.ifsp.spo.todos.controllers;

// Importa anotações do Spring necessárias para criar um controller
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Marca esta classe como um controller Spring MVC
@Controller
public class HomeController {

    /**
     * Mapeia requisições GET para a raiz do site ("/").
     * Ou seja, quando o usuário acessa http://localhost:8080/, este método será executado.
     *
     * @return o nome do template HTML que será exibido (ex: templates/sla.html)
     */
    @GetMapping("/")
    public String index() {
        // Retorna o nome do arquivo HTML que será renderizado (sem a pasta /templates e a extensão .html se estiver usando Thymeleaf)
        return "sla.html";
    }
}
