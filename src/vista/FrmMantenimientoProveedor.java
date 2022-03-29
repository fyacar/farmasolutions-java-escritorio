package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimientos.GestionProveedores;
import model.Proveedores;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.SystemColor;
import java.awt.Font;
import com.itextpdf.*;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Date;

public class FrmMantenimientoProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JTextField txtDescripcion;
	private JTable tblProveedores;
	DefaultTableModel modelo = new DefaultTableModel();
	private JScrollPane scrollPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMantenimientoProveedor frame = new FrmMantenimientoProveedor();
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
	public FrmMantenimientoProveedor() {
		setForeground(new Color(0, 255, 255));
		setBackground(Color.BLUE);
		setTitle("Mantenimiento Proveedor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 510);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatenimientoProveedor = new JLabel("Mantenimiento Proveedor:");
		lblMatenimientoProveedor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMatenimientoProveedor.setBounds(30, 22, 363, 20);
		contentPane.add(lblMatenimientoProveedor);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(30, 72, 46, 14);
		contentPane.add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(224, 72, 56, 14);
		contentPane.add(lblNombre);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(224, 105, 77, 14);
		contentPane.add(lblTelfono);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(224, 143, 77, 14);
		contentPane.add(lblDireccin);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(224, 175, 56, 14);
		contentPane.add(lblCorreo);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(224, 211, 77, 14);
		contentPane.add(lblDescripcin);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(79, 72, 135, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(315, 69, 135, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(315, 102, 135, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(315, 140, 135, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(315, 172, 135, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(315, 208, 135, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setEnabled(false);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarDatos();
				listado();
				txtCodigo.setText("");
				//generarPDF();
			}
		});
		btnRegistrar.setBounds(532, 147, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizar();
				listado();
			}
		});
		btnActualizar.setBounds(89, 105, 113, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			eliminar();
			listado();
			}
		});
		btnEliminar.setBounds(89, 139, 113, 23);
		contentPane.add(btnEliminar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(89, 173, 113, 23);
		contentPane.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(89, 205, 113, 23);
		contentPane.add(btnLimpiar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 717, 195);
		contentPane.add(scrollPane);
		
		tblProveedores = new JTable();
		tblProveedores.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Telefono");
		modelo.addColumn("Direccion");
		modelo.addColumn("Correo");
		modelo.addColumn("Descripcion");
		scrollPane.setViewportView(tblProveedores);
		
		JButton btnGenerarCdigo = new JButton("Generar C\u00F3digo");
		btnGenerarCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRegistrar.setEnabled(true);
				PonerCodigo();
				txtNombre.setText("");
				txtTelefono.setText("");
				txtDireccion.setText("");
				txtCorreo.setText("");
				txtDescripcion.setText("");
			}
		});
		btnGenerarCdigo.setBounds(508, 95, 135, 23);
		contentPane.add(btnGenerarCdigo);
		
		JButton btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generarPDF();
			}
		});
		btnReporte.setBounds(532, 191, 89, 23);
		contentPane.add(btnReporte);
		tblProveedores.getColumn("Código").setMaxWidth(50);
		tblProveedores.getColumn("Telefono").setMaxWidth(70);
		tblProveedores.getColumn("Nombre").setMaxWidth(70);
		tblProveedores.getColumn("Direccion").setMaxWidth(120);
		tblProveedores.getColumn("Correo").setMaxWidth(150);
		tblProveedores.getColumn("Direccion").setMinWidth(120);
		tblProveedores.getColumn("Correo").setMinWidth(150);



		listado();
	}
	String fecha= new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	
	private void generarPDF(){
		if(tblProveedores.getRowCount()== 0){
			JOptionPane.showMessageDialog(this, "Lista Vacia, no se puede generar PDF.");
		}
		else{		
		String nombrearchivo ="PDF/ListaProveedores.pdf";
		try {
			
			Document doc = new Document(); 
			FileOutputStream fos = new FileOutputStream(nombrearchivo);
			PdfWriter pdfw = PdfWriter.getInstance(doc, fos);				
			doc.open();
			// logotipo de la empresa / poner imagen
			Image img = Image.getInstance("src/img/FarmaSolutionBanner.png");
			img.scaleToFit(220,120); // ajusta el tamaño de la imagen.
			img.setAlignment(Chunk.ALIGN_LEFT); //alinea el objeto
			doc.add(img);
			Paragraph salto = new Paragraph();
			salto = new Paragraph(" ");	
			doc.add(salto);
			Paragraph fe = new Paragraph("FECHA: " + fecha, 
					FontFactory.getFont("arial",11,Font.BOLD));
			fe.setAlignment(Chunk.ALIGN_RIGHT);
			doc.add(fe);
			
			doc.add(salto);
			doc.add(salto);	
			
			
			Paragraph pa = new Paragraph("REPORTE DE PROVEEDORES: " ,
					FontFactory.getFont("arial",15,Font.BOLD));
			pa.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(pa);
			
			doc.add(salto);
			doc.add(salto);

			//tabla:
			
			PdfPTable tabla = new PdfPTable(6);						
			tabla.addCell("CÓDIGO");
			tabla.addCell("NOMBRE");
			tabla.addCell("TELEFONO");	
			tabla.addCell("DIRECCION");
			tabla.addCell("CORREO");
			tabla.addCell("DESCRIPCION");			

			for (int i=0; i < tblProveedores.getRowCount(); i++) {					
				tabla.addCell(tblProveedores.getValueAt(i, 0).toString());
				tabla.addCell(tblProveedores.getValueAt(i, 1).toString());
				tabla.addCell(tblProveedores.getValueAt(i, 2).toString());
				tabla.addCell(tblProveedores.getValueAt(i, 3).toString());
				tabla.addCell(tblProveedores.getValueAt(i, 4).toString());
				tabla.addCell(tblProveedores.getValueAt(i, 5).toString());							
			}	
			
			doc.add(tabla);	
			
		doc.close();					
		Desktop.getDesktop().open(new File(nombrearchivo));				
		
	} catch (Exception e) {
		System.out.println("Error al crear PDF: " + e.getMessage());			
	}
	//fin PDF
		}
	} 
	

