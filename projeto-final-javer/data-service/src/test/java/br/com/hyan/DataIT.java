package br.com.hyan;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.hyan.dto.DataDto;
import br.com.hyan.model.Data;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Sql(scripts = "/sql/clientes-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clientes-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class DataIT {
	
	@Autowired
	WebTestClient testClient;

	@Test
	public void createCliente_ComDadosValidos_ReturnUsuarioCriadoComStatus201() {
		DataDto dataDto = new DataDto("Hyan Reche", "11951446751", true, 0.0);
		Data responseBody = testClient
		        .post()
		        .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto)
		        .exchange()
		        .expectStatus().isCreated()
		        .expectBody(Data.class)
		        .returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getNome()).isEqualTo("Hyan Reche");
		org.assertj.core.api.Assertions.assertThat(responseBody.getTelefone()).isEqualTo("11951446751");
		org.assertj.core.api.Assertions.assertThat(responseBody.getCorrentista()).isEqualTo(true);
		org.assertj.core.api.Assertions.assertThat(responseBody.getSaldo_cc()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getScore_credito()).isNotNull();
	}
	
	@Test
	public void createCliente_ComDadosInvalidos_ReturnUsuarioNaoEncontradoComStatus422() {
		// Nome vazio
		DataDto dataDto = new DataDto("", "11951446751", true, 0.0);
		testClient
		        .post()
		        .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
		        .value (response -> {
		org.assertj.core.api.Assertions.assertThat(response).contains("O nome não pode ser nulo");	
				});
		
		//Telefone nulo
		DataDto dataDto2 = new DataDto("Hyan Reche", null, true, 0.0);
		testClient
                .post()
		        .uri("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto2)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
                .value (response -> {
	    org.assertj.core.api.Assertions.assertThat(response).contains("O telefone não pode ser nulo");	
		});
		
		//Telefone vazio
		DataDto dataDto3 = new DataDto("Hyan Reche", "", true, 0.0);
		testClient
		.post()
		.uri("/clientes")
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(dataDto3)
		.exchange()
		.expectStatus().isEqualTo(422)
		.expectBody(String.class)
		.value (response -> {
			org.assertj.core.api.Assertions.assertThat(response).contains("O telefone deve conter apenas números e ter 11 dígitos");	
		});
		
		//Telefone incorreto
		DataDto dataDto4 = new DataDto("Hyan Reche", "95144", true, 0.0);
		testClient
		        .post()
                .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataDto4)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
		        .value (response -> 
		    		org.assertj.core.api.Assertions.assertThat(response).contains("O telefone deve conter apenas números e ter 11 dígitos"));
		
		//Sem estado de correntista
		DataDto dataDto5 = new DataDto("Hyan Reche", "11951446751", null, 0.0);
		testClient
		        .post()
		        .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto5)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
		        .value (response -> {
		org.assertj.core.api.Assertions.assertThat(response).contains("O estado de correntista não pode ser nulo");	
		});
		
		//Saldo conta corrente nulo ou em branco
		DataDto dataDto6 = new DataDto("Hyan Reche", "11951446751", true, null);
		Data responseBody = testClient
		        .post()
		        .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto6)
		        .exchange()
		        .expectStatus().isCreated()
		        .expectBody(Data.class)
		        .returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getNome()).isEqualTo("Hyan Reche");
		org.assertj.core.api.Assertions.assertThat(responseBody.getTelefone()).isEqualTo("11951446751");
		org.assertj.core.api.Assertions.assertThat(responseBody.getCorrentista()).isEqualTo(true);
		org.assertj.core.api.Assertions.assertThat(responseBody.getSaldo_cc()).isEqualTo(0.0);
		org.assertj.core.api.Assertions.assertThat(responseBody.getScore_credito()).isEqualTo(0.0);
	}

	@Test
	public void recuperarClientePorId_ComIdExistente_ReturnUsuarioRecuperadoComStatus200() {
		Data responseBody = testClient
		        .get()
		        .uri("/clientes/1")
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody(Data.class)
		        .returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo(1);
	}
	
	@Test
	public void recuperarClientePorId_ComIdInexistente_ReturnErrorMessageComStatus404() {
		testClient
				.get()
				.uri("/clientes/30")
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(String.class)
                .value (response -> {
	    org.assertj.core.api.Assertions.assertThat(response).contains("Usuário com id 30 não encontrado.");	
		});
	}
	
	@Test
	public void recuperarTodosClientes_ReturnUsuariosRecuperadosComStatus200() {
		List<Data> responseBody = testClient
		        .get()
		        .uri("/clientes")
		        .exchange()
		        .expectStatus().isOk()
		        .expectBodyList(Data.class)
                .returnResult().getResponseBody();
		
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
	}
	
	@Test
	public void updateCliente_ComDadosValidos_ReturnUsuarioCriadoComStatus200() {
		DataDto dataDto = new DataDto("Hyan Reche ALTERADO", "11951446751", true, 0.0);
		Data responseBody = testClient
		        .put()
		        .uri("/clientes/1")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto)
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody(Data.class)
		        .returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getNome()).isEqualTo("Hyan Reche ALTERADO");
		org.assertj.core.api.Assertions.assertThat(responseBody.getTelefone()).isEqualTo("11951446751");
		org.assertj.core.api.Assertions.assertThat(responseBody.getCorrentista()).isEqualTo(true);
		org.assertj.core.api.Assertions.assertThat(responseBody.getSaldo_cc()).isEqualTo(0.0);
		org.assertj.core.api.Assertions.assertThat(responseBody.getScore_credito()).isEqualTo(0.0);
	}
	
	@Test
	public void updateClientePorId_ComIdInexistente_ReturnErrorMessageComStatus404() {
		DataDto dataDto = new DataDto("Hyan Reche ALTERADO", "11951446751", true, 0.0);
		testClient
				.put()
				.uri("/clientes/30")
				.contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(String.class)
                .value (response -> {
	    org.assertj.core.api.Assertions.assertThat(response).contains("Usuário com id 30 não encontrado.");	
		});
	}
	
	@Test
	public void updateCliente_ComDadosInvalidos_ReturnErrorMessageComStatus422() {
		// Nome vazio
		DataDto dataDto = new DataDto("", "11951446751", true, 0.0);
		testClient
		        .put()
		        .uri("/clientes/1")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
		        .value (response -> {
		org.assertj.core.api.Assertions.assertThat(response).contains("O nome não pode ser nulo");	
				});
		
		//Telefone vazio
		DataDto dataDto2 = new DataDto("Hyan Reche", null, true, 0.0);
		testClient
                .put()
		        .uri("/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto2)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
                .value (response -> {
	    org.assertj.core.api.Assertions.assertThat(response).contains("O telefone não pode ser nulo");	
		});
		
		//Telefone incorreto
		DataDto dataDto3 = new DataDto("Hyan Reche", "", true, 0.0);
		testClient
		.put()
		.uri("/clientes/1")
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(dataDto3)
		.exchange()
		.expectStatus().isEqualTo(422)
		.expectBody(String.class)
		.value (response -> 
		org.assertj.core.api.Assertions.assertThat(response).contains("O telefone deve conter apenas números e ter 11 dígitos"));
		
		//Telefone incorreto
		DataDto dataDto4 = new DataDto("Hyan Reche", "95144", true, 0.0);
		testClient
		        .put()
                .uri("/clientes/1")
		        .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataDto4)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
		        .value (response -> 
		    		org.assertj.core.api.Assertions.assertThat(response).contains("O telefone deve conter apenas números e ter 11 dígitos"));
		
		//Sem estado de correntista
		DataDto dataDto5 = new DataDto("Hyan Reche", "11951446751", null, 0.0);
		testClient
		        .put()
		        .uri("/clientes/1")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto5)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
		        .value (response -> {
		org.assertj.core.api.Assertions.assertThat(response).contains("O estado de correntista não pode ser nulo");	
		});
		
		//Saldo conta corrente nulo ou em branco
		DataDto dataDto6 = new DataDto("Hyan Reche", "11951446751", true, null);
		Data responseBody = testClient
		        .put()
		        .uri("/clientes/1")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto6)
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody(Data.class)
		        .returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getNome()).isEqualTo("Hyan Reche");
		org.assertj.core.api.Assertions.assertThat(responseBody.getTelefone()).isEqualTo("11951446751");
		org.assertj.core.api.Assertions.assertThat(responseBody.getCorrentista()).isEqualTo(true);
		org.assertj.core.api.Assertions.assertThat(responseBody.getSaldo_cc()).isEqualTo(0.0);
		org.assertj.core.api.Assertions.assertThat(responseBody.getScore_credito()).isEqualTo(0.0);
	}
	
	@Test
	public void excluirClientePorId_ComIdExistente_ReturnUsuarioExcuidoComSucessoComStatus200() {
		testClient
		        .delete()
		        .uri("/clientes/4")
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody(String.class)
		        .value (response -> {
		org.assertj.core.api.Assertions.assertThat(response).contains("Usuário com id 4 excluído com sucesso!");	
		});
    }
	
	@Test
	public void excluirClientePorId_ComIdInexistente_ReturnErrorMessageComStatus404() {
		testClient
		.delete()
		.uri("/clientes/4")
		.exchange()
		.expectStatus().isNotFound()
		.expectBody(String.class)
		.value (response -> {
			org.assertj.core.api.Assertions.assertThat(response).contains("Usuário com id 4 não encontrado.");	
		});
	}
}
