package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

//import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controle.Urna;
import modelo.Candidato;
import modelo.Eleitor;
import modelo.Voto;

public class ColherVoto extends JFrame{
	private JPanel left;
	private JPanel right;
	private JPanel bottom;
	
	private GridBagLayout layoutLeft;
	private GridBagLayout layoutRight;
	private GridBagLayout layoutBottom;
	
	private JLabel lblNome;
	//private JLabel lblCPF;
	//private JLabel lblImagemRosto;
	private JLabel lblNumero;
	private JLabel lblPartido;
	
	private JTextField txtNome;
	//private JTextField txtCPF;
	private JTextField txtNumero;
	private JTextField txtPartido;
	//private Icon icnImagemRosto;

	private JLabel teclas1, teclas2, teclas3;
	private JButton btnConfirma;
	private JButton btnCorrige;
	private JButton btnBranco;

	private Urna u;
	private Eleitor eleitor;
	Voto voto = new Voto();
	
	private boolean habilita = false; 
	
	public ColherVoto(Urna u, Eleitor e) {
		super("Colher Voto");
		
		this.u = new Urna();
		this.u = u;
		this.eleitor = new Eleitor();
		this.eleitor = e;
				
		Evento evento = new Evento();
		
		/********** LEFT ***********/
		layoutLeft = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		//gbc1.gridwidth = -4;
		left = new JPanel();
		left.setLayout(layoutLeft);
		//left.setBackground(Color.BLUE);
		
		lblNumero = new JLabel("Número:    ");
		gbc1.gridx=0; gbc1.gridy=0;
		left.add(lblNumero, gbc1);
		
		txtNumero = new JTextField(5);
		gbc1.gridx=1;
		txtNumero.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {}
			
			@Override
			public void keyReleased(KeyEvent arg0) {}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					
					System.out.println(txtNumero.getText().length());
//					if(txtNumero.getText().length() == ) {
					//habilita = !habilita;
					Candidato c = new Candidato();
					c = u.getCandidato(Integer.parseInt(txtNumero.getText()));
					//System.out.println(c.getNome());
					if (c != null) {
					//	System.out.println("não nulo CIRO");
						txtNome.setText(c.getNome());
						txtPartido.setText(c.getPartido().getNome());
						txtNumero.setEnabled(false);
						voto = new Voto(eleitor, c, u.getSecao(), new Date());
					}
						
					//}
				}
			}
		});
		left.add(txtNumero, gbc1);
		
		lblNome = new JLabel("Nome:    ");
		gbc1.gridx=0; gbc1.gridy=1;
		lblNome.setVisible(true);
		left.add(lblNome,gbc1);
		
		txtNome = new JTextField(20);
		gbc1.gridx=1;
		//txtNome.setVisible(true);
		txtNome.setEnabled(false);
		left.add(txtNome, gbc1);
		
		lblPartido = new JLabel("Partido:    ");
		gbc1.gridx=0; gbc1.gridy=2;
		//lblPartido.setVisible(habilita);
		left.add(lblPartido, gbc1);
		
		txtPartido = new JTextField(7);
		gbc1.gridx = 1;
		//txtPartido.setVisible(habilita);
		txtPartido.setEnabled(false);
		left.add(txtPartido, gbc1);
		
		this.add(left, BorderLayout.WEST);
		
		/********* DIREITA ************/
		layoutRight = new GridBagLayout();
		
		right = new JPanel();
		right.setLayout(layoutRight);
		//right.setBackground(Color.GREEN);
		/*
		 * icnImagemRosto = new ImageIcon(getClass().getResource(arg0));
		 * rig
		*/
		//right.add(txtSecao);
		this.add(right, BorderLayout.EAST);
		
		
		/************* SUL ***********/
		layoutBottom = new GridBagLayout();
		GridBagConstraints gbc3 = new GridBagConstraints();
		
		bottom = new JPanel();
		bottom.setLayout(layoutBottom);
		//bottom.setBackground(Color.MAGENTA);
		
		teclas1 = new JLabel("Aperte a telcla");
		teclas2 = new JLabel("VERDE para CONFIRMAR este voto");
		teclas3 = new JLabel("Laranja para REINICIAR este voto");
		gbc3.gridx=0; gbc3.gridy=0;
		bottom.add(teclas1, gbc3);
		gbc3.gridy=1;
		bottom.add(teclas2, gbc3);
		gbc3.gridy=2;
		bottom.add(teclas3, gbc3);
		
		btnBranco = new JButton("Branco");
		gbc3.gridx = 5; gbc3.gridy = 3;
		btnBranco.setBackground(Color.WHITE);
		//btnBranco.setEnabled(false);
		bottom.add(btnBranco, gbc3);
		
		btnCorrige = new JButton("Corrige");
		gbc3.gridx = 6;
		btnCorrige.setBackground(Color.ORANGE);
		bottom.add(btnCorrige, gbc3);
		
		btnConfirma = new JButton("Confirma");
		gbc3.gridx = 7; //gbc3.gridy =1;
		btnConfirma.setBackground(Color.GREEN);
		bottom.add(btnConfirma, gbc3);
		this.add(bottom, BorderLayout.PAGE_END);
		
		btnBranco.addActionListener(evento);
		btnConfirma.addActionListener(evento);
		btnCorrige.addActionListener(evento);
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private class Evento implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == btnConfirma) {
				if(txtNumero.getText().length()>0) {
					u.colherVoto(voto);
				}
				e.setSource(btnCorrige);
				btnBranco.setEnabled(false);
				btnConfirma.setEnabled(false);
				btnCorrige.setEnabled(false);
			}
			
			if (e.getSource() == btnCorrige) {
				txtNome.setText("");
				txtNumero.setText("");
				txtPartido.setText("");
				habilita = false;
			}
			
			if (e.getSource() == btnBranco) {
				Candidato c = new Candidato();
				int s = u.getSecao();
				Date horaVoto = new Date();
				voto = new Voto(eleitor, c, s, horaVoto);
				u.colherVoto(voto);
			}
		}
		
	}
}