private void actualizar(){
	int codigo;	
	String nombre, telefono, direccion, correo, descripcion;	
	codigo = leerCodigo();
	nombre = leerNombre() ;
	telefono = leerTelefono();
	direccion = leerDireccion();
	correo = leerCorreo();
	descripcion = leerDescripcion();
	Proveedores p= new Proveedores();
	p.setNombre(nombre);
	p.setTelefono(telefono);
	p.setDireccion(direccion);
	p.setCorreo(correo);
	p.setDescripcion(descripcion);
	p.setCodigo(codigo);
	int rs= new GestionProveedores().actualizar(p);
	if (rs==0) {
		JOptionPane.showMessageDialog(this, "Usuario no actualizado");
	}else {
		JOptionPane.showMessageDialog(this, "Usuario actualizado");
	}
	
}
private void registrarDatos() {
		
		int codigo;	
		String nombre, telefono, direccion, correo, descripcion;	
		codigo = leerCodigo();
		nombre = leerNombre() ;
		telefono = leerTelefono();
		direccion = leerDireccion();
		correo = leerCorreo();
		descripcion = leerDescripcion();
		
		Proveedores p = new Proveedores();
		p.setCodigo(codigo);
		p.setNombre(nombre);
		p.setTelefono(telefono);
		p.setDireccion(direccion);
		p.setCorreo(correo);
		p.setDescripcion(descripcion);

		int rs = 0;

		
		if(txtDireccion.getText().length() == 0 || txtTelefono.getText().length() == 0 ||txtNombre.getText().length()==0 ||txtCorreo.getText().length() == 0|| txtDescripcion.getText().length() == 0){ rs=0;
		}else {
			rs= new GestionProveedores().registrar(p);
		}
	 
		if(rs==0){
			JOptionPane.showMessageDialog(this,"Error al registrar");
		}
		else{
			JOptionPane.showMessageDialog(this, "Usuario Registrado");}
		
	}

private String obtenerCodAlumno() {
	return new GestionProveedores().GenerarCodigo();
}
void PonerCodigo() {
	String cod;
	cod= obtenerCodAlumno();
	txtCodigo.setText(cod);

}

 void listado() {
		ArrayList<Proveedores> lstProveedores= new GestionProveedores().listado();
		if(lstProveedores == null || lstProveedores.size() ==0) {
			JOptionPane.showMessageDialog(this, "No hay proveedoress");
		} else {
			modelo.setRowCount(0);
			for(Proveedores p: lstProveedores)  {
				Object aDatos[]= {  p.getCodigo(),p.getNombre(), p.getTelefono(), p.getDireccion(), p.getCorreo(), p.getDescripcion()};
				modelo.addRow(aDatos);
			}
		}
	}
void limpiar() {
	txtCodigo.setText("");
	txtNombre.setText("");
	txtTelefono.setText("");
	txtDireccion.setText("");
	txtCorreo.setText("");
	txtDescripcion.setText("");
}
private void eliminar() {
		
		int codigo = leerCodigo();
		int rs = new GestionProveedores().eliminar(codigo);
		
		if(rs==0){
			JOptionPane.showMessageDialog(this, "El código no existe");			
		} else {
			JOptionPane.showMessageDialog(this, "Eliminado correctamente");
		}
					
	}
private void buscar() {
	 int codigo = leerCodigo();
	
	Proveedores p = new GestionProveedores().buscar(codigo);
	if(p==null) {
		
	JOptionPane.showMessageDialog(this, "Codigo de Usuario no existe");	
	
	}else {
		txtNombre.setText(p.getNombre());
		txtTelefono.setText(p.getTelefono());
		txtDireccion.setText(p.getDireccion());
		txtCorreo.setText(p.getCorreo());
	    txtDescripcion.setText(p.getDescripcion());
	}
	
}
private int leerCodigo() {
	if(txtCodigo.getText().length() == 0){
		JOptionPane.showMessageDialog(this, "Ingrese un código");
		return 0;
	}else{
	return  Integer.parseInt(txtCodigo.getText());
	}
}
private String leerNombre() {
		if(txtTelefono.getText().length() == 0){
			JOptionPane.showMessageDialog(this, "Ingrese un nombre");
			return null;
		}else{
		return txtNombre.getText();
		}
	}
private String leerTelefono() {
	if(txtTelefono.getText().length() == 0){
		JOptionPane.showMessageDialog(this, "Ingrese un telefono");
		return null;
	}else{
	return  txtTelefono.getText();
	}
}

private String leerDireccion() {
	if(txtDireccion.getText().length() == 0){
		JOptionPane.showMessageDialog(this, "Ingrese una dirección");
		return null;
	}else{
	return  txtDireccion.getText();
	}
}
private String leerCorreo() {
	if(txtCorreo.getText().length() == 0){
		JOptionPane.showMessageDialog(this, "Ingrese un correo");
		return null;
	}else{
	return  txtCorreo.getText();
	}
}
private String leerDescripcion() {
	if(txtDescripcion.getText().length() == 0){
		JOptionPane.showMessageDialog(this, "Ingrese una descripción");
		return null;
	}else{
	return  txtDescripcion.getText();
	}
}
}
