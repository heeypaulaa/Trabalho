package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ColherVoto extends JFrame{
	private JPanel left;
	private JPanel right;
	private JPanel bottom;
	
	private GridBagLayout layoutLeft;
	private GridBagLayout layoutRight;
	private GridBagLayout layoutBottom;
	
	private JLabel lblNome;
	private JLabel lblCPF;
	private JLabel lblImagemRosto;
	private JLabel lblNumero;
	private JLabel lblPartido;
	
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtNumero;
	private JTextField txtPartido;
	private Icon icnImagemRosto;

	private JLabel teclas;
	private JButton btnConfirma;
	private JButton btnCorrige;
	private JButton btnBranco;

	private boolean habilita = true; 
	
	public ColherVoto() {
		super("Colher Voto");
		
		/********** LEFT ***********/
		layoutLeft = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.gridwidth = -4;
		left = new JPanel();
		left.setLayout(layoutLeft);
		left.setBackground(Color.BLUE);
		
		lblNumero = new JLabel("Número:    ");
		gbc1.gridx=0; gbc1.gridy=0;
		left.add(lblNumero, gbc1);
		
		txtNumero = new JTextField(5);
		gbc1.gridx=1;
		txtNumero.setEnabled(!habilita);
		left.add(txtNumero, gbc1);
		
		lblNome = new JLabel("Nome:    ");
		gbc1.gridx=0; gbc1.gridy=1;
		lblNome.setVisible(habilita);
		left.add(lblNome,gbc1);
		
		txtNome = new JTextField(20);
		gbc1.gridx=1;
		txtNome.setVisible(habilita);
		txtNome.setEnabled(false);
		left.add(txtNome, gbc1);
		
		lblPartido = new JLabel("Partido:    ");
		gbc1.gridx=0; gbc1.gridy=2;
		lblPartido.setVisible(habilita);
		left.add(lblPartido, gbc1);
		
		txtPartido = new JTextField(7);
		gbc1.gridx = 1;
		txtPartido.setVisible(habilita);
		txtPartido.setEnabled(false);
		left.add(txtPartido, gbc1);
		
		this.add(left, BorderLayout.WEST);
		
		/********* DIREITA ************/
		layoutRight = new GridBagLayout();
		//setLayout(layoutRight);
		
		right = new JPanel();
		right.setLayout(layoutRight);
		right.setBackground(Color.GREEN);
		/*
		 * icnImagemRosto = new ImageIcon(getClass().getResource(arg0));
		 * rig
		*/
		//right.add(txtSecao);
		this.add(right, BorderLayout.EAST);
		
		
		/************* SUL ***********/
		layoutBottom = new GridBagLayout();
		GridBagConstraints gbc3 = new GridBagConstraints();
		//setLayout(layoutBottom);
		
		bottom = new JPanel();
		bottom.setLayout(layoutBottom);
		bottom.setBackground(Color.MAGENTA);
		
		teclas = new JLabel("Aperte a telcla\n"
				+ "VERDE para CONFIRMAR este voto\n"
				+ "Laranja para REINICIAR este voto");
		//gbc3.gridx=0; gbc3.gridy=0;
		teclas.setVisible(habilita);
		bottom.add(teclas, gbc3);
		
		btnBranco = new JButton("Branco");
		gbc3.gridx =0; gbc3.gridy =1;
		btnBranco.setBackground(Color.WHITE);
		btnBranco.setEnabled(false);
		bottom.add(btnBranco, gbc3);
		
		btnCorrige = new JButton("Corrige");
		gbc3.gridx = 1; //gbc3.gridy =1;
		btnCorrige.setBackground(Color.ORANGE);
		bottom.add(btnCorrige, gbc3);
		
		btnConfirma = new JButton("Confirma");
		gbc3.gridx = 2; //gbc3.gridy =1;
		btnConfirma.setBackground(Color.GREEN);
		bottom.add(btnConfirma, gbc3);
		this.add(bottom, BorderLayout.PAGE_END);
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}

}
