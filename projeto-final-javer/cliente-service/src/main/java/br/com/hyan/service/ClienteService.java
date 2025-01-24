package br.com.hyan.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.hyan.dto.ClienteDto;
import br.com.hyan.model.Cliente;
import br.com.hyan.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ClienteRepository repository;
	
	private final String urlSegundoApp = "http://localhost:8081/clientes/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Cliente createCliente(ClienteDto clienteDto) {
		
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDto.getNome());
		cliente.setTelefone(clienteDto.getTelefone());
		cliente.setCorrentista(clienteDto.getCorrentista());
		cliente.setSaldo_cc(clienteDto.getSaldo_cc());
		
		return repository.save(cliente);
	}
	
	public List<Cliente> getAllClientes() {
		return repository.findAll();
	}
	
	public Cliente getClienteById(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElse(null);
	}
	
    public Cliente updateCliente(Long id, ClienteDto clienteDto) {
    	
    	Optional<Cliente> clienteExistente = repository.findById(id);
    	
		if (clienteExistente.isPresent()) {
			
			Cliente cliente = clienteExistente.get();
			cliente.setNome(clienteDto.getNome());
			cliente.setTelefone(clienteDto.getTelefone());
			cliente.setCorrentista(clienteDto.getCorrentista());
			cliente.setSaldo_cc(clienteDto.getSaldo_cc());
			
			return repository.save(cliente);
		}
		return null;
	}
    
    public void deleteCliente(Long id) {
    	repository.deleteById(id);
    }
    
    public Float calcularScoreCredito(Long id) {
    	
    	String url = urlSegundoApp + id;
    	System.out.println("URL da requisição: " + url);
    	
    	Cliente cliente = restTemplate.getForObject(url, Cliente.class);
    	
    	if (cliente != null) {
    		System.out.println("Cliente recuperado: " + cliente);
    		return cliente.getSaldo_cc() * 0.1f;
    	}
    	System.out.println("Cliente não encontrado para o id: " + id);
    	return 0f;
    }
    
    public ClienteDto convertToDto(Cliente cliente) {
    	return modelMapper.map(cliente,  ClienteDto.class);
    }
}
