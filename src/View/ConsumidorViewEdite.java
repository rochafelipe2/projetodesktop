package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.ControllerConsumidor;
import Model.ConsumidorModel;
import Services.ConsumidorService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsumidorViewEdite extends JFrame {

	private JPanel contentPane;
	private JTextField clienteNome;
	private JTextField clienteIdade;
	private JTextField clienteCelular;
	private ConsumidorModel model;
	private ConsumidorService service = new ConsumidorService();
	private ControllerConsumidor controlador = new ControllerConsumidor();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsumidorViewEdite frame = new ConsumidorViewEdite(0, null);
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
	public ConsumidorViewEdite(int id, JFrame parentFrame) {
		controlador.setArquivo("consumidores");
		model = (ConsumidorModel)controlador.buscar(id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(10, 36, 46, 14);
		contentPane.add(lblNewLabel);
		
		clienteNome = new JTextField();
		clienteNome.setBounds(66, 33, 143, 20);
		clienteNome.setText(model.getNome());
		contentPane.add(clienteNome);
		clienteNome.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(66, 11, 46, 14);
		lblId.setText(model.getId()+"");
		contentPane.add(lblId);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 11, 46, 14);
		contentPane.add(lblCliente);
		
		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setBounds(10, 61, 46, 14);
		contentPane.add(lblIdade);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(10, 98, 46, 14);
		contentPane.add(lblCelular);
		
		clienteIdade = new JTextField();
		clienteIdade.setColumns(10);
		clienteIdade.setBounds(66, 64, 143, 20);
		clienteIdade.setText(model.getIdade()+"");
		contentPane.add(clienteIdade);
		
		clienteCelular = new JTextField();
		clienteCelular.setColumns(10);
		clienteCelular.setBounds(66, 95, 143, 20);
		clienteCelular.setText(model.getCelular());
		contentPane.add(clienteCelular);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.nome = clienteNome.getText();
				model.idade = Integer.parseInt(clienteIdade.getText());
				model.celular = clienteCelular.getText();
				//service.Atualizar(model);
				controlador.atualizar(model);
				setVisible(false);
				((ConsumidorView)parentFrame).loadTable();
			}
		});
		btnSalvar.setBounds(10, 139, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(109, 139, 89, 23);
		contentPane.add(btnCancelar);
	}
}
