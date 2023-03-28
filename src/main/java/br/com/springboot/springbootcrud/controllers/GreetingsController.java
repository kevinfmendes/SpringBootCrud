package br.com.springboot.springbootcrud.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    /*Primeiro método de API, retornando o JSON pra ser usado*/
    @GetMapping (value = "listatodos")
    @ResponseBody  /*retorna os dados p/ o corpo da resposta. um JSON*/
    public ResponseEntity<List<Usuario>> listaUsuario() {
    	
    List<Usuario> usuarios = usuarioRepository.findAll();/*EXECUTA A CONSULTA E LISTA PRA GENTE OS USUARIOS*/
    
    return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*retorna lista JSON*/
    	
    }
    
    @PostMapping(value = "salvar") /*mapeia a requisicao (assim como os outros neste msm controller)*/
    @ResponseBody /*Descricao da resposta, retornando nosso JSON*/
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario) { /*Recebe os dados p/ salvar*/
    	
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @PutMapping(value = "atualizar") /*mapeia a requisicao (assim como os outros neste msm controller)*/
    @ResponseBody /*Descricao da resposta, retornando nosso JSON*/
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario) { /*Recebe os dados p/ salvar*/
    	
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("Não foi possível atualizar pois não foi identificado ID da pessoa.", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @DeleteMapping(value = "delete") /*mapeia a requisicao (assim como os outros neste msm controller)*/
    @ResponseBody /*Descricao da resposta, retornando nosso JSON*/
    public ResponseEntity<String> deletar (@RequestParam Long iduser) { /*Deleta os dados*/
    	
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("Pessoa deletada com sucesso", HttpStatus.OK);
    	
    }
    

    @GetMapping(value = "buscarpessoaid") /*mapeia a requisicao (assim como os outros neste msm controller)*/
    @ResponseBody /*Descricao da resposta, retornando nosso JSON*/
    public ResponseEntity<Usuario> buscarpessoaid (@RequestParam (name = "iduser") Long iduser ) { /*busca os dados*/
    	
    	Usuario user = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarPorNome") /*mapeia a requisicao (assim como os outros neste msm controller)*/
    @ResponseBody /*Descricao da resposta, retornando nosso JSON*/
    public ResponseEntity<List<Usuario>> buscarPorNome ( @RequestParam (name = "name") String name ) { /*busca os dados*/
    	
    	List<Usuario> user = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarPorIdade") /*mapeia a requisicao (assim como os outros neste msm controller)*/
    @ResponseBody /*Descricao da resposta, retornando nosso JSON*/
    public ResponseEntity<List<Usuario>> buscarPorIdade (@RequestParam (name = "idade") int idade ) { /*busca os dados*/
    	
    	List<Usuario> user = usuarioRepository.buscarPorIdade(idade);
    	return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
    	
    }
    
     
}
