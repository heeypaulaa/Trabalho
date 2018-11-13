package modelo;

public class Candidato {
	
	private String nome;
	private int numero;
	private String cpf;
	public PartidoPolitico partido;

	public Candidato(String nome, int numero, String cpf, PartidoPolitico partido) {
		this.nome = nome;
		this.numero = numero;
		this.cpf = cpf;
		this.partido = new PartidoPolitico();
		this.partido = partido;
	}

	public Candidato() {
		this("não informado", 0, "000.000.000-00", null);
	}
	
	/* GET */
	public String getNome() {
		return this.nome;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public PartidoPolitico getPartido() {
		return partido;
	}
	
	/* SET */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void setPartido(PartidoPolitico partido) {
		this.partido = partido;
	}
}
