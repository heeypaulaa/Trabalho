/**
 * @author paulacunha
 *
 **/

package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controle.Urna;
import image.PGMFileReader;
import image.PGMImage;
import image.PPMFileReader;
import modelo.Eleitor;

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
	private JButton btnFoto;
	
	private String imagePath;
	private String nomeImage = "";
	private PGMImage image;
	
	private GridBagConstraints gbc1;
	private Eleitor eleitor = new Eleitor();
	
	private Urna u;
	
	public Login(Urna u) {
		super("Login");
		this.u = new Urna();
		this.u = u;
		System.out.println("construtor Login");
		System.out.println("secao"+this.u.getSecao());
		
		/********** LEFT ***********/
		layoutLeft = new GridBagLayout();
		gbc1 = new GridBagConstraints();
		
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
		
		btnFoto = new JButton("Selecionar foto");
		gbc1.gridx = 0; gbc1.gridy=4;
		left.add(btnFoto, gbc1);
		
		Evento e = new Evento();
		btnFoto.addActionListener(e);
		
		this.add(left, BorderLayout.WEST);
		
		/********* DIREITA ************/
		layoutRight = new GridBagLayout();
		//setLayout(layoutRight);
		
		right = new JPanel();
		//right.setBackground(Color.GREEN);
		
		icnImagemRosto = new ImageIcon();
		lblImagemRosto = new JLabel(icnImagemRosto);
		
		//* rig
		
		right.add(lblImagemRosto);
		this.add(right, BorderLayout.EAST);
		
		
		/************* SUL ***********/
		layoutBottom = new GridBagLayout();
		
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
		
		btnConfirma.addActionListener(e);
		btnCorrige.addActionListener(e);
		btnFoto.addActionListener(e);
		
		setSize(800,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
	}
	
	public void draw() {
        MemoryImageSource source = new MemoryImageSource(image.getWidth(), image.getHeight(), ColorModel.getRGBdefault(), image.toRGBModel(), 0, image.getWidth());
        Image img =Toolkit.getDefaultToolkit().createImage(source);
        //lblImagemRosto.setIcon(null);
        this.remove(lblImagemRosto);
        lblImagemRosto = new JLabel(new ImageIcon(img));
        gbc1.gridx = 4; gbc1.gridy=0;
    	right.add(lblImagemRosto, gbc1);
        //this.add(lblImagemRosto, BorderLayout.CENTER);
        this.validate();   
    }
	
	private class Evento implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fchFoto;
			if(e.getSource() == btnConfirma) {
//				Eleitor el = new Eleitor();
//				el = u.getEleitor(txtCPF.getText());
//				new ColherVoto(u, el);
				new ColherVoto(u,eleitor);
				//e.setSource(btnCorrige);
			}
			if(e.getSource() == btnCorrige) {
				txtNome.setText("");
				txtCPF.setText("");
				txtSecao.setText("");
				txtTituloEleitor.setText("");
			}
			if(e.getSource() == btnFoto) {
				//System.out.println(getClass().getResource(null).toString());
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
			}
			if(image != null) {
				draw();
				System.out.println(nomeImage);
				eleitor = u.getEleitor(nomeImage);
				System.out.println(u.getSecao());
				System.out.println("depois");

				if(u.ComparaArqPPM(("/home/paulacunha/eclipse-workspace/Central/bin/visao/"+nomeImage),
						("/home/paulacunha/eclipse-workspace/Central/bin/visao/"+nomeImage))) {
					txtNome.setText(eleitor.getNome());
					txtCPF.setText(eleitor.getCpf());
					txtSecao.setText(Integer.toString(eleitor.getSessao()));
					txtTituloEleitor.setText(Integer.toString(eleitor.getTituloEleitor()));
				}
	            
	        }
		}
	}
}
