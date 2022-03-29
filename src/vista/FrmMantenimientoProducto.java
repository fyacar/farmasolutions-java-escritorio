package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;


import mantenimientos.GestionProductos;
import mantenimientos.GestionProveedores;
import model.Categorias;
import model.Productos;
import model.Proveedores;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class FrmMantenimientoProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtStock;
	private JComboBox cboTipo;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JDateChooser dtcFchVencimiento;
	private JDateChooser dtcfchRegistro;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMantenimientoProducto frame = new FrmMantenimientoProducto();
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
	public FrmMantenimientoProducto() {
		setTitle("Mantenimiento Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(25, 65, 46, 14);
		contentPane.add(lblCdigo);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n: ");
		lblDescripcin.setBounds(25, 90, 70, 14);
		contentPane.add(lblDescripcin);
		
		JLabel lblFechaRegistro = new JLabel("Fecha Registro:");
		lblFechaRegistro.setBounds(25, 115, 87, 14);
		contentPane.add(lblFechaRegistro);
		
		JLabel lblFechaVencimiento = new JLabel("Fecha Vencimiento: ");
		lblFechaVencimiento.setBounds(25, 140, 106, 14);
		contentPane.add(lblFechaVencimiento);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(25, 165, 70, 14);
		contentPane.add(lblStock);
		
		JLabel lblIdtipo = new JLabel("Tipo: ");
		lblIdtipo.setBounds(25, 224, 46, 14);
		contentPane.add(lblIdtipo);
		
		JLabel lblCodigoProveedor = new JLabel("C\u00F3digo Proveedor: ");
		lblCodigoProveedor.setBounds(25, 249, 106, 14);
		contentPane.add(lblCodigoProveedor);
		
		JLabel lblMantenimintoDeProducto = new JLabel("Mantenimi\u00E9nto de Producto");
		lblMantenimintoDeProducto.setBounds(119, 11, 140, 14);
		contentPane.add(lblMantenimintoDeProducto);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(155, 62, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		dtcfchRegistro = new JDateChooser();
		dtcfchRegistro.setBounds(155, 115, 87, 20);
		contentPane.add(dtcfchRegistro);
		
		dtcFchVencimiento = new JDateChooser();
		dtcFchVencimiento.setBounds(154, 140, 87, 20);
		contentPane.add(dtcFchVencimiento);
		
		txtStock = new JTextField();
		txtStock.setBounds(155, 162, 86, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		cboTipo = new JComboBox();
		cboTipo.setBounds(155, 218, 94, 17);
		contentPane.add(cboTipo);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrarDatos();
			}
		});
		btnRegistrar.setBounds(308, 61, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizar();
			}
		});
		btnActualizar.setBounds(308, 90, 89, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnEliminar.setBounds(308, 115, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(308, 166, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(308, 200, 89, 23);
		contentPane.add(btnLimpiar);
		
		cboProveedores = new JComboBox();
		cboProveedores.setBounds(157, 246, 168, 20);
		contentPane.add(cboProveedores);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(155, 87, 86, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(25, 190, 46, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(155, 187, 86, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JButton btnGenerarCod = new JButton("Generar Cod.");
		btnGenerarCod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCodigo();
			}
		});
		btnGenerarCod.setBounds(308, 140, 106, 23);
		contentPane.add(btnGenerarCod);
		
		llenaComboCategorias();
		llenaComboProveedores();
		
	}
	
	private void btnCodigo(){
		txtCodigo.setText(obtenerCodigoProd());
	}
	
	
private String obtenerCodigoProd() {
		
		return new GestionProductos().generCodProd();
	}

private void registrarDatos() {
		
		int stock, tipo, codprov;	
		double precio;
		String codigo, descripcion, fchRegistro, fchVencimiento;
		precio = leerPrecio();
		codigo = leerCodigo();
		descripcion = leerDescripcion() ;
		fchRegistro = leerFechaRegistro();
		fchVencimiento = leerFechaVencimiento();
		stock = leerStock();
		tipo = leerTipo();
		codprov = leerCodigoProveerdor();
		
		Productos p = new Productos(codigo, descripcion, fchRegistro, fchVencimiento, codprov, stock, tipo, precio);
		int rs = 0;

		rs= new GestionProductos().registrar(p);
		
	 
		if(rs==0){
			JOptionPane.showMessageDialog(this,"Error al registrar");
		}
		else{
			JOptionPane.showMessageDialog(this, "Usuario Registrado");}
		
	}
	
private void actualizar() {
	
	int stock, tipo, codprov;	
	double precio;
	String codigo, descripcion, fchRegistro, fchVencimiento;
	precio = leerPrecio();
	codigo = leerCodigo();
	descripcion = leerDescripcion() ;
	fchRegistro = leerFechaRegistro();
	fchVencimiento = leerFechaVencimiento();
	stock = leerStock();
	tipo = leerTipo();
	codprov = leerCodigoProveerdor();
	
	
	Productos p = new Productos(codigo, descripcion, fchRegistro, fchVencimiento, codprov, stock, tipo, precio);
	
	int rs = new GestionProductos().actualizar(p);		
	
	// salidas
	if(rs==0){
		JOptionPane.showMessageDialog(this,"Error al actualizar");
	}
	else{
		JOptionPane.showMessageDialog(this, "Usuario Actualizado");
				}
	}


private void eliminar() {
	
	String codigo = leerCodigo();
	int rs = new GestionProductos().eliminar(codigo);
	
	if(rs==0){
		JOptionPane.showMessageDialog(this, "El código no existe");			
	} else {
		JOptionPane.showMessageDialog(this, "Eliminado correctamente");
	}
				
}

	
	private int leerCodigoProveerdor() {
		System.out.println(cboProveedores.getSelectedItem().toString().substring(0, 5));
	return	Integer.parseInt(cboProveedores.getSelectedItem().toString().substring(0, 5)); 	
}

	private int leerStock() {
	
	return Integer.parseInt(txtStock.getText());
}

	private String leerFechaVencimiento() {
		if(dtcFchVencimiento.getDate()== null){
			JOptionPane.showMessageDialog(this, "Debe elegir una fecha");
			return null;
		}
		else{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dtcFchVencimiento.getDate());
		}
}

	private String leerFechaRegistro() {
		if(dtcfchRegistro.getDate()== null){
			JOptionPane.showMessageDialog(this, "Debe elegir una fecha");
			return null;
		}
		else{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dtcfchRegistro.getDate());
		}
}

	private String leerDescripcion() {
	
	return txtDescripcion.getText();
}

	private String leerCodigo() {
	
	return txtCodigo.getText();
}

	private double leerPrecio() {
		return Double.parseDouble(txtPrecio.getText());
}
	
	private int leerTipo() {
		System.out.println(cboTipo.getSelectedItem().toString().substring(0, 1));
		return	Integer.parseInt(cboTipo.getSelectedItem().toString().substring(0, 1)); 	
}

	void llenaComboCategorias(){
		//obtener un listado de la tabla a usar en el combo. 
		ArrayList<Categorias> lstCategorias = new GestionProductos().listadoCateogoriasCombo();
		
		//pasat el listado al combo
		if(lstCategorias == null){
			JOptionPane.showMessageDialog(this, "Lista de Categorias, vacía");
		}else{
			cboTipo.addItem("Seleccione");
			for (Categorias c : lstCategorias) {
				cboTipo.addItem(c.getIdTipo() + ".- " + c.getDescripcion());
			}
		}
		}
		void llenaComboProveedores(){
			//obtener un listado de la tabla a usar en el combo. 
			ArrayList<Proveedores> lstProveedores = new GestionProductos().listadoProveedoresCombo();
			
			//pasat el listado al combo
			if(lstProveedores == null){
				JOptionPane.showMessageDialog(this, "Lista de Categorias, vacía");
			}else{
				cboProveedores.addItem("Seleccione");
				for (Proveedores p : lstProveedores) {
					cboProveedores.addItem(p.getCodigo() + ".- " + p.getNombre());
				}
			}
	}
}
