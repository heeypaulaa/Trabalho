package controle;


import java.io.*;
import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import modelo.Candidato;
import modelo.Eleitor;
import modelo.PartidoPolitico;
import modelo.Voto;

public class CentralDeDados {
	
	private Eleitor[] eleitores = new Eleitor[50];
	private Candidato[] candidatos = new Candidato[50];
	private PartidoPolitico[] partidos = new PartidoPolitico[50];
	private Voto votos[] = new Voto[50];
	private String arqEleitor; 
	private String arqPartido;
	private String arqCandidato;
	private String arqVotos;
	
	public CentralDeDados() {}
	
	public CentralDeDados(String arqEleitor, String arqCandidato, String arqPartido, String arqVotos) {
		this.arqCandidato = arqCandidato;
		this.arqEleitor = arqEleitor;
		this.arqPartido = arqPartido;
		this.arqVotos = arqVotos;
		this.importarDadosIniciais(arqEleitor, arqCandidato, arqPartido);
	}
	
	public boolean cadastrarEleitor(int tituloEleitor, String nome, String cpf, String imagemRosto, int sessao) {
		if(this.digitoVerifCPF(cpf)) {
			for (int i = 0; i < eleitores.length; i++) {
				if (eleitores[i] != null) {
					if(eleitores[i].getCpf().equals(cpf)||(eleitores[i].getImagemRosto().equals(imagemRosto))) {
						System.out.println("igual");
						return false;
					}
				}
				else {
					//System.out.println("aqui");
					eleitores[i] = new Eleitor();
					eleitores[i].setNome(nome);
					eleitores[i].setCpf(cpf);
					eleitores[i].setSessao(sessao);
					eleitores[i].setTituloEleitor(tituloEleitor);
					eleitores[i].setImagemRosto(imagemRosto);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean cadastrarCandidato(String nome, int numero, String cpf, String partidoPolitico) {
		if(this.digitoVerifCPF(cpf)) {
			PartidoPolitico partido = new PartidoPolitico();
			partido = this.getPartido(partidoPolitico);
			if (partido != null) {
				for (int i = 0; i < this.candidatos.length; i++) {
					if(this.candidatos[i] != null) {
						if (candidatos[i].getCpf().equals(cpf)) {
							JOptionPane.showMessageDialog(null, "EERO: Candidato cadastrado");;
							return false;
						}else if(candidatos[i].getNumero() == numero){
							JOptionPane.showMessageDialog(null, "ERRO: Número já está cadastrado no sistema");;
							return false;
						}
					}
					else {
						this.candidatos[i] = new Candidato(nome, numero, cpf, partido);
						return true;
					}
				}
			}else
				JOptionPane.showMessageDialog(null, "Partido inexistente");
		}
		return false;
	}
	
	public int getCandidato(int numero) {
		for (int i = 0; i < candidatos.length; i++) {
			if (candidatos[i]!= null) {
				if(candidatos[i].getNumero() == numero) {
					return i;
				}
			}
		}
		return -1;
	}
	
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
	
	@Override
	public String toString() {
		String r = "";
		for (int i = 0; i < partidos.length; i++) {
			if(this.partidos[i]!=null)
				r+= partidos[i].toString();
		}
		return r;
	}

	
	public Candidato[] relacaoDeCandidatos() {
		return this.candidatos;
	}
	
	public void relacaoEleitoresUrna(int urna) {
		int qtdVotosRealizados = 0;
		for (int i = 0; i < votos.length; i++) {
			if(votos[i] != null)
				if(votos[i].getSessao() == urna)
					qtdVotosRealizados++;
		}
		JOptionPane.showMessageDialog(null, qtdVotosRealizados+" Votos recebidos na Seção/Urna "+urna);
	}
	
	public void setVotos(Eleitor eleitor, int secao, Date hora, Candidato candidato) {
		Voto v = new Voto(eleitor, candidato, secao, hora);
		for (int i = 0; i < votos.length; i++) {
			if (votos[i] == null) {
				votos[i] = new Voto();
				votos[i] = v;
				return;
			}
		}
	}
	
	public boolean buscarVotosUrna() {
		Gson gson = new Gson();
	    Voto voto = new Voto();
	    try {
	        FileReader reader;
	        reader = new FileReader(arqVotos);
	        BufferedReader leitor = new BufferedReader(reader);
	
	        String linha = leitor.readLine(); // lê a primeira linha
	        while (linha != null) {
	            voto = gson.fromJson(linha, Voto.class);
	            setVotos(voto.getEleitor(), voto.getSessao(), voto.getHoraVoto(), voto.getCandidato());
	            linha = leitor.readLine(); // lê da segunda até a última linha
	        }
	        leitor.close();
	    } catch (IOException e) {
	        return false;
	    }
	    return true;
	}
	
	public void enviarDados() {
		if (eleitoresToJson(this.arqEleitor) && partidosToJson(this.arqPartido) && candidatosToJson(this.arqCandidato))
			JOptionPane.showMessageDialog(null, "Dados enviados");
		else
			JOptionPane.showMessageDialog(null, "ERRO: Dados não enviados");
		
	}
	
	public int[] mostrarResultadosEleicao() {
		int votoPorCandidato[] = new int[50];
		for (int i = 0; i < votos.length; i++) {
			int posicao = this.getCandidato(votos[i].getCandidato().getNumero());
			if(posicao != -1) {
				votoPorCandidato[posicao]++;
			}
		}
		return votoPorCandidato;
	}

	public boolean expressaoRegCPF(String cpf) {
    	/*Valida cpf com expressão regular*/
        if (cpf != null) {
            return cpf.matches("[0-9]{3}+[.]{1}+[0-9]{3}+[.]{1}+[0-9]{3}+[-]{1}+[0-9]{2}");
        }
        return false;
    }
	
	public boolean digitoVerifCPF(String cpf) {
		if (expressaoRegCPF(cpf)) {
			int soma1 = (cpf.charAt(0)*10) + (cpf.charAt(1)*9) + (cpf.charAt(2)*8)
					+(cpf.charAt(4)*7) + (cpf.charAt(5)*6) + (cpf.charAt(6)*5)
					+(cpf.charAt(8)*4) + (cpf.charAt(9)*3) + (cpf.charAt(10)*2);
			int resto1 = (soma1 * 10 ) % 11;
			if (resto1 == 10)
				resto1 = 0;
			if (resto1 == cpf.charAt(1)) {
				int soma2 = (cpf.charAt(0)*11) + (cpf.charAt(1)*10) + (cpf.charAt(2)*9)
						+ (cpf.charAt(4)*8) + (cpf.charAt(5)*7) + (cpf.charAt(6)*6)
						+ (cpf.charAt(8)*5) + (cpf.charAt(9)*4) + (cpf.charAt(10)*3)
						+ (cpf.charAt(12)*2);
				int resto2 = (soma2 * 10) % 11;
				if (resto2 == 10)
					resto2 = 0;
				if (resto2 == cpf.charAt(0)) 
					return true;
			}
			return true;
		}
		return false;
	}
	
	public boolean partidosToJson(String nomeArq) {
    	/*exporta para um arquivo todos os dados dos partidos, no modelo json*/
        Gson gson = new Gson();
        try {
            //Escreve Json convertido em arquivo chamado "file.json"
            FileWriter writer = new FileWriter(nomeArq);
            for (int i = 0; i < partidos.length; i++) {
				if(partidos[i] != null) {
					String aux = gson.toJson(partidos[i]);
                    System.out.println(aux);
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
	
	public boolean candidatosToJson(String nomeArq) {
    	/*exporta para um arquivo todos os dados dos candidatos, no modelo json*/
        Gson gson = new Gson();
        try {
            //Escreve Json convertido em arquivo chamado "file.json"
            FileWriter writer = new FileWriter(nomeArq);
            for (int i = 0; i < this.candidatos.length; i++) {
				if(this.candidatos[i] != null) {
					String aux = gson.toJson(this.candidatos[i]);
                    System.out.println(aux);
                    writer.write(aux);
                    writer.write("\n");//Não sei se pode ter esse \n
				}
			}
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
	
	public boolean eleitoresToJson(String nomeArq) {
    	/*exporta para um arquivo todos os dados dos eleitores, no modelo json*/
        Gson gson = new Gson();
        try {
            //Escreve Json convertido em arquivo chamado "file.json"
            FileWriter writer = new FileWriter(nomeArq);
            for (int i = 0; i < this.eleitores.length; i++) {
				if(this.eleitores[i] != null) {
					String aux = gson.toJson(this.eleitores[i]);
                    // System.out.println(aux);
                    writer.write(aux);
                    writer.write("\n");//Não sei se pode ter esse \n
				}
			}
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
	
	public boolean importarDadosIniciais(String arqEleitor, String arqCandidato, String arqPartido) {
		/*importa de um arquivo todos os dados dos candidatos, eleitores e partidos já estavam cadastrados, no modelo json*/
		if(!importarEleitores(arqEleitor) || !importarPartidos(arqPartido) || !importarCandidatos(arqCandidato))
			return false;
	    return true;
	}

	private boolean importarCandidatos(String nomeArq) {
		Gson gson = new Gson();
	    Candidato candidato = new Candidato();
	    try {
	        FileReader reader;
	        reader = new FileReader(nomeArq);
	        BufferedReader leitor = new BufferedReader(reader);
	
	        String linha = leitor.readLine(); // lê a primeira linha
	        while (linha != null) {
	            candidato = gson.fromJson(linha, Candidato.class);
	            cadastrarCandidato(candidato.getNome(), candidato.getNumero(), candidato.getCpf(), candidato.getPartido().getNome());
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
	        reader = new FileReader(nomeArq);
	        BufferedReader leitor = new BufferedReader(reader);
	
	        String linha = leitor.readLine(); // lê a primeira linha
	        while (linha != null) {
	            partido = gson.fromJson(linha, PartidoPolitico.class);
	            cadastrarPartidos(partido.getNome(), partido.getNumero());
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
	        reader = new FileReader(nomeArq);
	        BufferedReader leitor = new BufferedReader(reader);
	
	        String linha = leitor.readLine(); // lê a primeira linha
	        while (linha != null) {
	            eleitor = gson.fromJson(linha, Eleitor.class);
	            cadastrarEleitor(eleitor.getTituloEleitor(), eleitor.getNome(), eleitor.getCpf(), eleitor.getImagemRosto(), eleitor.getSessao());
	            linha = leitor.readLine(); // lê da segunda até a última linha
	        }
	
	        leitor.close();
	    } catch (IOException e) {
	        return false;
	    }
	    return true;
	}
	
}
