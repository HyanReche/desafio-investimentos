package br.com.hyan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Sql(scripts = "/sql/clientes-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clientes-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class DataDeleteClienteIT {
	
	@Autowired
	WebTestClient testClient;
	
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
