package modelo;

public class Eleitor {
	
	private int tituloEleitor;
	private String nome;
	private String cpf;
	private String imagemRosto;
	private int sessao;
	
	public Eleitor(int tituloEleitor, String nome, String cpf, String imagemRosto, int sessao) {
		this.tituloEleitor = tituloEleitor;
		this.nome = nome;
		this.cpf = cpf;
		this.imagemRosto = imagemRosto;
		this.sessao = sessao;
	}
	
	public Eleitor() {
		this(0,"não informado","não informado","não informado",0);
	}
	
	/* GET */
	public int getTituloEleitor() {
		return this.tituloEleitor;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	/*arrumar*/
	public String getImagemRosto() {
		return this.imagemRosto;
	}
	
	public int getSessao() {
		return this.sessao;
	}

	/* SET */
	public void setTituloEleitor(int tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/*arrumar*/
	public void setImagemRosto(String imagemRosto) {
		this.imagemRosto = imagemRosto;
	}
	
	public void setSessao(int sessao) {
		this.sessao = sessao;
	}
}
