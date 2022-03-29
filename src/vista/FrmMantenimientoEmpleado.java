package vista;

import java.awt.BorderLayout;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.EventQueue;
import java.awt.JobAttributes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;

import mantenimientos.GestionEmpleados;
import model.Empleados;
import model.Tipos;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FrmMantenimientoEmpleado extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JDateChooser dtcFchNaci;
	private JComboBox cboTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMantenimientoEmpleado frame = new FrmMantenimientoEmpleado();
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
	public FrmMantenimientoEmpleado() {
		setTitle("Mantenimiento de Empleado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrarDatos();
			}
			
		});
		btnRegistrar.setBounds(339, 294, 98, 23);
		contentPane.add(btnRegistrar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(25, 61, 201, 209);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setText("Escriba Codigo");
		txtCodigo.setBounds(70, 11, 101, 20);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnActualizar.setBounds(57, 52, 101, 23);
		panel.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(57, 92, 101, 23);
		panel.add(btnEliminar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			buscar();
			}
			
		});
		btnBuscar.setBounds(57, 138, 101, 23);
		panel.add(btnBuscar);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(20, 14, 46, 14);
		panel.add(lblCodigo);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(170, 294, 98, 23);
		contentPane.add(btnLimpiar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(246, 58, 318, 212);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(144, 11, 147, 20);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Nombre:  ");
		lblDescripcin.setBounds(10, 14, 77, 14);
		panel_1.add(lblDescripcin);
		
		JLabel lblFechaRegistro = new JLabel("Apellido: ");
		lblFechaRegistro.setBounds(10, 39, 90, 14);
		panel_1.add(lblFechaRegistro);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(144, 42, 147, 20);
		panel_1.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblFechaVencimiento = new JLabel("Usuario:");
		lblFechaVencimiento.setBounds(10, 76, 60, 14);
		panel_1.add(lblFechaVencimiento);
		
		JLabel lblNewLabel = new JLabel("Clave:");
		lblNewLabel.setBounds(10, 107, 46, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblStock = new JLabel("Fecha Nacimiento:");
		lblStock.setBounds(10, 132, 109, 14);
		panel_1.add(lblStock);
		
		JLabel lblTipo = new JLabel("Tipo: ");
		lblTipo.setBounds(10, 173, 46, 14);
		panel_1.add(lblTipo);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(144, 73, 147, 20);
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(144, 104, 147, 20);
		panel_1.add(txtClave);
		
		dtcFchNaci = new JDateChooser();
		dtcFchNaci.setBounds(169, 135, 122, 20);
		panel_1.add(dtcFchNaci);
		
		cboTipo = new JComboBox();
		cboTipo.setBounds(144, 173, 147, 23);
		panel_1.add(cboTipo);
		
		JLabel lblMantenimientoDeProducto = new JLabel("MANTENIMIENTO DE EMPLEADO");
		lblMantenimientoDeProducto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMantenimientoDeProducto.setBounds(170, 21, 284, 14);
		contentPane.add(lblMantenimientoDeProducto);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		
		llenaCombo();

	}
	
	void llenaCombo(){
		ArrayList<Tipos> lstTipos = new GestionEmpleados().listadoTipos();
		
		//pasat el listado al combo
		if(lstTipos == null){
			JOptionPane.showMessageDialog(this, "Tabla tipos, vacía");
		}else{
			cboTipo.addItem("Seleccione");
			for (Tipos t : lstTipos) {
				cboTipo.addItem(t.getIdTipo() + "-" + t.getDescripcion());
			}
		}
	}
	
	private void registrarDatos() {
		
		int id_tipo;	
		String nombre, apellido, usuario, clave, fnacim;	
		id_tipo = leerTipo();
		nombre = leerNombre() ;
		apellido = leerApellido();
		usuario = leerUsuario();
		clave = leerClave();
		fnacim = leerFnacim();
		
		Empleados e = new Empleados();
		e.setNombre(nombre);
		e.setApellido(apellido);
		e.setUsuario(usuario);
		e.setClave(clave);
		e.setFnacim(fnacim);
		e.setId_tipo(id_tipo);
		
		int rs = new GestionEmpleados().registrar(e);
		
	
		if(rs==0){
			JOptionPane.showMessageDialog(this,"Error al registrar");
		}
		else{
			JOptionPane.showMessageDialog(this, "Usuario Registrado");}
		
	}

	
	private void eliminar() {
		
		int codigo = leerCodigo();
		int rs = new GestionEmpleados().eliminar(codigo);
		
		if(rs==0){
			JOptionPane.showMessageDialog(this, "El código no existe");			
		} else {
			JOptionPane.showMessageDialog(this, "Eliminado correctamente");
		}
					
	}
	
	private void buscar() {
		 int codigo = leerCodigo();
		
		Empleados u = new GestionEmpleados().buscar(codigo);
		if(u==null) {
			
		JOptionPane.showMessageDialog(this, "Codigo de Usuario no existe");	
		
		}else {
			txtNombre.setText(u.getNombre());
			txtApellido.setText(u.getApellido());
			txtUsuario.setText(u.getUsuario());
			txtClave.setText(u.getClave());
			cboTipo.setSelectedIndex(u.getId_tipo());
			try {
				dtcFchNaci.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(u.getFnacim()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en la fecha: "+ e.getMessage());
			}
			
		}
		
	}

	
	
	//LECTURAS:
	
	private int leerCodigo() {
		if(txtCodigo.getText().length() == 0){
			JOptionPane.showMessageDialog(this, "Ingrese un código");
			return 0;
		}else{
		return  Integer.parseInt(txtCodigo.getText());
		}
	}

	private String leerFnacim() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.format((dtcFchNaci.getDate()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error en fecha");
			return null;
		} 			
	}

	private String leerClave() {
		if(String.valueOf(txtClave.getPassword()).length() == 6){
			return  String.valueOf(txtClave.getPassword());
		}
		else {
		JOptionPane.showMessageDialog(this, "La clave debe estar vacía y debe tener 6 caracteres");
		return null;
		}
						
	}

	private String leerUsuario() {
		if(txtUsuario.getText().length()==6){
			return txtUsuario.getText();
		}
		else {
		JOptionPane.showMessageDialog(this, "El usuario no debe estar vacío y debe tener"
				+ " 6 caracteres");
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

	private int leerTipo() {
		if(cboTipo.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(this,"El tipo no puede estar vacío. Seleccione un tipo");
		return 0;
		}
		else{
			return cboTipo.getSelectedIndex();
		}
	}	
	
	void validaciones(){
		
	}
	
}
