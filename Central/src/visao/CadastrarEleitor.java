package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controle.CentralDeDados;

public class CadastrarEleitor extends JFrame{
	
	private JPanel left;
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
	
	private GridBagConstraints gbc1;
	
	CentralDeDados c;
	
	public CadastrarEleitor(CentralDeDados c) {
		super("Cadastrar Eleitor");
		
		this.c = new CentralDeDados();
		this.c = c;
		
		/********** TOP ***********/
		layout = new GridBagLayout();
		gbc1 = new GridBagConstraints();
		
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
		
		txtCPF = new JTextField(14);
		gbc1.gridx=1; 
		txtCPF.setEnabled(true);
		left.add(txtCPF, gbc1);
		
		lblTituloEleitor = new JLabel("Título de Eleitor:");
		gbc1.gridx=0; gbc1.gridy=2;
		left.add(lblTituloEleitor, gbc1);
		
		txtTituloEleitor = new JTextField(14);
		gbc1.gridx=1;
		txtTituloEleitor.setEnabled(true);
		left.add(txtTituloEleitor, gbc1);
		
		lblSecao = new JLabel("Seção:");
		gbc1.gridx=0; gbc1.gridy=3;
		left.add(lblSecao, gbc1);
		
		txtSecao = new JTextField(5);
		gbc1.gridx=1;
		txtSecao.setEnabled(false);
		left.add(txtSecao, gbc1);
		
		Evento evento = new Evento();
		
		btnFoto = new JButton("Selecionar Foto");
		gbc1.gridx = 0; gbc1.gridy=4;
		left.add(btnFoto, gbc1);
		
		btnFoto.addActionListener(evento);
		
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
				boolean r = c.cadastrarEleitor(Integer.parseInt(txtTituloEleitor.getText()), 
						txtNome.getText(), txtCPF.getText(), null, Integer.parseInt(txtSecao.getText()));
				//System.out.println(c.toString());
				//txtNome.setText("");
				//txtNumero.setText("");
				if (r == true)
					//System.out.println("Partido político Cadastrado");
					JOptionPane.showMessageDialog(null, "Eleitor Cadastrado");
				else
					//System.out.println("ERRO: Nome ou número já está cadastrado");
					JOptionPane.showMessageDialog(null, "ERRO: CPF já existente");
				
			}
			if (e.getSource() == btnLimpar) {
				txtNome.setText("Nome");
				txtCPF.setText("CPF");
				txtSecao.setText("Secao");
				txtTituloEleitor.setText("Título de Eleitor");
				
			}
			if (e.getSource() == btnFoto) {
				fchFoto = new JFileChooser("/home/paulacunha/eclipse-workspace/Central/bin/visao");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "PPM Images", "ppm");  //Cria um filtro
				//filtro somente para fotos
				fchFoto.setFileFilter(filter);
				
				
				
				
				
//				final JPanel preview = new JPanel();
//				preview.setPreferredSize(new Dimension(150, 150));
//				addPropertyChangeListener(new PropertyChangeListener() {
//					
//					@Override
//					public void propertyChange(PropertyChangeEvent e) {
//						String propertyName = e.getPropertyName();
//				        if (propertyName.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)){
//				        	File selection = (File) e.getNewValue();
//				        	String name;
//				        	if (selection == null)
//				        		return;
//				        	else
//				        		name = selection.getAbsolutePath();
//				        	Icon newImage = new ImageIcon(name);
//				        	preview.set(Image)newImage);
//				        }
//		            }
//				});

				
				
				
				
				
				
				//Abre o diálogo JFileChooser
				int returnVal = fchFoto.showOpenDialog(getParent()); 
				
				//Verifica se o usuário clicou no botão OK
				if(returnVal == JFileChooser.APPROVE_OPTION) {  
					//Apresenta uma mensagem informando o nome do arquivo/diretório selecionado
					icnImagemRosto = new ImageIcon(fchFoto.getSelectedFile().getAbsolutePath());
					lblImagemRosto = new JLabel(icnImagemRosto);
					gbc1.gridx = 4; gbc1.gridy=0;
					left.add(lblImagemRosto, gbc1);
				}
			}

		}
		
	}
}
