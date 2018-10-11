package modelo;
import java.util.Date;

public class Voto {

	private Eleitor eleitor;
	private Candidato candidato;
	private int sessao;
	private Date horaVoto;
	
	public Voto(Eleitor eleitor, Candidato candidato, int sessao, Date horaVoto) {
		this.eleitor = eleitor;
		this.candidato = candidato;
		this.sessao = sessao;
		this.horaVoto = horaVoto;
	}
	
	public Voto() {
		this(null, null, 0, null);
	}
	
	/* GET */
	public Eleitor getEleitor() {
		return this.eleitor;
	}
	
	public Candidato getCandidato() {
		return this.candidato;
	}
	
	public int getSessao() {
		return this.sessao;
	}
	
	public Date getHoraVoto() {
		return this.horaVoto;
	}
	
	/* SET */
	public void setEleitor(Eleitor eleitor) {
		this.eleitor = eleitor;
	}
	
	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	
	public void setSessao(int sessao) {
		this.sessao = sessao;
	}
	
	public void setHoraVoto(Date horaVoto) {
		this.horaVoto = horaVoto;
	}
}
