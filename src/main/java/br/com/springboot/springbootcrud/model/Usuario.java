package br.com.springboot.springbootcrud.model;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
//sequencegenerator pra gerar sequencia de ids para o usuario, allocationsize pra definir o tamanho padrao, e initialValue como o primeiro a ser passado
public class Usuario implements Serializable {

	/*classe Usuario criada p/ receber o usuario obviamente
	 * implementando o serializable  pois eh padrao java
	 * clicando no usuario podemos adicionar uma versao padrao do serializable
	 * */
	private static final long serialVersionUID = 1L;
	
	//primary key do banco
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario") //definindo a strategy como sequence conforme a anotation sequencegenerator e informando o name  
	private Long id;
	
	private String nome;
	
	private int idade;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
		
	//getter/setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
}
