package br.com.hyan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.hyan.dto.ClienteDto;
import br.com.hyan.model.Score;
import br.com.hyan.repository.ScoreRepository;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository repository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private final String urlSegundoApp = "http://localhost:8081/clientes/";
    
    public Score getScoreById(Long id) {
    	
    	ClienteDto cliente = null;

        try {
            cliente = restTemplate.getForObject(urlSegundoApp + "{id}", ClienteDto.class, id);
        } catch (RestClientException e) {
            System.err.println("Erro ao buscar cliente com ID " + id + ": " + e.getMessage());
            return null;
        }

        if (cliente == null) {
            return null;
        }

        Score score = calcularScoreCredito(cliente);
        repository.save(score);

        return score;
    }
    
    public Score calcularScoreCredito(ClienteDto cliente) {
    	
    	double scoreCredito = cliente.getSaldo_cc() * 0.1;
    	
    	Score score = new Score();
    	score.setScore_credito(scoreCredito);
    	score.setClienteId(cliente.getId());
    	
    	return score;
    }
}
