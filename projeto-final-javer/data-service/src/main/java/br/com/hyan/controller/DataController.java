package br.com.hyan.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.hyan.dto.DataDto;
import br.com.hyan.exception.UsuarioNaoEncontradoException;
import br.com.hyan.model.Data;
import br.com.hyan.repository.DataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Banco Javer", description = "Realização de Operações ligadas ao cliente")
@RestController
@RequestMapping("/clientes")
public class DataController {
	
	@Autowired
	private DataRepository repository;

    
    
    @Operation(summary = "Criar cliente", description = "Adicionar um novo cliente à base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso!",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DataDto.class))),
                    @ApiResponse(responseCode = "422", description = "Campos inválidos.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DataDto.class)))
            })
	
	@PostMapping
	public ResponseEntity<Data> createCliente(@RequestBody @Valid Data data) {

	    Data savedData = repository.save(data);
	    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(savedData.getId())
	            .toUri();
	    
	    return ResponseEntity.created(location).body(savedData);
	}
	
	
    @Operation(summary = "Recuperar cliente pelo ID", description = "Recuperar um cliente através do ID fornecido",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente recuperado com sucesso!",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DataDto.class))),
                    @ApiResponse(responseCode = "404", description = "CLiente não encontrado.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DataDto.class)))
            })
    
	@GetMapping("/{id}")
	public Data getClienteById(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> 
		new UsuarioNaoEncontradoException(id)
		);
	}
	
	
    @Operation(summary = "Recuperar todos os clientes", description = "Recuperar todos os clientes da base de dados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Clientes recuperados com sucesso!",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DataDto.class)))
            })

	@GetMapping
    public List<Data> getAllClientes() {
        return repository.findAll();
    }

	
    @Operation(summary = "Atualizar cliente pelo ID", description = "Atualizar dados de um cliente através do ID fornecido",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente recuperado com sucesso!",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DataDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DataDto.class))),
                    @ApiResponse(responseCode = "422", description = "Campos inválidos.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DataDto.class)))
            })
    
    @PutMapping("/{id}")
    public ResponseEntity<Data> updateCliente(@PathVariable Long id, @RequestBody @Valid Data data) {
        
    	if (!repository.existsById(id)) {
    		throw new UsuarioNaoEncontradoException(id);
    	}
        
        data.setId(id);
        Data salvarData = repository.save(data);
            
        return ResponseEntity.ok(salvarData);
    }

	
    @Operation(summary = "Excluir cliente pelo ID", description = "Excluir um cliente através do ID fornecido",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso!",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DataDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DataDto.class)))
            })
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
    
    	if (!repository.existsById(id)) {
    		throw new UsuarioNaoEncontradoException(id);
    	}
    	
        repository.deleteById(id);
        
        return ResponseEntity.ok("Usuário com id " + id + " excluído com sucesso!");
    }
}
