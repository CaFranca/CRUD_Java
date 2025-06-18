// Define o pacote onde esta classe está localizada
package br.edu.ifsp.spo.todos;

// Importa a classe que inicia a aplicação Spring Boot
import org.springframework.boot.SpringApplication;

// Importa a anotação que marca esta classe como a principal aplicação Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação "Todos".
 * A anotação @SpringBootApplication configura e inicia o projeto Spring Boot.
 */
@SpringBootApplication
public class TodosApplication {

	/**
	 * Método principal (main), ponto de entrada da aplicação Java.
	 * É aqui que o Spring Boot inicia toda a aplicação.
	 *
	 * @param args argumentos da linha de comando (normalmente não usados aqui)
	 */
	public static void main(String[] args) {
		// Inicia a aplicação Spring Boot usando a classe TodosApplication como ponto de partida
		SpringApplication.run(TodosApplication.class, args);
	}

}
