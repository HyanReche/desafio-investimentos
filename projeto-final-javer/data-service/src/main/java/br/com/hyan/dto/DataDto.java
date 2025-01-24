package br.com.hyan.dto;

public class DataDto {

	private Long id;
	private String nome;
    private String telefone;
    private Boolean correntista;
    private Double saldo_cc;
   
    //CONSTRUTORES
    
    public DataDto() {
	}
    
    public DataDto(String nome, String telefone, Boolean correntista, Double saldo_cc) {
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
	public Double getSaldo_cc() {
		return saldo_cc;
	}
	public void setSaldo_cc(Double saldo_cc) {
		this.saldo_cc = saldo_cc;
	}
}
