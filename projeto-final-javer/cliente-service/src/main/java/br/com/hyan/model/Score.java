package br.com.hyan.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"clienteId", "score_credito"})
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double score_credito;

    @Column(nullable = false)
    private Long clienteId;

    // CONSTRUTORES
    
    public Score() {
    }

    public Score(Long id, Long clienteId, Double score_credito) {
        this.id = id;
        this.score_credito = score_credito;
        this.clienteId = clienteId;
    }

    // GETTERS E SETTERS
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getScore_credito() {
        return score_credito;
    }

    public void setScore_credito(Double score_credito) {
        this.score_credito = score_credito;
    }

    public Long getClienteId() {
        return clienteId; 
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId; 
    }
}
