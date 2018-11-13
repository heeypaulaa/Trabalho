/**
 * @author paulacunha
 *
 **/

package controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import modelo.Candidato;
import modelo.Eleitor;
import modelo.PartidoPolitico;
import modelo.Voto;

public class Urna {
	
	private int secao;
	private Eleitor[] eleitores = new Eleitor[50];
	private Candidato[] candidatos = new Candidato[50];
	private PartidoPolitico[] partidos = new PartidoPolitico[50];
	private Voto votos[] = new Voto[50];
	private String arqEleitor; 
	private String arqPartido;
	private String arqCandidato; 
	private String arqVotos;
	
	public Urna() {
	}
	
	public Urna(String arqEleitor, String arqCandidato, String arqPartido, String arqVotos, int secao) {
		this.arqCandidato = arqCandidato;
		this.arqEleitor = arqEleitor;
		this.arqPartido = arqPartido;
		this.arqVotos = arqVotos;
		setSecao(secao);
	}
		
	public int getSecao() {
		return secao;
	}

	public void setSecao(int secao) {
		this.secao = secao;
	}
	
	public Candidato getCandidato(int numero) {
		System.out.println("CANDIDATO");
		for (int i = 0; i < candidatos.length; i++) {
			
			if (candidatos[i]!= null) {
				System.out.println(candidatos[i].getNome());
				if(candidatos[i].getNumero() == numero) {
					return candidatos[i];
				}
			}
		}
		return null;
	}
	
	public Eleitor getEleitor(String foto) {
		System.out.println("fora");
		for (int i = 0; i < eleitores.length; i++) {
			System.out.println("no for");
			if(eleitores[i] != null) {
				System.out.println("não esta nulo");
				//System.out.println("get "+eleitores[i].getImagemRosto());
				if(eleitores[i].getImagemRosto().equals(foto))
					System.out.println(eleitores[i].getImagemRosto());
					return eleitores[i];
			}
		}
		System.out.println("nulo");
		return null;
	}
	
	public PartidoPolitico getPartido(String partidoPolitico) {
		for (int i = 0; i < partidos.length; i++) {
			if (partidos[i]!= null) {
				if(partidos[i].getNome().equals(partidoPolitico)) {
					return partidos[i];
				}
			}
		}
		return null;
	}
	
	public PartidoPolitico getPartido(int numero) {
		for (int i = 0; i < partidos.length; i++) {
			if (partidos[i]!= null) {
				if(partidos[i].getNumero() == numero) {
					return partidos[i];
				}
			}
		}
		return null;
	}
	
	public void setEleitores(int tituloEleitor, String nome, String cpf, String imagemRosto, int sessao) {
		for (int i = 0; i < eleitores.length; i++) {
			if (eleitores[i] == null) {
				
				eleitores[i] = new Eleitor();
				eleitores[i].setNome(nome);
				eleitores[i].setCpf(cpf);
				eleitores[i].setSessao(sessao);
				eleitores[i].setTituloEleitor(tituloEleitor);
				eleitores[i].setImagemRosto(imagemRosto);
				return;
			}
		}
	}
	
	public void setCandidatos(String nome, int numero, String cpf, String partidoPolitico) {
		PartidoPolitico partido = new PartidoPolitico();
		partido = this.getPartido(partidoPolitico);
		if (partido != null) {
			for (int i = 0; i < this.candidatos.length; i++) {
				if(this.candidatos[i] == null) {
					System.out.println("aqui C");
					this.candidatos[i] = new Candidato();
					this.candidatos[i].setNome(nome);
					this.candidatos[i].setNumero(numero);
					this.candidatos[i].setCpf(cpf);
					this.candidatos[i].setPartido(partido);
					System.out.println("aqui SET C");
					return;
				}
			}
		}
	}
	
	private void setPartidos(String nome, int numero) {
		for (int i = 0; i < partidos.length; i++) {
			if (partidos[i] == null) {
				partidos[i] = new PartidoPolitico();
				partidos[i].setNome(nome);
				partidos[i].setNumero(numero);
				return;
			}
		}
	}	

	public boolean autenticarUsuarioPorFoto(String foto1, String foto2) {
		if(ComparaArqPPM(foto1, foto2))
			return true;
		else
			return false;
	}
	
