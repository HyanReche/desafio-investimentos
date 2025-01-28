package br.com.hyan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.hyan.model.Data;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Sql(scripts = "/sql/clientes-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clientes-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class DataGetClienteByIdIT {
	
	@Autowired
	WebTestClient testClient;

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

}
