package modelo;

public class PartidoPolitico {
	
	private String nome;
	private int numero;
	
	public PartidoPolitico(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
	}
	
	public PartidoPolitico() {
		this("não informado", 0);
	}
	
	/* GET */
	public String getNome() {
		return this.nome;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	/* SET */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	@Override
	public String toString() {
		return "Nome: "+getNome()+" Numero: "+getNumero();
	}

}
