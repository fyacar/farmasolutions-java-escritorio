package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimientos.GestionClientes;
import mantenimientos.GestionProveedores;
import model.Clientes;
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

public class FrmMantenimientoCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JPasswordField txtClave;
	private JTextField txtPuntos;
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
					FrmMantenimientoCliente frame = new FrmMantenimientoCliente();
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
	public FrmMantenimientoCliente() {
		setForeground(new Color(0, 255, 255));
		setBackground(Color.BLUE);
		setTitle("Mantenimiento Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 510);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatenimientoCliente = new JLabel("Mantenimiento Cliente:");
		lblMatenimientoCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMatenimientoCliente.setBounds(30, 22, 363, 20);
		contentPane.add(lblMatenimientoCliente);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(30, 72, 46, 14);
		contentPane.add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(224, 72, 56, 14);
		contentPane.add(lblNombre);
		
		JLabel lblTelfono = new JLabel("Apellido:");
		lblTelfono.setBounds(224, 105, 77, 14);
		contentPane.add(lblTelfono);
		
		JLabel lblDireccin = new JLabel("Dni:");
		lblDireccin.setBounds(224, 143, 77, 14);
		contentPane.add(lblDireccin);
		
		JLabel lblCorreo = new JLabel("Clave");
		lblCorreo.setBounds(224, 175, 56, 14);
		contentPane.add(lblCorreo);
		
		JLabel lblDescripcin = new JLabel("Puntos");
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
		
		txtApellido = new JTextField();
		txtApellido.setBounds(315, 102, 135, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(315, 140, 135, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(315, 171, 135, 22);
		contentPane.add(txtClave);
		
		txtPuntos = new JTextField();
		txtPuntos.setBounds(315, 208, 135, 20);
		contentPane.add(txtPuntos);
		txtPuntos.setColumns(10);
		
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
		modelo.addColumn("Apellido");
		modelo.addColumn("Dni");
		modelo.addColumn("Clave");
		modelo.addColumn("Puntos");
		scrollPane.setViewportView(tblProveedores);
		
		JButton btnGenerarCdigo = new JButton("Generar C\u00F3digo");
		btnGenerarCdigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRegistrar.setEnabled(true);
				PonerCodigo();
				txtNombre.setText("");
				txtApellido.setText("");
				txtDni.setText("");
				txtClave.setText("");
				txtPuntos.setText("");
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
		



		listado();
	}
	String fecha= new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	
	private void generarPDF(){
		if(tblProveedores.getRowCount()== 0){
			JOptionPane.showMessageDialog(this, "Lista Vacia, no se puede generar PDF.");
		}
		else{		
		String nombrearchivo ="PDF/ListaClientes.pdf";
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
			
			
			Paragraph pa = new Paragraph("REPORTE DE CLIENTES: " ,
					FontFactory.getFont("arial",15,Font.BOLD));
			pa.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(pa);
			
			doc.add(salto);
			doc.add(salto);

			//tabla:
			
			PdfPTable tabla = new PdfPTable(6);						
			tabla.addCell("CÓDIGO");
			tabla.addCell("NOMBRE");
			tabla.addCell("APELLIDO");	
			tabla.addCell("DNI");
			tabla.addCell("CLAVE");
			tabla.addCell("PUNTOS");			

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
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7

	private int leerPuntos() {
		if(txtPuntos.getText().length() >0){
			return Integer.parseInt(txtPuntos.getText());
		}
		else {
		JOptionPane.showMessageDialog(this, "Los puntos no deben estar vacío y debe tener"
				+ " 1 caracteres");
		return 0;
		}
	}

	private String leerDni() {
		if(txtDni.getText().length()==8){
			return txtDni.getText();
		}
		else {
		JOptionPane.showMessageDialog(this, "El dni no debe estar vacío y debe tener"
				+ " 8 caracteres");
		return null;
		}
		
	}
	
	private String leerClave() {
		if(String.valueOf(txtClave.getPassword()).length() == 5){
			return  String.valueOf(txtClave.getPassword());
		}
		else {
		JOptionPane.showMessageDialog(this, "La clave debe tener 5 caracteres");
		return null;
		}
	}

	private String leerApellido() {
		if(txtApellido.getText().length()<=25 && txtApellido.getText().length()>0){
			return txtApellido.getText();
			}
			else{
				JOptionPane.showMessageDialog(this, "El apellido no puede estar vacío y no debe superar"
						+ " los 25 caracteres");
				return null;
			}
	}

	private String leerNombre() {
		if(txtNombre.getText().length()<=25 && txtNombre.getText().length()>0){
			return txtNombre.getText();
			}
			else{
				JOptionPane.showMessageDialog(this, "El Nombre no puede estar vacío y no debe superar"
						+ " los 25 caracteres");
				return null;
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
	private String obtenerCod() {
		return new GestionClientes().GenerarCodigo();
	}
	void PonerCodigo() {
		String cod;
		cod= obtenerCod();
		txtCodigo.setText(cod);

	}
	//////////////////////////////////////////////////////////////////////////////////////////////
private void actualizar(){
	int codigo, puntos;	
	String nombre, clave, apellido, dni;	
	codigo = leerCodigo();
	nombre = leerNombre() ;
	apellido= leerApellido();
	clave = leerClave();
	dni = leerDni();
	puntos = leerPuntos();
	Clientes c= new Clientes();
	c.setNombre(nombre);
	c.setApellido(apellido);
	c.setDni(dni);
	c.setClave(clave);
	c.setPuntos(puntos);
	c.setCodCli(codigo);
	int rs= new GestionClientes().actualizar(c);
	if (rs==0) {
		JOptionPane.showMessageDialog(this, "Usuario no actualizado");
	}else {
		JOptionPane.showMessageDialog(this, "Usuario actualizado");
	}
	
}
private void registrarDatos() {
		
	int codigo, puntos;	
	String nombre, clave, apellido, dni;	
	codigo = leerCodigo();
	nombre = leerNombre() ;
	apellido= leerApellido();
	clave = leerClave();
	dni = leerDni();
	puntos = leerPuntos();
	Clientes c= new Clientes();
	c.setNombre(nombre);
	c.setApellido(apellido);
	c.setDni(dni);
	c.setClave(clave);
	c.setPuntos(puntos);
	c.setCodCli(codigo);

		int rs = 0;

		
		if(txtDni.getText().length() == 0 || txtApellido.getText().length() == 0 ||txtNombre.getText().length()==0 ||txtClave.getText().length() == 0|| txtPuntos.getText().length() == 0){ rs=0;
		}else {
			rs= new GestionClientes().registrar(c);
		}
	 
		if(rs==0){
			JOptionPane.showMessageDialog(this,"Error al registrar");
		}
		else{
			JOptionPane.showMessageDialog(this, "Usuario Registrado");}
		
	}


 void listado() {
		ArrayList<Clientes> lstProveedores= new GestionClientes().listado();
		if(lstProveedores == null || lstProveedores.size() ==0) {
			JOptionPane.showMessageDialog(this, "No hay clientes");
		} else {
			modelo.setRowCount(0);
			for(Clientes c: lstProveedores)  {
				Object aDatos[]= {  c.getCodCli(),c.getNombre(), c.getApellido() , c.getDni(), String.valueOf(c.getClave()), c.getPuntos()};
				modelo.addRow(aDatos);
			}
		}
	}
void limpiar() {
	txtCodigo.setText("");
	txtNombre.setText("");
	txtApellido.setText("");
	txtDni.setText("");
	txtClave.setText("");
	txtPuntos.setText("");
}
private void eliminar() {
		
		int codigo = leerCodigo();
		int rs = new GestionClientes().eliminar(codigo);
		
		if(rs==0){
			JOptionPane.showMessageDialog(this, "El código no existe");			
		} else {
			JOptionPane.showMessageDialog(this, "Eliminado correctamente");
		}
					
	}
private void buscar() {
	 int codigo = leerCodigo();
	
	Clientes c = new GestionClientes().buscar(codigo);
	if(c==null) {
		
	JOptionPane.showMessageDialog(this, "Codigo de Usuario no existe");	
	
	}else {
		txtNombre.setText(c.getNombre());
		txtApellido.setText(c.getApellido());
		txtDni.setText(c.getDni());
		txtClave.setText(c.getClave());
	    txtPuntos.setText(String.valueOf(c.getPuntos()));
	}
	
}

}
