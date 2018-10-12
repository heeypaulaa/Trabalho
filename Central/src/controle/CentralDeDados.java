package controle;


import modelo.Candidato;
import modelo.Eleitor;
import modelo.PartidoPolitico;
import modelo.Voto;

public class CentralDeDados {
	
	private Eleitor[] eleitores = new Eleitor[50];
	private Candidato[] candidatos = new Candidato[50];
	private PartidoPolitico[] partidos = new PartidoPolitico[50];
	private Voto votos[];
	
	public CentralDeDados() {
		
	}
	
	public boolean cadastrarEleitor(int tituloEleitor, String nome, 
			String cpf, String imagemRosto, int sessao) {
		for (int i = 0; i < eleitores.length; i++) {
			if (eleitores[i] != null) {
				if(eleitores[i].getCpf().equals(cpf)) {
					System.out.println("igual");
					return false;
				}
			}
			else {
				eleitores[i] = new Eleitor();
				eleitores[i].setNome(nome);
				eleitores[i].setCpf(cpf);
				eleitores[i].setSessao(sessao);
				eleitores[i].setTituloEleitor(tituloEleitor);
				eleitores[i].setImagemRosto(imagemRosto);
				return true;
			}
		}
		return false;
	}
//	
//	public void cadastrarCandidato(String nome, int numero, 
//			String cpf, PartidoPolitico partido) {
//		
//		for (int i = 0; i < this.candidatos.length; i++) {
//			if(this.candidatos[i] == null) {
//				this.candidatos[i] = new Candidato(nome, numero, cpf, partido);
//			}
//			else {
//				if(this.candidatos[i].getCpf().equals(cpf))
//					return;
//			}
//		}
//	}
	
	public boolean cadastrarPartidos(String nome, int numero) {
		for (int i = 0; i < partidos.length; i++) {
			if (partidos[i] != null) {
				if(partidos[i].getNome().equals(nome) || (partidos[i].getNumero() == numero)) {
					System.out.println("igual");
					return false;
				}
			}
			else {
				partidos[i] = new PartidoPolitico();
				partidos[i].setNome(nome);
				partidos[i].setNumero(numero);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String r = "";
		for (int i = 0; i < partidos.length; i++) {
			if(this.partidos[i]!=null)
				r+= partidos[i].toString();
		}
		return r;
	}
	
	
	
	public void relacaoDeCandidatos() {
		
	}
	
	public void relacaoEleitoresUrna() {
		
	}
	
	public void buscarVotosUrna() {
		
	}
	
	public void mostrarResultadosEleicao() {
		
	}

	public boolean validarCPF(String cpf) {
    	/*Valida cpf com expressão regular*/
        if (cpf != null) {
            return cpf.matches("[0-9]{3}+[.]{1}+[0-9]{3}+[.]{1}+[0-9]{3}+[-]{1}+[0-9]{2}");
        }
        return false;
    }
	
}
