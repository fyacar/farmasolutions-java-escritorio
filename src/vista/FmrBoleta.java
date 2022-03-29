package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimientos.GestionVentas;
import model.CabeceraBoleta;
import model.DetalleBoleta;


import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class FmrBoleta extends JFrame {

	private JPanel contentPane;
	public static JTextField txtCodCliente;
	public static JTextField txtNombreCompletoCliente;
	private JTextField txtNumBoleta;
	private JTextField txtFechaActual;
	public static JTextField txtCodProducto;
	private JTextField txtCantidadCompraPro;
	public static JTextField txtDescripcionProducto;
	public static JTextField txtPrecioProducto;
	public static JTextField txtStockProducto;
	private JTextField txtTotal;
	
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FmrBoleta frame = new FmrBoleta();
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
	public FmrBoleta() {
		setTitle("Boleta de Venta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 290, 140);
		setLocationRelativeTo(null);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCliente = new JLabel("Buscar Cliente:");
		lblCliente.setBounds(74, 28, 94, 14);
		panel.add(lblCliente);
		
		txtCodCliente = new JTextField();
		txtCodCliente.setEditable(false);
		txtCodCliente.setBounds(105, 69, 164, 20);
		panel.add(txtCodCliente);
		txtCodCliente.setColumns(10);
		
		txtNombreCompletoCliente = new JTextField();
		txtNombreCompletoCliente.setEditable(false);
		txtNombreCompletoCliente.setBounds(105, 100, 164, 20);
		panel.add(txtNombreCompletoCliente);
		txtNombreCompletoCliente.setColumns(10);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			DlgCliente v = new DlgCliente();
			v.setVisible(true);
			}
		});
		button_2.setIcon(new ImageIcon(FmrBoleta.class.getResource("/img/loupe(4).png")));
		button_2.setBackground(SystemColor.menu);
		button_2.setBounds(178, 18, 39, 33);
		panel.add(button_2);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(30, 72, 65, 14);
		panel.add(lblCdigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 103, 65, 14);
		panel.add(lblNombre);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(334, 22, 245, 84);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(10, 14, 64, 14);
		panel_1.add(lblNmero);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 56, 46, 14);
		panel_1.add(lblFecha);
		
		txtNumBoleta = new JTextField();
		txtNumBoleta.setEditable(false);
		txtNumBoleta.setBounds(84, 11, 139, 20);
		panel_1.add(txtNumBoleta);
		txtNumBoleta.setColumns(10);
		
		txtFechaActual = new JTextField();
		txtFechaActual.setEditable(false);
		txtFechaActual.setBounds(84, 53, 139, 20);
		panel_1.add(txtFechaActual);
		txtFechaActual.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 162, 569, 134);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblProducto = new JLabel("Producto: ");
		lblProducto.setBounds(21, 38, 61, 14);
		panel_2.add(lblProducto);
		
		JLabel lblCantidad = new JLabel("Cantidad: ");
		lblCantidad.setBounds(296, 23, 61, 14);
		panel_2.add(lblCantidad);
		
		txtCodProducto = new JTextField();
		txtCodProducto.setEditable(false);
		txtCodProducto.setBounds(108, 32, 104, 20);
		panel_2.add(txtCodProducto);
		txtCodProducto.setColumns(10);
		
		txtCantidadCompraPro = new JTextField();
		txtCantidadCompraPro.setBounds(367, 20, 86, 20);
		panel_2.add(txtCantidadCompraPro);
		txtCantidadCompraPro.setColumns(10);
		
		txtDescripcionProducto = new JTextField();
		txtDescripcionProducto.setEditable(false);
		txtDescripcionProducto.setBounds(108, 64, 149, 20);
		panel_2.add(txtDescripcionProducto);
		txtDescripcionProducto.setColumns(10);
		
		txtPrecioProducto = new JTextField();
		txtPrecioProducto.setEditable(false);
		txtPrecioProducto.setBounds(189, 95, 68, 20);
		panel_2.add(txtPrecioProducto);
		txtPrecioProducto.setColumns(10);
		
		txtStockProducto = new JTextField();
		txtStockProducto.setEditable(false);
		txtStockProducto.setBounds(63, 95, 61, 20);
		panel_2.add(txtStockProducto);
		txtStockProducto.setColumns(10);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgProducto vp = new DlgProducto();
				vp.setVisible(true);
				
			}
		});
		button.setIcon(new ImageIcon(FmrBoleta.class.getResource("/img/loupe(4).png")));
		button.setBackground(UIManager.getColor("Button.background"));
		button.setBounds(222, 19, 39, 33);
		panel_2.add(button);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(21, 67, 72, 14);
		panel_2.add(lblDescripcin);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(144, 98, 55, 14);
		panel_2.add(lblPrecio);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(21, 98, 46, 14);
		panel_2.add(lblStock);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Agregar Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(296, 48, 203, 67);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(56, 23, 93, 33);
		panel_3.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCompra();
			}
		});
		button_1.setIcon(new ImageIcon(FmrBoleta.class.getResource("/img/shopping-cart(2).png")));
		button_1.setBackground(UIManager.getColor("Button.background"));
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevaBoleta();
			}
		});
		btnNuevo.setBounds(181, 530, 89, 23);
		contentPane.add(btnNuevo);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizarCompra();
			}
		});
		btnFinalizar.setBounds(307, 530, 89, 23);
		contentPane.add(btnFinalizar);
		
		txtFechaActual.setText(obtenerFecha());
		txtNumBoleta.setText(obtenerNumBoleta());
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Detalle de Boleta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(10, 337, 569, 182);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setBounds(391, 150, 55, 14);
		panel_4.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(456, 147, 101, 20);
		panel_4.add(txtTotal);
		txtTotal.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 547, 98);
		panel_4.add(scrollPane);
		
		tbSalida = new JTable();
		tbSalida.setModel(modelo);
		scrollPane.setViewportView(tbSalida);
		modelo.addColumn("Cod. Prod.");
		modelo.addColumn("Nomb. Pro.");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Precio unitario");
		modelo.addColumn("Importe");

		
		
	}
	
	private void finalizarCompra() {
		String numbol, fchbol;
		int codcli, codven;
		double totbol;
		//Entradas
		numbol= obtenerNumBoleta();
		fchbol = obtenerFecha();
		codcli = leerCodCliente(); //Se lee de la GUI
		codven = obtenerCodVendedor(); //Se obtiene del logeo. Del usario que ingresa
		totbol = total; //acumulador global
		CabeceraBoleta cab = new CabeceraBoleta(numbol, fchbol, codcli, codven, totbol);
		//CabeceraBoleta cab = new CabeceraBoleta(numbol, fchbol, codcli, codven, totbol);
		int rs = new GestionVentas().realizarVenta(cab, lstDetalles);
		//salida
		if(rs == 0){
			JOptionPane.showMessageDialog(this, "Error al realizar la transacción");
		} else if(tbSalida.getRowCount()==0){
			JOptionPane.showMessageDialog(this, "Debe añadir por lo menos un producto para finalizar la compra");
		}
		else{
			JOptionPane.showMessageDialog(this, "Transacción realizada");
			
			// inicio PDF
	
			String nombrearchivo = numbol + "recibo.pdf";
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
				Paragraph fe = new Paragraph("FECHA: " + obtenerFecha() , 
						FontFactory.getFont("arial",11,Font.BOLD));
				fe.setAlignment(Chunk.ALIGN_RIGHT);
				doc.add(fe);
				
				doc.add(salto);
				doc.add(salto);	
				
				
				Paragraph pa = new Paragraph("BOLETA DE VENTA " +numbol , 
						FontFactory.getFont("arial",15,Font.BOLD));
				pa.setAlignment(Chunk.ALIGN_CENTER);
				doc.add(pa);
				
				doc.add(salto);
				doc.add(salto);
				
				Paragraph cli = new Paragraph("CLIENTE: " + txtNombreCompletoCliente.getText() , 
						FontFactory.getFont("arial",11,Font.BOLD));
				cli.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(cli);
				Paragraph ven = new Paragraph("VENDEDOR: " + FmrAcceso.e.getNombre() + " " + FmrAcceso.e.getApellido() , 
						FontFactory.getFont("arial",11,Font.BOLD));
				ven.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(ven);
				
				doc.add(salto);
				doc.add(salto);
				
	
				
				//tabla:
				
				PdfPTable tabla = new PdfPTable(4);						
				tabla.addCell("CANTIDAD");
				tabla.addCell("DESCRIPCIÓN");
				tabla.addCell("P. UNITARIO");
				tabla.addCell("IMPORTE");

				for (int i=0; i < tbSalida.getRowCount(); i++) {					
					tabla.addCell(tbSalida.getValueAt(i, 2).toString());
					tabla.addCell(tbSalida.getValueAt(i, 1).toString());
					tabla.addCell(tbSalida.getValueAt(i, 3).toString());
					tabla.addCell(tbSalida.getValueAt(i, 4).toString());					
				}	
				
				doc.add(tabla);	
				
				doc.add(salto);
				
				Paragraph pa2 = new Paragraph("TOTAL: S/. " + txtTotal.getText(), 
						FontFactory.getFont("arial",13,Font.BOLD));
				pa2.setAlignment(Chunk.ALIGN_CENTER);
				doc.add(pa2);
				
			
			doc.close();					
			Desktop.getDesktop().open(new File(nombrearchivo));				
			
		} catch (Exception e) {
			System.out.println("Error al crear PDF: " + e.getMessage());			
		}
		//fin PDF

		nuevaBoleta();	
		
		}
} 				

	void nuevaBoleta(){
		txtCodCliente.setText("");
		txtNombreCompletoCliente.setText("");
		txtCodProducto.setText("");
		txtDescripcionProducto.setText("");
		txtDescripcionProducto.setText("");
		txtStockProducto.setText("");
		txtPrecioProducto.setText("");
		txtCantidadCompraPro.setText("");		
		txtTotal.setText("");		
		modelo.setRowCount(0);
		txtNumBoleta.setText(obtenerNumBoleta());
		
		
		//Falta limpiar todas las cajas.
		lstDetalles = new ArrayList<DetalleBoleta>();
		total = 0;
		
	}
	//acumulador para el importe total:
	double total = 0;
	//Aquí se guardará la información que vamos a grabar
	ArrayList<DetalleBoleta> lstDetalles = new ArrayList<DetalleBoleta>();	
	private JTable tb_Salida;
	private JTable tbSalida;
	
	private void agregarCompra() {
		//variables
		String codprod, nomprod;
		int cantidad, stock;
		double precio, importe;
		//entradas
		codprod = leerCodProd();
		nomprod = leerNomProd();
		cantidad = leerCantidad();
		precio = leerPrecio();
		stock = leerStock();
		
		if(leerCodProd()==null){
			JOptionPane.showMessageDialog(this, "Error al agregar Producto: Debe seleccionar un Producto");
			return;
		}
		
		if (stock < cantidad){
			JOptionPane.showMessageDialog(this, "Error al agregar el producto: Cantidad no disponible");
			return;		
		}
		if(leerCantidad()== 0){
			JOptionPane.showMessageDialog(this, "La cantidad no debe estar vacía y debe ser un número entero mayor a cero");
			return;
		}
		
		//procesos
		importe = cantidad * precio;
		total += importe;
		//salidas
		
		Object aDatos[] = {codprod, nomprod, cantidad, precio, importe};
		modelo.addRow(aDatos);
	
		txtTotal.setText(String.format("%.2f", total));
		
		//agregar a nuestro arrylist los productos a comprar:
		DetalleBoleta d = new DetalleBoleta(null, codprod, cantidad, precio, importe);
		lstDetalles.add(d);
		System.out.println("cantidad de productos: " + lstDetalles.size());
		
	}
	

	private int obtenerCodVendedor() {		
		try {
			return  FmrAcceso.e.getCodigo();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "No se encontró código de Vendedor");
			return 0;
		}
		
	}

	private int leerCodCliente() {
		
		try {
			return Integer.parseInt(txtCodCliente.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente");
			return 0;
		}
		
	}

	private String obtenerNumBoleta() {		
		
		return new GestionVentas().generaNumBoleta();
	}

	private String obtenerFecha() {
	
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

	private int leerCantidad() {

		try {
			return Integer.parseInt(txtCantidadCompraPro.getText());
		} catch (Exception e) {			
			return 0;
		}
		
	}

	private int leerStock() {
		try {
			return Integer.parseInt(txtStockProducto.getText());
		} catch (Exception e) {			
			return 0;
		}
		
	}

	private double leerPrecio() {
		if(txtPrecioProducto.getText().length()==0){			
			return 0;
		}else{
			return Double.parseDouble(txtPrecioProducto.getText());
		}
		
	}

	private String leerNomProd() {
		// TODO Auto-generated method stub
		return txtDescripcionProducto.getText();
	}

	private String leerCodProd() {
		if(txtCodProducto.getText().length()==0){			
			return null;
		}else{
		return txtCodProducto.getText();}
		}
	}

