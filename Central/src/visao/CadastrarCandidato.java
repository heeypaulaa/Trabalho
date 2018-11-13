package visao;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.Icon;
import javax.swing.JButton;
//import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.filechooser.FileNameExtensionFilter;

import controle.CentralDeDados;
//import image.PGMFileReader;
//import image.PPMFileReader;

public class CadastrarCandidato extends JFrame{
	
	private JPanel left;
	//private JPanel right;
	private JPanel bottom;
	
	private GridBagLayout layout;
	private GridBagLayout layoutSul;
	
	private JLabel lblNome;
	private JLabel lblCPF;
	//private JLabel lblImagemRosto;
	private JLabel lblPartido;
	private JLabel lblNumero;
	
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtPartido;
	private JTextField txtNumero;
	//private Icon icnImagemRosto;
	
	private JButton btnCadastrar;
	private JButton btnLimpar;
	
	private CentralDeDados c;
	
	public CadastrarCandidato(CentralDeDados c) {
		super("Cadastrar Candidato");
		
		this.c = new CentralDeDados();
		this.c = c;
		
		/********** TOP ***********/
		layout = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		
		left = new JPanel();
		left.setLayout(layout);
		
		lblNome = new JLabel("Nome: ");
		gbc1.gridx=0; gbc1.gridy=0;
		left.add(lblNome, gbc1);
		
		txtNome = new JTextField(20);
		gbc1.gridx=1;
		//txtNome.setEnabled(true);
		left.add(txtNome, gbc1);
		
		lblCPF = new JLabel("CPF:");
		gbc1.gridx=0; gbc1.gridy=1;
		left.add(lblCPF,gbc1);
		
		txtCPF = new JTextField("CPF", 14);
		gbc1.gridx=1; 
		//txtCPF.setEnabled(false);
		left.add(txtCPF, gbc1);
		
		lblNumero = new JLabel("Número:");
		gbc1.gridx=0; gbc1.gridy=2;
		left.add(lblNumero, gbc1);
		
		txtNumero = new JTextField(5);
		gbc1.gridx=1;
		txtNumero.setEnabled(true);
		left.add(txtNumero, gbc1);
		
		lblPartido = new JLabel("Nome do Partido Político:");
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
				
		btnLimpar = new JButton("Limpar");
		bottom.add(btnLimpar);
		
		btnCadastrar = new JButton("Cadastrar");
		bottom.add(btnCadastrar);
		this.add(bottom, BorderLayout.SOUTH);
		
		Evento evento = new Evento();
		
		btnCadastrar.addActionListener(evento);
		btnLimpar.addActionListener(evento);
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
	}
	
	private class Evento implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {			
			if (e.getSource() == btnCadastrar) {
				txtPartido.setText(c.getPartido(Integer.parseInt(txtNumero.getText())).getNome());
				
				System.out.println(txtNome.getText() +" - - " + txtNumero.getText());

				boolean r = c.cadastrarCandidato(txtNome.getText(), Integer.parseInt(txtNumero.getText()),
						txtCPF.getText(), txtPartido.getText());
				//System.out.println(c.toString());
				if (r == true)
					//JOptionPane.showm
					JOptionPane.showMessageDialog(null, "Candidato Cadastrado");
//				else
//					//System.err.println("ERRO: Nome ou número já está cadastrado");
//					JOptionPane.showMessageDialog(null, "ERRO: Nome ou número já está cadastrado");
			}
			if (e.getSource() == btnLimpar) {
				txtNome.setText("");
				txtCPF.setText("");
				txtNumero.setText("");
				txtPartido.setText("");			
			}
			
			
		}
		
	}


}
