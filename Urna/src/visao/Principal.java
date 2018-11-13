package visao;

import javax.swing.JOptionPane;

import controle.Urna;

public class Principal {

	public static void main(String[] args) {
		int secao = Integer.parseInt(JOptionPane.showInputDialog(null, "Número desta Urna (Seção)"));
		System.out.println("seçaõ "+secao);
		Urna u = new Urna("eleitores.json", "candidatos.json", "partidos.json","votos.json", secao);
		
		new TelaPrincipal(u);

	}

}
