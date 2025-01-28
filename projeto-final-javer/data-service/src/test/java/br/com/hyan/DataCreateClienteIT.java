package br.com.hyan;

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
class DataCreateClienteIT {
	
	@Autowired
	WebTestClient testClient;

	@Test
	public void createCliente_ComDadosValidos_ReturnUsuarioCriadoComStatus201() {
		DataDto dataDto = new DataDto("Hyan Reche", "11951446751", 500.0f, null, null);
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
		org.assertj.core.api.Assertions.assertThat(responseBody.getSaldo_cc()).isEqualTo(500.0f);
		org.assertj.core.api.Assertions.assertThat(responseBody.getScore_credito()).isNotNull();
		
		//Saldo conta corrente nulo ou em branco
		DataDto dataDto2 = new DataDto("Hyan Reche", "11951446751", null, null, null);
		Data responseBody2 = testClient
		        .post()
		        .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto2)
		        .exchange()
		        .expectStatus().isCreated()
		        .expectBody(Data.class)
		        .returnResult().getResponseBody();
				
		org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody2.getId()).isNotNull();			
		org.assertj.core.api.Assertions.assertThat(responseBody2.getNome()).isEqualTo("Hyan Reche");
		org.assertj.core.api.Assertions.assertThat(responseBody2.getTelefone()).isEqualTo("11951446751");
		org.assertj.core.api.Assertions.assertThat(responseBody2.getCorrentista()).isEqualTo(false);
		org.assertj.core.api.Assertions.assertThat(responseBody2.getSaldo_cc()).isEqualTo(0.0f);
		org.assertj.core.api.Assertions.assertThat(responseBody2.getScore_credito()).isEqualTo(0.0f);
		
		//Saldo conta corrente como 0.0
		DataDto dataDto3 = new DataDto("Hyan Reche", "11951446751", 0.0f, null, null);
		Data responseBody3 = testClient
		        .post()
		        .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto3)
		        .exchange()
		        .expectStatus().isCreated()
		        .expectBody(Data.class)
		        .returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody3).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody3.getId()).isNotNull();			
		org.assertj.core.api.Assertions.assertThat(responseBody3.getNome()).isEqualTo("Hyan Reche");
		org.assertj.core.api.Assertions.assertThat(responseBody3.getTelefone()).isEqualTo("11951446751");
		org.assertj.core.api.Assertions.assertThat(responseBody3.getCorrentista()).isEqualTo(false);
		org.assertj.core.api.Assertions.assertThat(responseBody3.getSaldo_cc()).isEqualTo(0.0f);
		org.assertj.core.api.Assertions.assertThat(responseBody3.getScore_credito()).isEqualTo(0.0f);
		
		//Estado de correntista nulo
		DataDto dataDto4 = new DataDto("Hyan Reche", "11951446751", null, null, null);
		Data responseBody4 = testClient
		        .post()
		        .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto4)
		        .exchange()
		        .expectStatus().isCreated()
		        .expectBody(Data.class)
		        .returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody4).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody4.getId()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody4.getNome()).isEqualTo("Hyan Reche");
		org.assertj.core.api.Assertions.assertThat(responseBody4.getTelefone()).isEqualTo("11951446751");
		org.assertj.core.api.Assertions.assertThat(responseBody4.getCorrentista()).isEqualTo(false);
		org.assertj.core.api.Assertions.assertThat(responseBody4.getSaldo_cc()).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody4.getScore_credito()).isNotNull();
	}
	
	@Test
	public void createCliente_ComDadosInvalidos_ReturnUsuarioNaoEncontradoComStatus422() {
		// Nome vazio
		DataDto dataDto = new DataDto("", "11951446751", 1000.0f, null, null);
		testClient
		        .post()
		        .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
		        .value (response -> {
		org.assertj.core.api.Assertions.assertThat(response).contains("O nome não pode ser nulo ou vazio");	
				});
		
		// Nome com números
		DataDto dataDto9 = new DataDto("Hyan Reche2", "11951446751", 1000.0f, null, null);
		testClient
		.post()
		.uri("/clientes")
		.contentType(MediaType.APPLICATION_JSON)
		.bodyValue(dataDto9)
		.exchange()
		.expectStatus().isEqualTo(422)
		.expectBody(String.class)
		.value (response -> {
			org.assertj.core.api.Assertions.assertThat(response).contains("O nome deve conter apenas letras e espaços");	
		});
		
		//Telefone nulo
		DataDto dataDto2 = new DataDto("Hyan Reche", null, 1000.0f, null, null);
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
		DataDto dataDto3 = new DataDto("Hyan Reche", "", 1000.0f, null, null);
		testClient
                .post()
                .uri("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataDto3)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(String.class)
                .value (response -> {
		org.assertj.core.api.Assertions.assertThat(response).contains("O telefone ou celular é obrigatório e deve estar no padrão nacional, sendo opcional o uso do DDD.");	
		});
		
		//Telefone incorreto
		DataDto dataDto4 = new DataDto("Hyan Reche", "95144", 1000.0f, null, null);
		testClient
		        .post()
                .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataDto4)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
		        .value (response -> 
	    org.assertj.core.api.Assertions.assertThat(response).contains("O telefone ou celular é obrigatório e deve estar no padrão nacional, sendo opcional o uso do DDD."));
		
		//Telefone incorreto
		DataDto dataDto5 = new DataDto("Hyan Reche", "9514467511234", 1000.0f, null, null);
		testClient
                .post()
                .uri("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataDto5)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(String.class)
                .value (response -> 
		org.assertj.core.api.Assertions.assertThat(response).contains("O telefone ou celular é obrigatório e deve estar no padrão nacional, sendo opcional o uso do DDD."));
		
		//Saldo conta corrente negativo
		DataDto dataDto6 = new DataDto("Hyan Reche", "951446751", -1000.0f, null, null);
		testClient
                .post()
                .uri("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dataDto6)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(String.class)
                .value (response -> 
		org.assertj.core.api.Assertions.assertThat(response).contains("O saldo da conta corrente não pode ser negativo"));
		
		//Score credito existente
		DataDto dataDto7 = new DataDto("Hyan Reche", "951446751", 1000.0f, null, 100.0f);
		testClient
		        .post()
		        .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto7)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
		        .value (response -> 
		org.assertj.core.api.Assertions.assertThat(response).contains("O campo \"score_credito\" não deve ser enviado através da requisição"));
		
		//Estado de correntista existente
		DataDto dataDto8 = new DataDto("Hyan Reche", "951446751", 1000.0f, true, null);
		testClient
		        .post()
		        .uri("/clientes")
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyValue(dataDto8)
		        .exchange()
		        .expectStatus().isEqualTo(422)
		        .expectBody(String.class)
		        .value (response -> 
		org.assertj.core.api.Assertions.assertThat(response).contains("O campo \"correntista\" não deve ser enviado através da requisição"));
	}
}
