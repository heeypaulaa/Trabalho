package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

public class CadastrarEleitor extends JFrame{
	
	private JPanel left;
	private JPanel right;
	private JPanel bottom;
	
	private GridBagLayout layout;
	private GridBagLayout layoutSul;
	
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
	
	private JButton btnFoto;
	private JButton btnCadastrar;
	private JButton btnLimpar;
	
	public CadastrarEleitor() {
		super("Cadastrar Eleitor");
		
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
		
		lblTituloEleitor = new JLabel("Título de Eleitor:");
		gbc1.gridx=0; gbc1.gridy=2;
		left.add(lblTituloEleitor, gbc1);
		
		txtTituloEleitor = new JTextField("Título de Eleitor",14);
		gbc1.gridx=1;
		txtTituloEleitor.setEnabled(false);
		left.add(txtTituloEleitor, gbc1);
		
		lblSecao = new JLabel("Seção:");
		gbc1.gridx=0; gbc1.gridy=3;
		left.add(lblSecao, gbc1);
		
		txtSecao = new JTextField("Seção", 5);
		gbc1.gridx=1;
		txtSecao.setEnabled(false);
		left.add(txtSecao, gbc1);
		
		btnFoto = new JButton("Selecionar Foto");
		gbc1.gridx = 0; gbc1.gridy=4;
		left.add(btnFoto, gbc1);
		
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
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}

	private class Evento implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fchFoto;
			
			if (e.getSource() == btnCadastrar) {
				//System.out.println(txtNome.getText() +" - - " + txtNumero.getText());
				
				//boolean r = c.cadastrarPartidos(txtNome.getText(), Integer.parseInt(txtNumero.getText()) );
				//System.out.println(c.toString());
				txtNome.setText("");
				//txtNumero.setText("");
				//if (r == true)
					//System.out.println("Partido político Cadastrado");
					JOptionPane.showMessageDialog(null, null, "Partido político Cadastrado", NORMAL);
				//else
					//System.out.println("ERRO: Nome ou número já está cadastrado");
					JOptionPane.showMessageDialog(null, null, "ERRO: Nome ou número já está cadastrado", ERROR);
				
			}
			if (e.getSource() == btnLimpar) {
				txtNome.setText("");
				//txtNumero.setText("");
				
			}
			if (e.getSource() == btnFoto) {
				fchFoto = new JFileChooser();
				
				//filtro somente para fotos
				//fchFoto.addChoosableFileFilter();
				fchFoto.setAcceptAllFileFilterUsed(false);
				
				//adiciona icones padrões
				fchFoto.setFileView(FileView);
				
			}

		}
		
	}
}
