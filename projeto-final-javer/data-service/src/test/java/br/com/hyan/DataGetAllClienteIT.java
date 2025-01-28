package br.com.hyan;

import java.util.List;

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
class DataGetAllClienteIT {
	
	@Autowired
	WebTestClient testClient;
	
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

}
