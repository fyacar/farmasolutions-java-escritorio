package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimientos.GestionClientes;
import model.Clientes;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DlgCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTable tblSalida;
	DefaultTableModel modelo = new DefaultTableModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCliente dialog = new DlgCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCliente() {
		setTitle("Buscar Cliente\r\n");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblBuscar = new JLabel("Filtrar por nombre:");
			lblBuscar.setBounds(23, 24, 113, 14);
			contentPanel.add(lblBuscar);
		}
		
		txtNombre = new JTextField();		
		txtNombre.addKeyListener(new KeyAdapter() {			
			public void keyReleased(KeyEvent e) {				
			
				listarxbusqueda();}				
			
		});
		txtNombre.setBounds(133, 21, 161, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 49, 386, 157);
		contentPanel.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Nombre Completo");
		scrollPane.setViewportView(tblSalida);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						enviarDatos();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		listado();
	}
	
	void enviarDatos() {		
		//1.- obtener la fila seleccionada en la tabla
		if(tblSalida.getSelectedRow()== -1){
			JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente");
		}else{
		int fila = tblSalida.getSelectedRow();
		//Enviar losdatos de la tabla a los txt del formulario de boleta según la fila seleccionada
		FmrBoleta.txtCodCliente.setText(tblSalida.getValueAt(fila, 0).toString());
		FmrBoleta.txtNombreCompletoCliente.setText(tblSalida.getValueAt(fila, 1)+ "");
		//cerrar el diálogo
		dispose();}		
	}
	void listado(){
		
		//obtener un listado de los usuarios, según el tipo, usando la gestión el num 2 es para clientes
		ArrayList<Clientes> lstClientes = new GestionClientes().listado();		
		// pasar el listado al txt.
		if(lstClientes.size()==0||lstClientes ==null){
			 JOptionPane.showMessageDialog(this, "Listado vacío");		 
		 } else {
			 modelo.setRowCount(0); //para limpiar la tabla.
			 for (Clientes cli: lstClientes) 
			
			 {
				Object aDatos[] = {cli.getCodCli(), cli.getNombre() + " " + cli.getApellido()};
				modelo.addRow(aDatos);
			}
		 }	
	}
	
	private void listarxbusqueda() {
		String nombreCompleto = txtNombre.getText();
			ArrayList<Clientes> lstClientes = new GestionClientes().buscarXNombreCompleto(nombreCompleto);		
				// pasar el listado al txt.
				if(lstClientes.size()==0||lstClientes ==null){
					modelo.setRowCount(0);	 
				 } else {
					 modelo.setRowCount(0); 
					 for (Clientes cli: lstClientes) 
					
					 {
						Object aDatos[] = {cli.getCodCli(), cli.getNombre() + " " + cli.getApellido()};
						modelo.addRow(aDatos);
					}
				 }	
		
	}
	
	
	
	
}
