package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ReporteClientes extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteClientes frame = new ReporteClientes();
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
	public ReporteClientes() {
		setTitle("Listado de Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListadoDeClientes = new JLabel("Listado de clientes:");
		lblListadoDeClientes.setBounds(20, 11, 102, 14);
		contentPane.add(lblListadoDeClientes);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(20, 39, 46, 14);
		contentPane.add(lblTipo);
		
		JComboBox cboTipoCliente = new JComboBox();
		cboTipoCliente.setBounds(52, 36, 102, 20);
		contentPane.add(cboTipoCliente);
	}
}
