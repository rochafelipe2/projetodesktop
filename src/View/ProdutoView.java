package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Control.ControllerProduto;
import Model.ClienteFilterModel;
import Model.FornecedorModel;
import Model.IModel;
import Model.ModelBase;
import Model.ProdutoModel;
import Services.FornecedorService;
import Services.ProdutoService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProdutoView extends JFrame {

	private JPanel contentPane;
	private JTextField nomeProduto;
	private JTextField descricaoProduto;
	private JTextField quantidadeProduto;
	private JTable table;
	private int selectedId;
	static ProdutoView frame = new ProdutoView();
	//private ProdutoService service = new ProdutoService("produtos");
	private ControllerProduto controler = new ControllerProduto();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	public ProdutoView() {
		controler.setArquivo("produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblDescricao = new JLabel("Descricao");
		lblDescricao.setBounds(10, 49, 46, 14);
		contentPane.add(lblDescricao);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 87, 46, 14);
		contentPane.add(lblQuantidade);
		
		nomeProduto = new JTextField();
		nomeProduto.setBounds(89, 8, 125, 20);
		contentPane.add(nomeProduto);
		nomeProduto.setColumns(10);
		
		descricaoProduto = new JTextField();
		descricaoProduto.setBounds(89, 46, 125, 20);
		contentPane.add(descricaoProduto);
		descricaoProduto.setColumns(10);
		
		quantidadeProduto = new JTextField();
		quantidadeProduto.setBounds(89, 84, 125, 20);
		contentPane.add(quantidadeProduto);
		quantidadeProduto.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Add
				if(nomeProduto.getText().isEmpty() || descricaoProduto.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Todos os campos são obrigatórios!",
							  "Atenção.!",0);
				}else{
					ProdutoModel model = new ProdutoModel();
					model.setNome(nomeProduto.getText());
					model.setDescricao(descricaoProduto.getText());
					model.setQuantidade(Integer.parseInt(quantidadeProduto.getText()));
					
					controler.add(model);
					JOptionPane.showMessageDialog(null,"Salvo com sucesso!",
							  "Sucesso!",2);
					
					
					nomeProduto.setText("");
					descricaoProduto.setText("");
					
					loadTable();
				}
			}
		});
		btnSalvar.setBounds(10, 126, 112, 23);
		contentPane.add(btnSalvar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Editar
				if(selectedId > 0){
					ProdutoViewEdite frameEdite = new ProdutoViewEdite(selectedId,frame);
					frameEdite.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um item da lista para editar.");
				}
			}
		});
		btnEditar.setBounds(132, 126, 105, 23);
		contentPane.add(btnEditar);
		
		JButton btnNewButton = new JButton("Remover");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Remover
					if(selectedId > 0){
					
					int flag = JOptionPane.showConfirmDialog(frame, "Deja continuar?");
					if(flag == 0){
						controler.remover(selectedId);
						JOptionPane.showMessageDialog(null,"Removido!",
								  "Sucesso!",2);
						loadTable();
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um item da lista para remover.");
				}	
			}
		});
		btnNewButton.setBounds(247, 126, 105, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 190, 401, 129);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		loadTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	           if(table.getSelectedRow() >= 0){
		            selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

	           }
	          
	        }
	    });
		scrollPane.setViewportView(table);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVoltar.setBounds(340, 4, 125, 28);
		contentPane.add(btnVoltar);

	}
	
	public void loadTable(){
		table.setModel(new DefaultTableModel());

		ArrayList<ModelBase> results = controler.buscar();
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> col = new Vector<String>();
		col.add("Id");
		col.add("Nome");
		col.add("Descrição");
		col.add("Quantidade");
		results.forEach( (x) -> {
			Vector<String> interno = new Vector<String>();
			ProdutoModel temp = (ProdutoModel) x;
			interno.add(temp.getId()+"");
			interno.add(temp.getNome());
			interno.add(temp.getDescricao());
			interno.add(temp.getQuantidade()+"");
			data.add(interno);
		});

		System.out.println("Atualizou de novo!!!"+data.toString());
		
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		
	}
}
