package br.com.hyan.model;

import java.io.Serializable;
import java.util.Objects;

import br.com.hyan.dto.ClienteDto;
import br.com.hyan.service.ScoreService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Cliente")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 80)
	private String nome;
	
	@Column(nullable = false, length = 11)
	private Long telefone;
	
	@Column(nullable = false, length = 80)
	private Boolean correntista;
	
	@Column(nullable = false, length = 80)
	private Float saldo_cc;
	
	@Column(nullable = true)
	private Double score_credito;
		
	
	//CONSTRUTORES
	public Cliente() {
	}

	
	public Cliente(String nome, Long telefone, Boolean correntista, Double score_credito, Float saldo_cc, ScoreService scoreService) {
		this.nome = nome;
		this.telefone = telefone;
		this.correntista = correntista;
		this.saldo_cc = saldo_cc;
		Score score = scoreService.calcularScoreCredito(new ClienteDto(nome, telefone, correntista, saldo_cc));
		if (score != null) {
			this.score_credito = score.getScore_credito();
		} else {
			this.score_credito = null;
		}
	}



	//GETTERS E SETTERS
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public Boolean getCorrentista() {
		return correntista;
	}

	public void setCorrentista(Boolean correntista) {
		this.correntista = correntista;
	}

	public Float getSaldo_cc() {
		return saldo_cc;
	}

	public void setSaldo_cc(Float saldo_cc) {
		this.saldo_cc = saldo_cc;
	}
	
	public Double getScore_credito() {
		return score_credito;
	}

	public void setScore_credito(Double score_credito) {
		this.score_credito = score_credito;
	}

	
	
	//HASHCODE AND EQUALS
	
	@Override
	public int hashCode() {
		return Objects.hash(correntista, nome, saldo_cc, score_credito, telefone);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(correntista, other.correntista) && Objects.equals(nome, other.nome)
				&& Objects.equals(saldo_cc, other.saldo_cc) && Objects.equals(score_credito, other.score_credito)
				&& Objects.equals(telefone, other.telefone);
	}
}
