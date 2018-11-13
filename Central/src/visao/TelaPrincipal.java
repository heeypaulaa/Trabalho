package visao;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controle.CentralDeDados;

public class TelaPrincipal  extends JFrame{
	private JPanel left;
	
	private GridBagLayout layoutLeft;
	
	private JButton btnBuscarDados;
	private JButton btnEnviarDados;
	private JButton btnMostrarVotos;
	private JButton btnCadEleitor;
	private JButton btnCadCandidato;
	private JButton btnCadPartido;
	private CentralDeDados c;
	
	//private Urna u;
	
	public TelaPrincipal(CentralDeDados c) {
		super("Central");
		
		this.c = new CentralDeDados();
		this.c = c;
		
//		this.u = new Urna();
//		this.u = u;
		
		/********** LEFT ***********/
		layoutLeft = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		
		left = new JPanel();
		left.setLayout(layoutLeft);		
		
		btnBuscarDados = new JButton("Buscar Dados");
		gbc1.gridx = 0; gbc1.gridy=0;
		left.add(btnBuscarDados, gbc1);
		
		btnEnviarDados = new JButton("Enviar Dados");
		gbc1.gridx=1;
		left.add(btnEnviarDados, gbc1);
		
		btnMostrarVotos = new JButton("Mostrar Votos");
		gbc1.gridx=2;
		left.add(btnMostrarVotos, gbc1);
		
		btnCadCandidato = new JButton("Cadastrar Candidato");
		gbc1.gridx = 0; gbc1.gridy=1;
		left.add(btnCadCandidato, gbc1);
		
		btnCadEleitor = new JButton("Cadastrar Eleitor");
		gbc1.gridx=1;
		left.add(btnCadEleitor, gbc1);
		
		btnCadPartido = new JButton("Cadastrar Partido");
		gbc1.gridx=2;
		left.add(btnCadPartido, gbc1);
		
		Evento e = new Evento();
		btnBuscarDados.addActionListener(e);
		btnEnviarDados.addActionListener(e);
		btnMostrarVotos.addActionListener(e);
		btnCadCandidato.addActionListener(e);
		btnCadEleitor.addActionListener(e);
		btnCadPartido.addActionListener(e);
		
		this.add(left, BorderLayout.CENTER);
		
		setSize(500,200);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	private class Evento implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnBuscarDados) {
				c.buscarVotosUrna();
			}
			if(e.getSource() == btnEnviarDados) {
				c.enviarDados();
				//JOptionPane.showMessageDialog(null, "EnviarDados");
			}
			if(e.getSource() == btnMostrarVotos) {
				int u = Integer.parseInt(JOptionPane.showInputDialog("Mostrar votos da urna/seção: "));
				c.relacaoEleitoresUrna(u);
			}
			if(e.getSource() == btnCadCandidato) {
				new CadastrarCandidato(c);
			}
			if(e.getSource() == btnCadEleitor) {
				new CadastrarEleitor(c);
			}
			if(e.getSource() == btnCadPartido) {
				new CadastrarPartido(c);
				
			}
		}
	}

}
