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

public class Login extends JFrame{
	
	private JPanel left;
	private JPanel right;
	private JPanel bottom;
	
	private GridBagLayout layoutLeft;
	private GridBagLayout layoutRight;
	private GridBagLayout layoutBottom;
	
	private JLabel lblNome;
	private JLabel lblCPF;
	private JLabel lblImagemRosto;
	private JLabel lblSecao;
	private JLabel lblTituloEleitor;
	
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtSecao;
	private JTextField txtTituloEleitor;
	private Icon icnImagemRosto;
	
	private JButton btnConfirma;
	private JButton btnCorrige;
	private JButton btnBranco;
	
	
	public Login() {
		super("Login");
		
		/********** LEFT ***********/
		layoutLeft = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		
		left = new JPanel();
		left.setLayout(layoutLeft);
		//left.setBackground(Color.BLUE);
		
		lblNome = new JLabel("Nome:");
		gbc1.gridx=0; gbc1.gridy=0;
		left.add(lblNome,gbc1);
		
		txtNome = new JTextField("Nome",30);
		gbc1.gridx=1;
		txtNome.setEnabled(false);
		left.add(txtNome, gbc1);
		
		lblCPF = new JLabel("CPF:");
		lblCPF.setLocation(10, 15);
		gbc1.gridx=0; gbc1.gridy=1;
		left.add(lblCPF,gbc1);
		
		txtCPF = new JTextField("CPF", 30);
		gbc1.gridx=1; 
		txtCPF.setEnabled(false);
		left.add(txtCPF, gbc1);
		
		lblTituloEleitor = new JLabel("Título de Eleitor:");
		gbc1.gridx=0; gbc1.gridy=2;
		left.add(lblTituloEleitor, gbc1);
		
		txtTituloEleitor = new JTextField("Título de Eleitor",30);
		gbc1.gridx=1;
		txtTituloEleitor.setEnabled(false);
		left.add(txtTituloEleitor, gbc1);
		
		lblSecao = new JLabel("Seção:");
		gbc1.gridx=0; gbc1.gridy=3;
		left.add(lblSecao, gbc1);
		
		txtSecao = new JTextField("Seção", 30);
		gbc1.gridx=1;
		txtSecao.setEnabled(false);
		left.add(txtSecao, gbc1);
		
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
		//setLayout(layoutBottom);
		
		bottom = new JPanel();
		bottom.setLayout(layoutBottom);
		//bottom.setBackground(Color.MAGENTA);
		
		btnBranco = new JButton("Branco");
		btnBranco.setBackground(Color.WHITE);
		btnBranco.setEnabled(false);
		bottom.add(btnBranco);
		
		btnCorrige = new JButton("Corrige");
		btnCorrige.setBackground(Color.ORANGE);
		bottom.add(btnCorrige);
		
		btnConfirma = new JButton("Confirma");
		btnConfirma.setBackground(Color.GREEN);
		bottom.add(btnConfirma);
		this.add(bottom, BorderLayout.PAGE_END);
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	

}
