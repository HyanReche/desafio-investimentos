package br.com.hyan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class DataDto {

	
	@NotBlank(message = "O nome não pode ser nulo ou vazio")
	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]*$", message = "O nome deve conter apenas letras e espaços")
	private String nome;

    @NotNull(message = "O telefone não pode ser nulo")
    @Pattern(regexp = "^(?:\\(?[1-9]{2}\\)?)?\\s?[1-9]{1}[0-9]{3,4}\\-?[0-9]{4}$", message = "O telefone ou celular é obrigatório e deve estar no padrão nacional, sendo opcional o uso do DDD.")
    private String telefone;

    @PositiveOrZero(message = "O saldo da conta corrente não pode ser negativo")
    private Float saldo_cc;
    
    private Boolean correntista;
    
    private Float score_credito;
    
    
    //CONSTRUTORES
    
	public DataDto() {
	}

    public DataDto(@NotBlank(message = "O nome não pode ser nulo") String nome,
			@NotNull(message = "O telefone não pode ser nulo") @Pattern(regexp = "^(?:\\(?[1-9]{2}\\)?)?\\s?[1-9]{1}[0-9]{3,4}\\-?[0-9]{4}$", message = "O telefone ou celular é obrigatório e deve estar no padrão nacional, sendo opcional o uso do DDD.") String telefone,
			@PositiveOrZero(message = "O saldo da conta corrente não pode ser negativo") Float saldo_cc,
			Boolean correntista, Float score_credito) {
		this.nome = nome;
		this.telefone = telefone;
		this.saldo_cc = saldo_cc;
		this.correntista = correntista;
		this.score_credito = score_credito;
	}


	// GETTERS E SETTERS
    
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

    public Float getSaldo_cc() {
        return saldo_cc;
    }

    public void setSaldo_cc(Float saldo_cc) {
        this.saldo_cc = saldo_cc;
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
}
