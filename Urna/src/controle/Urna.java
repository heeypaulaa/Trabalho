package controle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import modelo.Candidato;
import modelo.Eleitor;
import modelo.Voto;

public class Urna {
	
	private int secao;
	private Candidato candidatos[];
	private Eleitor eleitores[];
	private Voto votos[];
	
		
	public int getSecao() {
		return secao;
	}

	public void setSecao(int secao) {
		this.secao = secao;
	}

	public Candidato[] getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(Candidato[] candidatos) {
		this.candidatos = candidatos;
	}

	public Eleitor[] getEleitores() {
		return eleitores;
	}

	public void setEleitores(Eleitor[] eleitores) {
		this.eleitores = eleitores;
	}

	public Voto[] getVotos() {
		return votos;
	}

	public void setVotos(Voto[] votos) {
		this.votos = votos;
	}

	public boolean autenticarUsuarioPorFoto(String foto1, String foto2) {
		if(ComparaArqPPM(foto1, foto2))
			return true;
		else
			return false;
	}
	
	public boolean colherVoto() {
		return false;
	}
	
	public boolean transmitirParaCentral() {
		return false;
	}
    
    public boolean ComparaArqPPM(String nome1, String nome2){
		String[] vet1, vet2;

		try {
			FileReader imagem1 = new FileReader(nome1);
			BufferedReader img1 = new BufferedReader(imagem1);
			FileReader imagem2 = new FileReader(nome2);
			BufferedReader img2 = new BufferedReader(imagem2);

			String linhaImg1 = img1.readLine(); // lê a primeira linha
			String linhaImg2 = img2.readLine();

			linhaImg1 = img1.readLine(); // lê a segunda linha
			linhaImg2 = img2.readLine();

			linhaImg1 = img1.readLine(); // lê a terceira linha
			linhaImg2 = img2.readLine();
			
			linhaImg1 = img1.readLine(); // lê a quarta linha
			linhaImg2 = img2.readLine();
			while ((linhaImg1 != null) && (linhaImg2 != null)) {				
				vet1 = linhaImg1.split(" ");
				vet2 = linhaImg2.split(" ");
				if(vet1.length == vet2.length) {
					for (int i = 0; i < vet1.length; i++) {
						if( !(vet1[i].equals(vet2[i])) )
							return false;
					}
				}
				else
					return false;
				linhaImg1 = img1.readLine(); // lê da quinta até a última linha
				linhaImg2 = img2.readLine();
			}
			imagem1.close();
			imagem2.close();
			//arq2.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}
		System.out.println("Imagens iguais!");
		return true;
	}

}
