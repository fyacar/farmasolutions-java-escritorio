package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import hilos.HiloContador;
import mantenimientos.GestionEmpleados;
import model.Empleados;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FmrAcceso extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuarioEmp;
	private JPasswordField txtClaveEmp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FmrAcceso frame = new FmrAcceso();
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
	public FmrAcceso() {
		
		
		setTitle("FarmaSolutions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 480);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Empleados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(143, 188, 195, 121);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 25, 58, 14);
		panel.add(lblUsuario);
		
		txtUsuarioEmp = new JTextField();
		txtUsuarioEmp.setBounds(78, 22, 107, 20);
		panel.add(txtUsuarioEmp);
		txtUsuarioEmp.setColumns(10);
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(10, 57, 46, 14);
		panel.add(lblClave);
		
		txtClaveEmp = new JPasswordField();
		txtClaveEmp.setBounds(78, 53, 107, 20);
		panel.add(txtClaveEmp);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validadacceso();
				
			}
		});
		btnIngresar.setBounds(90, 84, 95, 28);
		panel.add(btnIngresar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ver lista de Productos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(20, 337, 192, 78);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgProducto vp = new DlgProducto();
				vp.setVisible(true);
				vp.setLocationRelativeTo(null);
				detenerConteo();
				
			}
		});
		btnVer.setBounds(47, 33, 89, 23);
		panel_2.add(btnVer);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Salir del Sistema", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(275, 337, 185, 78);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(49, 32, 89, 23);
		panel_3.add(btnSalir);
		
		lblMensaje = new JLabel("La ventana se cerrara en :");
		lblMensaje.setBounds(10, 2, 154, 14);
		contentPane.add(lblMensaje);
		
		lblTiempo = new JLabel("20");
		lblTiempo.setBounds(169, 2, 46, 14);
		contentPane.add(lblTiempo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FmrAcceso.class.getResource("/img/portada.png")));
		lblNewLabel.setBounds(201, 27, 77, 106);
		contentPane.add(lblNewLabel);
		
		JLabel lblFarmasolutions = new JLabel("FarmaSolutions");
		lblFarmasolutions.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFarmasolutions.setBounds(180, 148, 136, 14);
		contentPane.add(lblFarmasolutions);
		
		iniciacontador();
	}
	
	//DECLARAMOS  VARIABLE GLOBAL
	 public static Empleados e= new Empleados();
	 private JLabel lblMensaje;
	 public static JLabel lblTiempo;
	 
	 //METODO QUE VALIDA ACCESO
	void validadacceso(){
		String  usuarioempleado= leerUsuario();
		String claveempleado= leerClave();
		
		e= new GestionEmpleados().validarAcceso(usuarioempleado, claveempleado);
		
		if(e==null){
			JOptionPane.showMessageDialog(this, "El Usuario o Contraseña del Empleado es incorrecta,Intente de nuevo");
		}
		else {
	JOptionPane.showMessageDialog(this,"Benvenido al Sistema FARMASOLUTIONS \n"+
			e.getNombre()+""+e.getApellido());
		
		//ABRIMOS EL FRAME PRINCIPAL
		FmrPrincipal prin= new FmrPrincipal();
		prin.setVisible(true);
		dispose();
		limpiar();
		}
	}
	
	private String leerUsuario(){
		return txtUsuarioEmp.getText();
	}
	
	private String leerClave(){
		return txtClaveEmp.getText();
	}
	void limpiar(){
		txtUsuarioEmp.setText("");
		txtClaveEmp.setText("");
	}
	HiloContador hc= new HiloContador(this);
	//contador de 10 a 0 > mostrar en el label
	void iniciacontador(){
	//Instanciamos la clase hilo
		
		//iniciar o arrancar el hilo
		hc.start();
		
	    //Tenmos que tener el extends Trhead en clase Hilo Contador
	}
	
	void detenerConteo(){
		hc.stop();
	}
	
}
