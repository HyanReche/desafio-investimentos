package br.com.hyan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.hyan.dto.ClienteDto;
import br.com.hyan.dto.ResponseDto;
import br.com.hyan.exception.UsuarioNaoEncontradoException1;
import br.com.hyan.model.Score;
import br.com.hyan.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@Tag(name = "Serviços", description = "Direcionamento de serviços do Banco Javer")
@RestController
@RequestMapping
public class ClienteController {
	
    private final String urlScoreService = "http://localhost:8080/score/";
    private final String urlSegundoApp = "http://localhost:8081/clientes/";
    private final String urlCreateUsuario = "http://localhost:8081/clientes";
    private final String urlUpdateUsuario = "http://localhost:8081/clientes/";
    private final String urlGetUsuario = "http://localhost:8081/clientes/";
    private final String urlGetTodosUsuarios = "http://localhost:8081/clientes";
    private final String urlDeleteUsuario = "http://localhost:8081/clientes/";
    
    
	@Autowired
	private RestTemplate restTemplate;
	
    @Autowired
    private ScoreService scoreService; 

    
    @Operation(summary = "Apresentar URLs", description = "Mostrar URLs dos serviços da aplicação",
            responses = {
                    @ApiResponse(responseCode = "200", description = "URLs apresentadas com sucesso.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class)))
            }
    )
    
    @GetMapping("/clientes")
    public ResponseEntity<ResponseDto> getUrls() {
    	Map<String, String> urls = new HashMap<>();
        urls.put("Calcular Score", urlScoreService);
        urls.put("Criar usuário - POST", urlCreateUsuario);
        urls.put("Atualizar Usuário - PUT", urlUpdateUsuario);
        urls.put("Buscar Usuário por ID - GET", urlGetUsuario);
        urls.put("Buscar Todos os Usuários - GET", urlGetTodosUsuarios);
        urls.put("Deletar Usuário - DELETE", urlDeleteUsuario);
        ResponseDto response = new ResponseDto(
        		"URLs para os serviços de clientes e cálculo de score:", urls);
        
        return ResponseEntity.ok(response);
    }
    
    
    @Operation(summary = "Calcular Score de Crédito", description = "Calcular o Score de Crédito a partir do ID do Cliente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Score calculado com sucesso.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class))),
                    @ApiResponse(responseCode = "404", description = "ID de usuário não existente.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class)))
            }
    )
    
    @GetMapping("/score/{id}")
    public ResponseEntity<Score> getScore(@PathVariable Long id) {
        try {
            ResponseEntity<ClienteDto> response = restTemplate.exchange(
                urlSegundoApp + id, HttpMethod.GET, null, ClienteDto.class);

            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UsuarioNaoEncontradoException1(id);
            }

            ClienteDto cliente = response.getBody(); 
            Score score = scoreService.calcularScoreCredito(cliente);

            return ResponseEntity.ok(score);
            
        } catch (HttpClientErrorException.NotFound e) {
            throw new UsuarioNaoEncontradoException1(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}