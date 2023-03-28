package br.com.springboot.springbootcrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.springbootcrud.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value="select u from Usuario u where upper(trim(u.nome)) like %?1%") //trim adicionado p/ nao pesquisa espaços / upper adicionado p/ passar tudo p/ maiusculo e evitar erro de casesensitive
	List<Usuario> buscarPorNome(String name);
	
	@Query(value="select u from Usuario u where u.idade = ?1")
	List<Usuario> buscarPorIdade(int idade);
	
	
}
