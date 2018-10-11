package controle;


import modelo.Candidato;
import modelo.Eleitor;
import modelo.PartidoPolitico;
import modelo.Voto;

public class CentralDeDados {
	
	private Eleitor[] eleitores;
	private Candidato[] candidatos;
	private PartidoPolitico[] partidos;
	private Voto votos[];
	
	public CentralDeDados() {
		partidos = new PartidoPolitico[50];
	}
	
//	public void cadastrarEleitores(int tituloEleitor, String nome, 
//			String cpf, String imagemRosto, int sessao) {
//		
//		for (int i = 0; i < this.eleitores.length; i++) {
//			if(this.eleitores[i] == null) {
//				this.eleitores[i] = new Eleitor(tituloEleitor, nome, cpf, imagemRosto, sessao);
//			}
//			else {
//				if(this.eleitores[i].getCpf().equals(cpf))
//					return;
//			}
//		}
//	}
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
		
		for (int i = 0; i < this.partidos.length; i++) {
			if (this.partidos[i] != null) {
				if(this.partidos[i].getNome().equals(nome) || (this.partidos[i].getNumero() == numero)) {
					System.out.println("igual");
					return false;
				}
			}
			else {
				this.partidos[i] = new PartidoPolitico();
				this.partidos[i].setNome(nome);
				this.partidos[i].setNumero(numero);
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
