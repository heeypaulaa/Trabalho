package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;
import java.awt.image.MemoryImageSource;
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
import image.*;

public class CadastrarEleitor extends JFrame{
	
	private JPanel left;
	private JPanel right;
	private JPanel bottom;
	
	private GridBagLayout layout;
	private GridBagLayout layoutSul;
	private GridBagLayout layoutRight;
	
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
	
	private String imagePath;
	private String nomeImage = "";
	private PGMImage image;
	
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
		
		txtNome = new JTextField(20);
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
		//txtSecao.setEnabled(false);
		left.add(txtSecao, gbc1);
		
		Evento evento = new Evento();
		
		btnFoto = new JButton("Selecionar Foto");
		gbc1.gridx = 0; gbc1.gridy=4;
		left.add(btnFoto, gbc1);
		
		btnFoto.addActionListener(evento);
		
		this.add(left, BorderLayout.CENTER);
		
		/************* Direita ***********/
		layoutRight = new GridBagLayout();
		
		right = new JPanel();
		//right.setBackground(Color.GREEN);
		
		icnImagemRosto = new ImageIcon();
		lblImagemRosto = new JLabel(icnImagemRosto);
		
		 //* rig
		
		right.add(lblImagemRosto);
		this.add(right, BorderLayout.EAST);
		
		/************* SUL ***********/
		layoutSul = new GridBagLayout();
		
		bottom = new JPanel();
		bottom.setLayout(layoutSul);
				
		btnLimpar = new JButton("Limpar");
		bottom.add(btnLimpar);
		
		btnCadastrar = new JButton("Cadastrar");
		bottom.add(btnCadastrar);
		this.add(bottom, BorderLayout.SOUTH);
		
		btnCadastrar.addActionListener(evento);
		btnLimpar.addActionListener(evento);
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
	}
	
	public void draw() {
        MemoryImageSource source = new MemoryImageSource(image.getWidth(), image.getHeight(), ColorModel.getRGBdefault(), image.toRGBModel(), 0, image.getWidth());
        Image img =Toolkit.getDefaultToolkit().createImage(source);
        this.remove(lblImagemRosto);
        lblImagemRosto = new JLabel (new ImageIcon(img));
        gbc1.gridx = 4; gbc1.gridy=0;
    	right.add(lblImagemRosto, gbc1);
        //this.add(lblImagemRosto, BorderLayout.CENTER);
        this.validate();   
    }
	
	private class Evento implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fchFoto;
			if (e.getSource() == btnCadastrar) {
				System.out.println("aqui C "+ nomeImage);
				if (!(nomeImage.equals(""))) {
				System.out.println("aqui C2");
				boolean r = c.cadastrarEleitor(Integer.parseInt(txtTituloEleitor.getText()), 
						txtNome.getText(), txtCPF.getText(), nomeImage, Integer.parseInt(txtSecao.getText()));
				if (r == true)
					JOptionPane.showMessageDialog(null, "Eleitor Cadastrado");
				else
					JOptionPane.showMessageDialog(null, "ERRO: CPF já existente");
				}	
			}
			if (e.getSource() == btnLimpar) {
				txtNome.setText("");
				txtCPF.setText("");
				txtSecao.setText("");
				txtTituloEleitor.setText("");
				remove(lblImagemRosto);
				
			}
			if (e.getSource() == btnFoto) {
				fchFoto = new JFileChooser("/home/paulacunha/eclipse-workspace/Central/bin/visao");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PPM Images", "ppm");  //Cria um filtro
				//filtro somente para fotos
				fchFoto.setFileFilter(filter);
				//Abre o diálogo JFileChooser
				int returnVal = fchFoto.showOpenDialog(getParent());
				//Verifica se o usuário clicou no botão OK
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					nomeImage = fchFoto.getSelectedFile().getName();
					imagePath=fchFoto.getSelectedFile().getAbsolutePath();
					if (imagePath.toLowerCase().matches(".+\\.pgm")) {
	                    image=PGMFileReader.readImage(imagePath);
	                }
	                else if (imagePath.toLowerCase().matches(".+\\.ppm")) {
	                    image=PPMFileReader.readImage(imagePath).convertToPGM();
	                }
				}
				if(image != null) {
		            draw();
		        }
			}
			
		}
		
	}
}
