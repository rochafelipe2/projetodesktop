package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.ClienteFilterModel;
import Model.ConsumidorModel;
import Model.IModel;
import Model.ModelBase;
import Services.ConsumidorService;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import Control.ControllerArquivoBinario;
import Control.ControllerConsumidor;

import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JInternalFrame;
import javax.swing.ListSelectionModel;

public class ConsumidorView extends JFrame {
	static ConsumidorView frame = new ConsumidorView();
	private JPanel contentPane;
	private JTextField nomeCliente;
	private JTextField idadeCliente;
	private JTextField celularCliente;
	private ConsumidorService service = new ConsumidorService();
	private ControllerConsumidor controlador = new ControllerConsumidor();
	private JTable table;
	private int selectedId;
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
	public ConsumidorView() {
		controlador.setArquivo("consumidores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblnomeCliente = new JLabel("Nome");
		lblnomeCliente.setBounds(12, 16, 69, 16);
		panel.add(lblnomeCliente);
		
		nomeCliente = new JTextField();
		nomeCliente.setBounds(68, 11, 224, 26);
		panel.add(nomeCliente);
		nomeCliente.setColumns(10);
		
		JLabel lblidadeCliente = new JLabel("Idade");
		lblidadeCliente.setBounds(12, 53, 69, 16);
		panel.add(lblidadeCliente);
		
		JLabel lblcelularCliente = new JLabel("Celular");
		lblcelularCliente.setBounds(12, 90, 69, 16);
		panel.add(lblcelularCliente);
		
		idadeCliente = new JTextField();
		idadeCliente.setBounds(68, 48, 224, 26);
		idadeCliente.setColumns(10);
		panel.add(idadeCliente);
		
		celularCliente = new JTextField();
		celularCliente.setBounds(68, 85, 224, 26);
		celularCliente.setColumns(10);
		panel.add(celularCliente);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(12, 122, 117, 29);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		if(nomeCliente.getText().isEmpty() || idadeCliente.getText().isEmpty() || celularCliente.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(frame,"Todos os campos são obrigatórios!",
					  "Atenção.!",0);
		}else{
			
		
				ConsumidorModel model = new ConsumidorModel();
				model.setNome(nomeCliente.getText());
				model.setCelular(celularCliente.getText());
				model.setIdade(Integer.parseInt(idadeCliente.getText()));
				
				//service.Adicionar(model);
				controlador.add(model);
				JOptionPane.showMessageDialog(null,"Consumidor salvo com sucesso!",
						  "Sucesso!",1);
				
				
				nomeCliente.setText("");
				celularCliente.setText("");
				idadeCliente.setText("");
				loadTable();
		}
			}
		});
		panel.add(btnSalvar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 186, 418, 234);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setSurrendersFocusOnKeystroke(true);
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
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(selectedId > 0){
					ConsumidorViewEdite frameEdite = new ConsumidorViewEdite(selectedId,frame);
					frameEdite.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um cliente da lista para editar.");
				}
				
			}
		});
		btnEditar.setBounds(140, 123, 117, 28);
		panel.add(btnEditar);
		
		JButton btnNewButton = new JButton("Remover");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedId > 0){
					int flag = JOptionPane.showConfirmDialog(frame, "Deseja continuar?");
					if(flag == 0){
						//service.Remover(selectedId);
						controlador.remover(selectedId);
						JOptionPane.showMessageDialog(null,"Removido!",
								  "Sucesso!",2);
						loadTable();	
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um cliente da lista para remover.");
				}
			}
		});
		btnNewButton.setBounds(269, 123, 117, 28);
		panel.add(btnNewButton);
		
		JButton btnMenuPrincipal = new JButton("Voltar");
		btnMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnMenuPrincipal.setBounds(351, 0, 117, 28);
		panel.add(btnMenuPrincipal);
		
	}
	
	public void loadTable(){
		//ArrayList<IModel> results = service.Buscar(new ClienteFilterModel());
		
		ArrayList<ModelBase> results = controlador.buscar(); 
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> col = new Vector<String>();
		col.add("Id");
		col.add("Nome");
		col.add("Idade");
		col.add("Celular");
		results.forEach( (x) -> {
			Vector<String> interno = new Vector<String>();
			ConsumidorModel temp = (ConsumidorModel) x;
			interno.add(temp.getId()+"");
			interno.add(temp.getNome());
			interno.add(temp.getIdade()+"");
			interno.add(temp.getCelular());
			System.out.print(interno.toString());
			data.add(interno);
		});

		
		table.setModel(new DefaultTableModel(
				data,
				col
				)
			
		);
	}
}
