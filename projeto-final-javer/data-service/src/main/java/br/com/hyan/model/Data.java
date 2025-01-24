package br.com.hyan.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Data {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome não pode ser nulo")
	@Column(nullable = false, length = 80)
	private String nome;
	
	@NotNull(message = "O telefone não pode ser nulo")
	@Pattern(regexp = "^[0-9]{11}$", message = "O telefone deve conter apenas números e ter 11 dígitos")
	@Column(nullable = false)
	private String telefone;
	
	@NotNull(message = "O estado de correntista não pode ser nulo")
	@Column(nullable = false, length = 80)
	private Boolean correntista;
	
	@Column(nullable = true, length = 80)
	private Double score_credito = 0.0;
	
	@Column(nullable = false, length = 80)
	private Double saldo_cc = 0.0;
	
	
	//CONSTRUTORES
	
	public Data() {
	}

	
	public Data(Long id, String nome, String telefone, Boolean correntista, Double saldo_cc, Double score_credito) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.correntista = correntista;
		this.saldo_cc = (saldo_cc != null) ? saldo_cc : 0.0;
		this.score_credito = (saldo_cc != null && saldo_cc != 0.0) ? saldo_cc * 0.1 : 0.0;;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getCorrentista() {
		return correntista;
	}

	public void setCorrentista(Boolean correntista) {
		this.correntista = correntista;
	}

	public Double getScore_credito() {
		return score_credito;
	}

	public void setScore_credito(Double score_credito) {
		this.score_credito = score_credito;
	}

	public Double getSaldo_cc() {
		return saldo_cc;
	}

	public void setSaldo_cc(Double saldo_cc) {
		this.saldo_cc = (saldo_cc != null) ? saldo_cc : 0.0;
		this.score_credito = (saldo_cc != null && saldo_cc != 0.0) ? saldo_cc * 0.1 : 0.0;;
	}
	
	
	//HASHCODE AND EQUALS
	
	@Override
	public int hashCode() {
		return Objects.hash(correntista, id, nome, saldo_cc, score_credito, telefone);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		return Objects.equals(correntista, other.correntista) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(saldo_cc, other.saldo_cc)
				&& Objects.equals(score_credito, other.score_credito) && Objects.equals(telefone, other.telefone);
	}
	

}
