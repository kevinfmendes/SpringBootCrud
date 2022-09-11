package br.com.springboot.springbootcrud.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.springbootcrud.model.Usuario;
import br.com.springboot.springbootcrud.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
//AQUI QUE VAI SER RECEPTADO OS DADOS DA WEB
//CAI PRIMEIRO AQUI NO CONTROLLER MAPEADO
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository; //injecao de dependencia //
   
    @RequestMapping(value = "/mapeado/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Nome mapeado na url que vai chegar no controller e exibir na tela:" + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String secondMethod (@PathVariable String nome) {
    
    	Usuario usuario = new Usuario();
    	
    	usuario.setNome(nome); 
    	
    	usuarioRepository.save(usuario); //gravando no banco de dados o nome do usuario
    	return "Olá " + nome + "!";
    }
    
    
    @GetMapping (value = "listatodos")
    @ResponseBody  /*retorna os dados p/ o corpo da resposta. um JSON*/
    public ResponseEntity<List<Usuario>> listaUsuario() {
    	
    List<Usuario> usuarios = usuarioRepository.findAll();/*EXECUTA A CONSULTA E LISTA PRA GENTE OS USUARIOS*/
    
    return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*retorna lista JSON*/
    	
    }
    
    
    
     
}
