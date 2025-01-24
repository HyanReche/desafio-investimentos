package br.com.hyan;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.hyan.dto.ResponseDto;
import br.com.hyan.model.Score;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ClienteIT {
	
    @Autowired
    WebTestClient testClient;
    
	@Test
	public void getUrls_ReturnMessageEUrlsComStatus200() {
		ResponseDto responseBody = testClient
		        .get()
		        .uri("/clientes")
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody(ResponseDto.class)
		        .returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getMessage()).isEqualTo("URLs para os serviços de clientes e cálculo de score:");
	
	    Map<String, String> expectedUrls = new HashMap<>();
	    expectedUrls.put("Calcular Score", "http://localhost:8080/score/");
	    expectedUrls.put("Criar usuário - POST", "http://localhost:8081/clientes");
	    expectedUrls.put("Atualizar Usuário - PUT", "http://localhost:8081/clientes/");
	    expectedUrls.put("Buscar Usuário por ID - GET", "http://localhost:8081/clientes/");
	    expectedUrls.put("Buscar Todos os Usuários - GET", "http://localhost:8081/clientes");
	    expectedUrls.put("Deletar Usuário - DELETE", "http://localhost:8081/clientes/");

	    org.assertj.core.api.Assertions.assertThat(responseBody.getUrls())
	            .isEqualTo(expectedUrls);
	}
	
	@Test
	public void getScore_ComIdValido_ReturnScoreEIdClienteComStatus200() {
		Score responseBody = testClient
				.get()
				.uri("/score/1")
				.exchange()
				.expectStatus().isOk()
				.expectBody(Score.class)
				.returnResult().getResponseBody();
		
		org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
		org.assertj.core.api.Assertions.assertThat(responseBody.getScore_credito()).isEqualTo(150.0);
		org.assertj.core.api.Assertions.assertThat(responseBody.getClienteId()).isEqualTo(1);	
	}
	
	@Test
	public void getScore_ComIdInvalido_ReturnErrorMessageComStatus404() {
		testClient
				.get()
				.uri("/score/30")
				.exchange()
				.expectStatus().isNotFound()
		        .expectStatus().isEqualTo(404)
		        .expectBody(String.class)
		        .value (response -> {
		org.assertj.core.api.Assertions.assertThat(response).contains("Usuário com id 30 não encontrado.");	
				});
	}
}
