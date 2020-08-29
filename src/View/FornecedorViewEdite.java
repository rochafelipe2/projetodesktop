package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.ClienteModel;
import Model.FornecedorModel;
import Services.FornecedorService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FornecedorViewEdite extends JFrame {

	private JPanel contentPane;
	private JTextField nomeFornecedor;
	private JTextField descricaoFornecedor;
	private FornecedorModel model;
	private FornecedorService service = new FornecedorService("fornecedores");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FornecedorViewEdite frame = new FornecedorViewEdite(0, null);
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
	public FornecedorViewEdite(int id, JFrame parentFrame) {
		model = (FornecedorModel)service.Buscar(id);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(10, 30, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lbldescricao = new JLabel("Descri\u00E7\u00E3o");
		lbldescricao.setBounds(10, 69, 69, 14);
		contentPane.add(lbldescricao);
		
		nomeFornecedor = new JTextField();
		nomeFornecedor.setBounds(105, 27, 162, 20);
		nomeFornecedor.setText(model.nome);
		
		contentPane.add(nomeFornecedor);
		nomeFornecedor.setColumns(10);
		
		descricaoFornecedor = new JTextField();
		descricaoFornecedor.setBounds(105, 66, 162, 20);
		descricaoFornecedor.setText(model.descricao);
		contentPane.add(descricaoFornecedor);
		descricaoFornecedor.setColumns(10);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.nome = nomeFornecedor.getText();
				model.descricao = lbldescricao.getText();
				
				service.Atualizar(model);
				setVisible(false);
				((FornecedorView)parentFrame).loadTable();
				
			}
		});
		btnNewButton.setBounds(10, 108, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(109, 108, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
