package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class HomeView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView frame = new HomeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnConsumidores = new JButton("Consumidores");
		btnConsumidores.setBounds(10, 119, 185, 23);
		btnConsumidores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConsumidorView.main(new String[]{""});
				} catch (Exception error) {
					error.printStackTrace();
				}
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnConsumidores);
		
		JButton btnFornecedores = new JButton("Fornecedores");
		btnFornecedores.setBounds(10, 154, 185, 23);
		btnFornecedores.setVerticalAlignment(SwingConstants.TOP);
		btnFornecedores.setAlignmentX(0.5f);
		btnFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				FornecedorView.main(new String[]{""});
				
			}
		});
		contentPane.add(btnFornecedores);
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoView.main(new String[]{""});
			}
		});
		btnProdutos.setBounds(10, 189, 185, 23);
		contentPane.add(btnProdutos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(246, 196, 75, -106);
		contentPane.add(separator);
		
		JLabel lblOlSejaBem = new JLabel("Ol\u00E1. seja bem vindo ao aplicativo desktop desenvolvido na UTFPR.\r\n");
		lblOlSejaBem.setBounds(31, 6, 413, 59);
		contentPane.add(lblOlSejaBem);
		
		JLabel lblAbaixoAsOpes = new JLabel(" Abaixo as op\u00E7\u00F5es de cadastro.");
		lblAbaixoAsOpes.setBounds(31, 49, 368, 16);
		contentPane.add(lblAbaixoAsOpes);
		
		JLabel lblDesenvolvidoPorFelipe = new JLabel("Desenvolvido por Felipe Rocha | 2020");
		lblDesenvolvidoPorFelipe.setBounds(209, 439, 269, 16);
		contentPane.add(lblDesenvolvidoPorFelipe);
		
		ImageIcon icon = createImageIcon();
		
		JLabel img = new JLabel(icon);
		contentPane.add(img);
	}
	
	protected ImageIcon createImageIcon() {
			
	return new ImageIcon("C:\\Users\\lipdi\\workspace\\ProjetoDesktop\\src\\View\\img.jpg", "");
	}
			
}
