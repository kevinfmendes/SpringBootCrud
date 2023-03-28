const dados = {
	nome_pessoa: '#nomePessoa',
	idade_pessoa: '#idadePessoa',
	data_nascimento: '#dataNascimento',
	cep: '#cep',
};

function validaInformaceos(){
		const nome = $('#nomePessoa').val().trim();
		const idade = $('#idadePessoa').val().trim();
		const dataNasc = $('#dataNascimento').val().trim();
		const cep = $('#cep').val().trim();
		
			if (!nome){
				alert('Por favor, informe um nome para o cadastro de pesosa.');
				$(dados.nome_pessoa).focus();
				return false;
			}
			
			if (!cep) {
				alert('Por favor, informe um cep.');
				$(dados.cep).focus();
				return false;
			}
			
			if (!idade){
				$(dados.idade_pessoa).focus();
				alert('Por favor, informe uma idade para o cadastro de pesosa.');
				return false;
			}
			
			if (!dataNasc){
				$(dados.data_nascimento).focus();
				alert('Por favor, informe uma data de nascimento para o cadastro de pesosa.');
				return false;
			}
		return true;
}
	
function salvarPessoa(){
		var nome = $('#nomePessoa').val();
		var idade = $('#idadePessoa').val();
		var id = $('#idPessoa').val();
		var dataNasc = $('#dataNascimento').val();
		var cep = $('#cep').val();
		
		var resultValidacao = validaInformaceos();
		
		if (!resultValidacao) {
			return;
		} else {
			$.ajax({
				method: "POST",
				url : "salvar",
				data : JSON.stringify({
					id: id,
					nome: nome,
					idade: idade,
					dataNascimento : dataNasc
					
				}),
				contentType: "application/json; charset=utf-8",
				success: function(response) {
					$('#idPessoa').val(response.id);
					alert("Salvo com Sucesso!");
				}
			}).fail(function(xhr, status, errorThrown){
				alert("Erro ao salvar: " +xhr.responseText);
			});
			
		}
	}
	
function excluirTela () {
		
		var id = $('#idPessoa').val();
		if (id != null && id.trim() != '') {			
			excluirPessoa(id);
		} else {
			alert('Por favor, informe o cadastro que será excluido.')
		}
		
	}
	
function pesquisaPessoa(){
		var nomePesquisa = $("#buscaNome").val();
		if (nomePesquisa != null && nomePesquisa.trim() != ''){
			$.ajax({
				method: "GET",
				url : "buscarPorNome",
				data : "name=" +nomePesquisa,
				success: function(response) { 
					
					$("#tabelaResultados > tbody > tr").remove();
					
					for (var i = 0; i < response.length; i++){
						
							$("#tabelaResultados > tbody").append(
								'<tr id=' + response[i].id + '><td>'
								+ response[i].id +
								'</td><td>'
								+ response[i].nome +
								'</td><td>'
								+ response[i].idade +
								'</td><td>' + response[i].dataNascimento + '</td><td><button type="button" class="btn btn-primary" onclick="editarPessoa('
								+ response[i].id +
								')">Editar</button></td><td><button type="button" class="btn btn-danger" onclick="excluirPessoa('
								+ response[i].id +
								')">Excluir</button></td></tr>');

									}

								}
							}).fail(function(xhr, status, errorThrown) {
						alert("Erro ao pesquisar: " + xhr.responseText);
					});
		}

	}
	
	
function pesquisaPessoaIdade(){
		var idadePesquisa = $("#buscaIdade").val();
		if (idadePesquisa != null){
			$.ajax({
				method: "GET",
				url : "buscarPorIdade",
				data : "idade=" +idadePesquisa,
				success: function(response) { 
					
					$("#tabelaResultados > tbody > tr").remove();
					
					for (var i = 0; i < response.length; i++){
						
							$("#tabelaResultados > tbody").append(
								'<tr id=' + response[i].id + '><td>'
								+ response[i].id +
								'</td><td>'
								+ response[i].nome +
								'</td><td>'
								+ response[i].idade +
								'</td><td>' + response[i].dataNascimento + '</td><td><button type="button" class="btn btn-primary" onclick="editarPessoa('
								+ response[i].id +
								')">Editar</button></td><td><button type="button" class="btn btn-danger" onclick="excluirPessoa('
								+ response[i].id +
								')">Excluir</button></td></tr>');

									}

								}
							}).fail(function(xhr, status, errorThrown) {
						alert("Erro ao pesquisar: " + xhr.responseText);
					});
		}
	}


function editarPessoa(id) {

		$.ajax({
			method : "GET",
			url : "buscarpessoaid",
			data : "iduser=" + id,
			success : function(response) {

				$('#nomePessoa').val(response.nome);
				$('#idadePessoa').val(response.idade);
				$('#idPessoa').val(response.id);

				$('#modalPesquisarPessoa').modal('hide');
				

			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar usuario: " + xhr.responseText);
		});
	}
	
function excluirPessoa(id) {
	
	 if (confirm('Deseja realmente deletar?')) {
		
		$.ajax({
			method : "DELETE",
			url : "delete",
			data : "iduser=" + id,
			success : function(response) {

				$('#nomePessoa').val('');
				$('#idadePessoa').val('');
				$('#idPessoa').val('');
				
				$("#" +id).remove();

			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao DELETAR usuario: " + xhr.responseText);
		});
	 	}	
}



function calcularIdade() {
  var dataDeNascimento = document.getElementById("dataNascimento").value;
  var dataAtual = new Date();
  var anoDeNascimento = new Date(dataDeNascimento).getFullYear();
  var anoAtual = dataAtual.getFullYear();
  var idade = anoAtual - anoDeNascimento;
  
  $("#idadePessoa").val(idade);
  
}
            
 function limpa_formulário_cep() {
                // Limpa valores do formulário de cep.
                $("#rua").val("");
                $("#cidade").val("");
                $("#uf").val("");
               
            }
            
            //Quando o campo cep perde o foco.
 function buscaCep() {

                //Nova variável "cep" somente com dígitos.
                var cep = $("#cep").val().replace(/\D/g, '');

                //Verifica se campo cep possui valor informado.
                if (cep != "") {

                    //Expressão regular para validar o CEP.
                    var validacep = /^[0-9]{8}$/;

                    //Valida o formato do CEP.
                    if(validacep.test(cep)) {

                        //Preenche os campos com "..." enquanto consulta webservice.
                        $("#rua").val("buscando...");
                        $("#cidade").val("buscando...");
                        $("#uf").val("buscando...");

                        //Consulta o webservice viacep.com.br/
                        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                            if (!("erro" in dados)) {
                                //Atualiza os campos com os valores da consulta.
                                $("#rua").val(dados.logradouro);
                                $("#cidade").val(dados.localidade);
                                $("#uf").val(dados.uf);
                            } //end if.
                            else {
                                //CEP pesquisado não foi encontrado.
                                limpa_formulário_cep();
                                alert("CEP não encontrado.");
                            }
                        });
                    } //end if.
                    else {
                        //cep é inválido.
                        limpa_formulário_cep();
                        alert("Formato de CEP inválido.");
                    }
                } //end if.
                else {
                    //cep sem valor, limpa formulário.
                    limpa_formulário_cep();
                }
 }
 
 
        
	