	public boolean colherVoto(Voto voto) {
		for (int i = 0; i < votos.length; i++) {
			if (votos[i] == null) {
				votos[i] = new Voto();
				votos[i] = voto;
				return true;
			}
		}
		return false;
	}
	
	public boolean transmitirParaCentral() {
		if(votosToJson())
			return true;
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
    
    /*Escreve Json convertido em arquivo chamado "<file>.json"*/
    public boolean votosToJson() {
    	Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter("/home/paulacunha/eclipse-workspace/Central/"+arqVotos);
            for (int i = 0; i < this.votos.length; i++) {
				if(this.votos[i] != null) {
					String aux = gson.toJson(this.votos[i]);
                    writer.write(aux);
                    writer.write("\n");
				}
			}
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean importarDadosIniciais() {
		/*importa de um arquivo todos os dados dos candidatos, eleitores e partidos já estavam cadastrados, no modelo json*/
		if(importarEleitores(this.arqEleitor))
			System.out.println("importou eleitor");
		if(importarPartidos(this.arqPartido))
			System.out.println("importou partido");
		if(importarCandidatos(this.arqCandidato)) {
			System.out.println("imporotu candidato");
			return true;
		}
		System.out.println(arqEleitor+" "+arqCandidato+" "+arqPartido);
	    return false;
	}

	private boolean importarCandidatos(String nomeArq) {
		Gson gson = new Gson();
	    Candidato candidato = new Candidato();
	    try {
	        FileReader reader;
	        reader = new FileReader("/home/paulacunha/eclipse-workspace/Central/"+nomeArq);
	        BufferedReader leitor = new BufferedReader(reader);
	        String linha = leitor.readLine(); // lê a primeira linha
	        while (linha != null) {
	        	System.out.println("não esta NULO");
	            candidato = gson.fromJson(linha, Candidato.class);
	            setCandidatos(candidato.getNome(), candidato.getNumero(), candidato.getCpf(), candidato.getPartido().getNome());
	            linha = leitor.readLine(); // lê da segunda até a última linha
	        }
	        leitor.close();
	    } catch (IOException e) {
	        return false;
	    }
	    return true;
	}

	private boolean importarPartidos(String nomeArq) {
		Gson gson = new Gson();
	    PartidoPolitico partido = new PartidoPolitico();
	    try {
	        FileReader reader;
	        reader = new FileReader("/home/paulacunha/eclipse-workspace/Central/"+nomeArq);
	        BufferedReader leitor = new BufferedReader(reader);
	
	        String linha = leitor.readLine(); // lê a primeira linha
	        while (linha != null) {
	            partido = gson.fromJson(linha, PartidoPolitico.class);
	            setPartidos(partido.getNome(), partido.getNumero());
	            linha = leitor.readLine(); // lê da segunda até a última linha
	        }
	        leitor.close();
	    } catch (IOException e) {
	        return false;
	    }
	    return true;
	}

	public boolean importarEleitores(String nomeArq) {
		Gson gson = new Gson();
	    Eleitor eleitor = new Eleitor();
	    try {
	        FileReader reader;
	        reader = new FileReader("/home/paulacunha/eclipse-workspace/Central/"+nomeArq);
	        BufferedReader leitor = new BufferedReader(reader);
	
	        String linha = leitor.readLine(); // lê a primeira linha
	        while (linha != null) {
	            eleitor = gson.fromJson(linha, Eleitor.class);
	            if (eleitor.getSessao() == this.getSecao()) {
	            	System.out.println("this urna "+this.secao);
		            System.out.println("eleitor secao "+eleitor.getSessao());
	            	setEleitores(eleitor.getTituloEleitor(), eleitor.getNome(), eleitor.getCpf(), eleitor.getImagemRosto(), eleitor.getSessao());
	            }
	            linha = leitor.readLine(); // lê da segunda até a última linha
	        }
	
	        leitor.close();
	    } catch (IOException e) {
	        return false;
	    }
	    return true;
	}
	
	public void print() {
		if(eleitores != null) {
			for (int i = 0; i < eleitores.length; i++) {
				System.out.println(eleitores[i].getNome());
			}
		}
	}
	
}
