package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimientos.GestionClientes;
import mantenimientos.GestionProductos;
import model.Clientes;
import model.Productos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DlgProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBuscar;
	private JTable tblSalida;
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgProducto dialog = new DlgProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgProducto() {
		setTitle("Buscar Producto");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblBuscar = new JLabel("Buscar:");
			lblBuscar.setBounds(41, 23, 46, 14);
			contentPanel.add(lblBuscar);
		}
		{
			txtBuscar = new JTextField();
			txtBuscar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					listarxbusqueda();
				}
			});
			txtBuscar.setBounds(97, 20, 152, 20);
			contentPanel.add(txtBuscar);
			txtBuscar.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 57, 414, 160);
			contentPanel.add(scrollPane);
			{
				tblSalida = new JTable();
				tblSalida.setModel(modelo);
				modelo.addColumn("Código");
				modelo.addColumn("Descripción");
				modelo.addColumn("Precio");
				modelo.addColumn("Stock");
				scrollPane.setViewportView(tblSalida);
			}
		}
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
					public void actionPerformed(ActionEvent e) {
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
		
		if(tblSalida.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(this, "Debe seleccionar un producto");
		}else{
		//1.- obtener la fila seleccionada en la tabla
		int fila = tblSalida.getSelectedRow();
		//Enviar losdatos de la tabla a los txt del formulario de boleta según la fila seleccionada
		FmrBoleta.txtCodProducto.setText(tblSalida.getValueAt(fila, 0).toString());
		FmrBoleta.txtDescripcionProducto.setText(tblSalida.getValueAt(fila, 1).toString());
		FmrBoleta.txtPrecioProducto.setText(tblSalida.getValueAt(fila, 2).toString());
		FmrBoleta.txtStockProducto.setText(tblSalida.getValueAt(fila, 3).toString());
	
		dispose();
		}
	}
	void listado(){
				
		
		ArrayList<Productos> lstProductos = new GestionProductos().listado();		
		// pasar el listado al txt.
		if(lstProductos.size()==0||lstProductos ==null){
			 JOptionPane.showMessageDialog(this, "Listado vacío");		 
		 } else {
			 modelo.setRowCount(0); //para limpiar la tabla.
			 for (Productos p: lstProductos) 
			
			 {
				Object aDatos[] = {p.getIdprod(), p.getDescripcion(), p.getPrecio(),
						p.getStock()};
				modelo.addRow(aDatos);
			}
		 }	
	}
	
	private void listarxbusqueda() {
		String nombreProducto = txtBuscar.getText();
			ArrayList<Productos> lstProductos = new GestionProductos().buscarXNombreCompleto(nombreProducto);		
				// pasar el listado al txt.
				if(lstProductos.size()==0||lstProductos ==null){
					modelo.setRowCount(0);	 
				 } else {
					 modelo.setRowCount(0); 
					 for (Productos p: lstProductos) 
					
					 {
						Object aDatos[] = {p.getIdprod(), p.getDescripcion(), p.getPrecio(), p.getStock()};
						modelo.addRow(aDatos);
					}
				 }	
		
	}
	
}
