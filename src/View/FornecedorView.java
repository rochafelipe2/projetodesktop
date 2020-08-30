package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.ClienteFilterModel;
import Model.ClienteModel;
import Model.FornecedorModel;
import Model.IModel;
import Services.FornecedorService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class FornecedorView extends JFrame {

	private JPanel contentPane;
	private JTextField nomeFornecedor;
	private JTextField descricaoFornecedor;
	private JTable table;
	private int selectedId;
	static FornecedorView frame = new FornecedorView();

	private FornecedorService service = new FornecedorService("fornecedores");
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
	public FornecedorView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 473, 366);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblnome = new JLabel("Nome");
		lblnome.setBounds(28, 11, 34, 16);
		panel.add(lblnome);
		
		nomeFornecedor = new JTextField();
		nomeFornecedor.setBounds(96, 5, 180, 28);
		panel.add(nomeFornecedor);
		nomeFornecedor.setColumns(10);
		
		descricaoFornecedor = new JTextField();
		descricaoFornecedor.setBounds(96, 48, 180, 28);
		panel.add(descricaoFornecedor);
		descricaoFornecedor.setColumns(10);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(6, 91, 78, 28);
		panel.add(btnNewButton);
		
		JLabel lbldescricao = new JLabel("Descri\u00E7\u00E3o");
		lbldescricao.setBounds(28, 54, 56, 16);
		panel.add(lbldescricao);
		
		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.setBounds(95, 91, 84, 28);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remover");
		btnNewButton_2.setBounds(191, 91, 107, 28);
		panel.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 131, 451, 229);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	           if(table.getSelectedRow() >= 0){
		            selectedId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());

	           }
	          
	        }
	    });
		loadTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_3 = new JButton("Voltar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton_3.setBounds(347, 5, 120, 28);
		panel.add(btnNewButton_3);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Remover
				if(selectedId > 0){
					
					int flag = JOptionPane.showConfirmDialog(frame, "Deja continuar?");
					if(flag == 0){
						service.Remover(selectedId);
						JOptionPane.showMessageDialog(null,"Removido!",
								  "Sucesso!",2);
						loadTable();
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um item da lista para remover.");
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(selectedId > 0){
					FornecedorViewEdite frameEdite = new FornecedorViewEdite(selectedId,frame);
					frameEdite.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um item da lista para editar.");
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nomeFornecedor.getText().isEmpty() || descricaoFornecedor.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Todos os campos são obrigatórios!",
							  "Atenção.!",0);
				}else{
					FornecedorModel model = new FornecedorModel();
					model.setNome(nomeFornecedor.getText());
					model.setDescricao(descricaoFornecedor.getText());
					
					
					service.Adicionar(model);
					JOptionPane.showMessageDialog(null,"Salvo com sucesso!",
							  "Sucesso!",2);
					
					
					nomeFornecedor.setText("");
					descricaoFornecedor.setText("");
					
					loadTable();
				}
			}
		});
		
	}
	
	public void loadTable(){
		table.setModel(new DefaultTableModel());
		ArrayList<IModel> results = service.Buscar(new ClienteFilterModel());
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> col = new Vector<String>();
		col.add("Id");
		col.add("Nome");
		col.add("Descrição");
		
		results.forEach( (x) -> {
			Vector<String> interno = new Vector<String>();
			FornecedorModel temp = (FornecedorModel) x;
			interno.add(temp.getId()+"");
			interno.add(temp.getNome());
			interno.add(temp.getDescricao());
			
			data.add(interno);
		});

		System.out.println("Atualizou de novo!!!"+data.toString());
		
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		
	}
}
