package br.com.springboot.springbootcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Spring Boot application starter class
 * 
 * Essa anotation SpringBootApplication 
 * utilizada pra ler praticamente todo projeto
 * 
 */
@SpringBootApplication //starta a aplicacao c/ spring
public class Application {
	
    public static void main(String[] args) {
        
    	SpringApplication.run(Application.class, args); /*é a linha principal que roda o projeto Java Spring Boot*/
        
        
    }
    
}
