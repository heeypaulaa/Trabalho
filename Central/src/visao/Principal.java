package visao;

import controle.CentralDeDados;

public class Principal {

	public static void main(String[] args) {
		CentralDeDados c = new CentralDeDados("eleitores.json", "candidatos.json", "partidos.json","votos.json");
		
		new TelaPrincipal(c);
	}

}
