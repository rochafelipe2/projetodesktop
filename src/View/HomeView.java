package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Component;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnConsumidores = new JButton("Consumidores");
		btnConsumidores.setBounds(10, 29, 185, 23);
		btnConsumidores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConsumidorView frame = new ConsumidorView();
					frame.setVisible(true);
				} catch (Exception error) {
					error.printStackTrace();
				}
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnConsumidores);
		
		JButton btnFornecedores = new JButton("Fornecedores");
		btnFornecedores.setBounds(10, 63, 185, 23);
		btnFornecedores.setVerticalAlignment(SwingConstants.TOP);
		btnFornecedores.setAlignmentX(0.5f);
		btnFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FornecedorView frame = new FornecedorView();
				frame.setVisible(true);
			}
		});
		contentPane.add(btnFornecedores);
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.setBounds(10, 97, 185, 23);
		contentPane.add(btnProdutos);
	}

}
