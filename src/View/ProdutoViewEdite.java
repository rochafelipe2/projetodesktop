package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.ControllerProduto;
import Model.FornecedorModel;
import Model.ProdutoModel;
import Services.ProdutoService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProdutoViewEdite extends JFrame {

	private JPanel contentPane;
	private JTextField nomeProduto;
	private JTextField descricaoProduto;
	private JTextField quantidadeProduto;
	private ProdutoModel model;
	private ControllerProduto service = new ControllerProduto();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoViewEdite frame = new ProdutoViewEdite(0, null);
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
	public ProdutoViewEdite(int id, JFrame parentFrame) {
		service.setArquivo("produtos");
		model = (ProdutoModel)service.buscar(id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 32, 64, 14);
		contentPane.add(lblNome);
		
		JLabel lblDescricao = new JLabel("Descricao");
		lblDescricao.setBounds(10, 61, 64, 14);
		contentPane.add(lblDescricao);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 91, 64, 14);
		contentPane.add(lblQuantidade);
		
		nomeProduto = new JTextField();
		nomeProduto.setBounds(84, 29, 169, 20);
		contentPane.add(nomeProduto);
		nomeProduto.setColumns(10);
		
		descricaoProduto = new JTextField();
		descricaoProduto.setBounds(84, 58, 169, 20);
		contentPane.add(descricaoProduto);
		descricaoProduto.setColumns(10);
		
		quantidadeProduto = new JTextField();
		quantidadeProduto.setBounds(84, 88, 169, 20);
		contentPane.add(quantidadeProduto);
		quantidadeProduto.setColumns(10);
		
		nomeProduto.setText(model.nome);
		descricaoProduto.setText(model.descricao);
		quantidadeProduto.setText(model.quantidade+"");
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.setNome(nomeProduto.getText());
				model.setDescricao(descricaoProduto.getText());
				model.setQuantidade(Integer.parseInt((quantidadeProduto.getText())));
				
				service.atualizar(model);
				JOptionPane.showMessageDialog(parentFrame, "Atualizado!");
				setVisible(false);
				((ProdutoView)parentFrame).loadTable();
			}
		});
		btnSalvar.setBounds(10, 129, 111, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(131, 129, 111, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblCodigoproduto = new JLabel("codigoProduto");
		lblCodigoproduto.setBounds(10, 11, 78, 14);
		contentPane.add(lblCodigoproduto);
		lblCodigoproduto.setText(model.id+"");
	}

}
