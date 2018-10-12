package visao;

/****
 * @author paulacunha
 * 
 * 
 ****/
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controle.CentralDeDados;

public class CadastrarPartido extends JFrame{
	
	private JPanel left;
	private JPanel bottom;
	
	private GridBagLayout layout;
	private GridBagLayout layoutSul;
	
	private JLabel lblNome;
	private JLabel lblNumero;
	
	private JTextField txtNome;
	private JTextField txtNumero;
	
	private JButton btnCadastrar;
	private JButton btnLimpar;
	private CentralDeDados c;
	
	public CadastrarPartido(CentralDeDados c) {
		super("Cadastrar Partido");
		
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
		
		txtNome = new JTextField(7);
		gbc1.gridx=1;
		txtNome.setEnabled(true);
		left.add(txtNome, gbc1);
		
		lblNumero = new JLabel("Número: ");
		lblNumero.setLocation(10, 15);
		gbc1.gridx=0; gbc1.gridy=1;
		left.add(lblNumero, gbc1);
		
		txtNumero = new JTextField(2);
		gbc1.gridx=1; 
		txtNumero.setEnabled(true);
		left.add(txtNumero, gbc1);
		
		/************* SUL ***********/
		layoutSul = new GridBagLayout();
		Evento evento = new Evento();
		
		bottom = new JPanel();
		bottom.setLayout(layoutSul);
				
		btnLimpar = new JButton("Limpar");
		btnLimpar.setEnabled(true);
		bottom.add(btnLimpar);
		
		btnCadastrar = new JButton("Cadastrar");
		bottom.add(btnCadastrar);
		
		btnCadastrar.addActionListener(evento);
		btnLimpar.addActionListener(evento);
		
		
		
		this.add(left, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
		
		setSize(300,200);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	private class Evento implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == btnCadastrar) {
				System.out.println(txtNome.getText() +" - - " + txtNumero.getText());
				
				boolean r = c.cadastrarPartidos(txtNome.getText(), Integer.parseInt(txtNumero.getText()) );
				System.out.println(c.toString());
				txtNome.setText("");
				txtNumero.setText("");
				if (r == true)
					//JOptionPane.showm
					JOptionPane.showMessageDialog(null, "Partido político Cadastrado");
				else
					//System.err.println("ERRO: Nome ou número já está cadastrado");
					JOptionPane.showMessageDialog(null, "ERRO: Nome ou número já está cadastrado");
				
			}
			if (e.getSource() == btnLimpar) {
				txtNome.setText("");
				txtNumero.setText("");
				
			}
						
		}
		
	}

}
