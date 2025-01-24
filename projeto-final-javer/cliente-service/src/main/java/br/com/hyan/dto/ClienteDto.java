package br.com.hyan.dto;

public class ClienteDto {

	private Long id;
	private String nome;
    private Long telefone;
    private Boolean correntista;
    private Float saldo_cc;
    
    //CONSTRUTORES
    
    public ClienteDto() {
	}
    
    public ClienteDto(String nome, Long telefone, Boolean correntista, Float saldo_cc) {
    	this.nome = nome;
		this.telefone = telefone;
		this.correntista = correntista;
		this.saldo_cc = saldo_cc;
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
	
}
