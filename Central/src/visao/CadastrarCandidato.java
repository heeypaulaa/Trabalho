package visao;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CadastrarCandidato extends JFrame{
	
	private JPanel left;
	private JPanel right;
	private JPanel bottom;
	
	private GridBagLayout layout;
	private GridBagLayout layoutSul;
	
	private JLabel lblNome;
	private JLabel lblCPF;
	private JLabel lblImagemRosto;
	private JLabel lblPartido;
	private JLabel lblNumero;
	
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtPartido;
	private JTextField txtNumero;
	private Icon icnImagemRosto;
	
	private JButton btnCadastrar;
	private JButton btnLimpar;
	private JButton btnCancelar;
	
	public CadastrarCandidato() {
		super("Cadastrar Candidato");
		
		/********** TOP ***********/
		layout = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		
		left = new JPanel();
		left.setLayout(layout);
		
		lblNome = new JLabel("Nome: ");
		gbc1.gridx=0; gbc1.gridy=0;
		left.add(lblNome, gbc1);
		
		txtNome = new JTextField(30);
		gbc1.gridx=1;
		txtNome.setEnabled(true);
		left.add(txtNome, gbc1);
		
		lblCPF = new JLabel("CPF:");
		gbc1.gridx=0; gbc1.gridy=1;
		left.add(lblCPF,gbc1);
		
		txtCPF = new JTextField("CPF", 14);
		gbc1.gridx=1; 
		txtCPF.setEnabled(false);
		left.add(txtCPF, gbc1);
		
		lblNumero = new JLabel("Número:");
		gbc1.gridx=0; gbc1.gridy=2;
		left.add(lblNumero, gbc1);
		
		txtNumero = new JTextField(5);
		gbc1.gridx=1;
		txtNumero.setEnabled(true);
		left.add(txtNumero, gbc1);
		
		lblPartido = new JLabel("Paritido Político:");
		gbc1.gridx=0; gbc1.gridy=3;
		left.add(lblPartido, gbc1);
		
		txtPartido = new JTextField(7);
		gbc1.gridx=1;
		txtPartido.setEnabled(false);
		left.add(txtPartido, gbc1);

		
		this.add(left, BorderLayout.CENTER);
		
		/************* SUL ***********/
		layoutSul = new GridBagLayout();
		
		bottom = new JPanel();
		bottom.setLayout(layoutSul);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(true);
		bottom.add(btnCancelar);
		
		btnLimpar = new JButton("Limpar");
		bottom.add(btnLimpar);
		
		btnCadastrar = new JButton("Cadastrar");
		bottom.add(btnCadastrar);
		this.add(bottom, BorderLayout.SOUTH);
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}


}
