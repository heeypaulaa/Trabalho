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

import controle.Urna;

public class TelaPrincipal extends JFrame{
	private JPanel left;
	
	private GridBagLayout layoutLeft;
	
	private JButton btnBuscarDados;
	private JButton btnEnviarDados;
	private JButton btnVotar;
	
	private Urna u;
	
	public TelaPrincipal(Urna u) {
		super("Urna");
		this.u = new Urna();
		this.u = u;
		System.out.println(u.getSecao());
		
		/********** LEFT ***********/
		layoutLeft = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		
		left = new JPanel();
		left.setLayout(layoutLeft);		
		
		btnBuscarDados = new JButton("Buscar Dados");
		gbc1.gridx = 0; gbc1.gridy=0;
		left.add(btnBuscarDados, gbc1);
		
		btnEnviarDados = new JButton("Enviar Dados");
		gbc1.gridy=1;
		//btnEnviarDados.setEnabled(false);
		left.add(btnEnviarDados, gbc1);
		
		btnVotar = new JButton("Votar");
		gbc1.gridy=2;
		btnVotar.setEnabled(false);
		left.add(btnVotar, gbc1);
		
		Evento e = new Evento();
		btnBuscarDados.addActionListener(e);
		btnEnviarDados.addActionListener(e);
		btnVotar.addActionListener(e);
		
		this.add(left, BorderLayout.CENTER);
		
		setSize(200,200);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	private class Evento implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnBuscarDados) {
				if(u.importarDadosIniciais()) {
					JOptionPane.showMessageDialog(null, "Dados importados");
					btnVotar.setEnabled(true);
				}
//				Eleitor el = new Eleitor();
//				el = u.getEleitor(txtCPF.getText());
//				new ColherVoto(u, el);
				//new ColherVoto(null, null);
				//e.setSource(btnCorrige);
			}
			if(e.getSource() == btnEnviarDados) {
				if(u.votosToJson())
					JOptionPane.showMessageDialog(null, "Votos JSON");
			}
			if(e.getSource() == btnVotar) {
				new Login(u);
			}
		}
	}
}
