package visao;

import controle.CentralDeDados;

public class Principal {

	public static void main(String[] args) {
		CentralDeDados cd = new CentralDeDados();
		CadastrarEleitor c = new CadastrarEleitor(cd);
		//CadastrarCandidato c = new CadastrarCandidato(cd);
		//CadastrarPartido c = new CadastrarPartido(cd);

	}

}
