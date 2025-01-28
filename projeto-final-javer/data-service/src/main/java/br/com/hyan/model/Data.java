package br.com.hyan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Data {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome não pode ser nulo")
	@Column(nullable = false, length = 80)
	private String nome;
	
	@NotNull(message = "O telefone não pode ser nulo")
	@Pattern(regexp = "^(?:\\(?[1-9]{2}\\)?)?\\s?[1-9]{1}[0-9]{3,4}\\-?[0-9]{4}$", message = "O telefone ou celular é obrigatório e deve estar no padrão nacional, sendo opcional o uso do DDD.")
    @Column(nullable = false)
	private String telefone;
	
	@PositiveOrZero
	@Column(nullable = false, length = 80)
	private Float saldo_cc = 0.0f;
	
	@PositiveOrZero
	@Column(nullable = true, length = 80)
	private Float score_credito = 0.0f;
	
	@Column(nullable = false)
	private Boolean correntista = false;
	
	
	
	//CONSTRUTORES
	
	public Data() {
	}

	
	public Data(Long id, String nome, String telefone, Boolean correntista, Float saldo_cc, Float score_credito) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.correntista = correntista;
		this.saldo_cc = (saldo_cc != null) ? saldo_cc : 0.0f;
		this.score_credito = (saldo_cc != null && saldo_cc != 0.0f) ? saldo_cc * 0.1f : 0.0f;;
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

	public Float getScore_credito() {
		return score_credito;
	}

	public void setScore_credito(Float score_credito) {
		this.score_credito = score_credito;
	}

	public Float getSaldo_cc() {
		return saldo_cc;
	}

	public void setSaldo_cc(Float saldo_cc) {
		this.saldo_cc = (saldo_cc != null && saldo_cc > 0.0f) ? saldo_cc : 0.0f;
		this.score_credito = (saldo_cc != null && saldo_cc != 0.0f) ? saldo_cc * 0.1f : 0.0f;;
	}
}
