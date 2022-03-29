package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;

public class FmrPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FmrPrincipal frame = new FmrPrincipal();
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
	public FmrPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FmrPrincipal.class.getResource("/img/icono.png")));
		setTitle("FarmaSolutions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnSistema.add(mntmSalir);
		
		JMenu mnMantenimientos = new JMenu("Mantenimientos");
		menuBar.add(mnMantenimientos);
		
		JMenuItem mntmVendedor = new JMenuItem("Empleado");
		mntmVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			FrmMantenimientoEmpleado fe= new FrmMantenimientoEmpleado();
			fe.setVisible(true);
			fe.setLocationRelativeTo(null);
			fe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnMantenimientos.add(mntmVendedor);
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMantenimientoCliente mc = new FrmMantenimientoCliente();
				mc.setVisible(true);
				mc.setLocationRelativeTo(null);
				mc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnMantenimientos.add(mntmCliente);
		
		JMenuItem mntmProveedor = new JMenuItem("Proveedor");
		mntmProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMantenimientoProveedor mp = new FrmMantenimientoProveedor();
				mp.setVisible(true);
				mp.setLocationRelativeTo(null);
				mp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnMantenimientos.add(mntmProveedor);
		
		JMenu mnTransaccional = new JMenu("Transaccional");
		menuBar.add(mnTransaccional);
		
		JMenuItem mntmVentas = new JMenuItem("Ventas");
		mntmVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FmrBoleta vb = new FmrBoleta();
				vb.setVisible(true);
				vb.setLocationRelativeTo(null);
				vb.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnTransaccional.add(mntmVentas);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenuItem mntmProveedores = new JMenuItem("Proveedores");
		mntmProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteProveedores rp = new ReporteProveedores();
				rp.setVisible(true);
				rp.setLocationRelativeTo(null);
				rp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnReportes.add(mntmProveedores);
		
		JMenuItem mntmEmpleados = new JMenuItem("Empleados");
		mntmEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ReporteEmpleados re= new ReporteEmpleados();
			re.setVisible(true);
			re.setLocationRelativeTo(null);
			re.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnReportes.add(mntmEmpleados);
		
		JMenuItem mntmVentas_1 = new JMenuItem("Ventas");
		mntmVentas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteVentas rv = new ReporteVentas();
				rv.setVisible(true);
				rv.setLocationRelativeTo(null);
				rv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnReportes.add(mntmVentas_1);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setIcon(new ImageIcon(FmrPrincipal.class.getResource("/img/portadafarma.png")));
		lblNewLabel.setBounds(54, 55, 370, 174);
		contentPane.add(lblNewLabel);
		
		
		switch (FmrAcceso.e.getId_tipo()){
		case 2:
			mnMantenimientos.setVisible(false);			
			// mnReportes.setVisible(false);
			break;
		default:
			break;
		
		}
	}
}
