package vista;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimientos.GestionProveedores;
import model.Proveedores;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ReporteProveedores extends JFrame {

	private JPanel contentPane;
	private JTable tblProveedor;
	private JComboBox cboProveedor;
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteProveedores frame = new ReporteProveedores();
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
	public ReporteProveedores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelProveedor = new JLabel("Nombre del Proveedor");
		lblNombreDelProveedor.setBounds(24, 31, 152, 14);
		contentPane.add(lblNombreDelProveedor);
		
		cboProveedor = new JComboBox();
		cboProveedor.setBounds(162, 28, 161, 20);
		contentPane.add(cboProveedor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 749, 183);
		contentPane.add(scrollPane);
		
		tblProveedor = new JTable();
		tblProveedor.setModel(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Telefono");
		modelo.addColumn("Direccion");
		modelo.addColumn("Correo");
		modelo.addColumn("Descripcion");
		scrollPane.setViewportView(tblProveedor);
		tblProveedor.getColumn("Codigo").setMaxWidth(50);
		tblProveedor.getColumn("Telefono").setMaxWidth(70);
		tblProveedor.getColumn("Nombre").setMaxWidth(70);
		tblProveedor.getColumn("Direccion").setMaxWidth(120);
		tblProveedor.getColumn("Correo").setMaxWidth(150);
		tblProveedor.getColumn("Direccion").setMinWidth(120);
		tblProveedor.getColumn("Correo").setMinWidth(150);

		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listado();
			}
		});
		btnConsultar.setBounds(335, 27, 89, 23);
		contentPane.add(btnConsultar);
		
		JButton btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirPDF();
			}
		});
		btnReporte.setBounds(434, 27, 103, 23);
		contentPane.add(btnReporte);
		llenarCombo(); 
	}
	void llenarCombo() {
		ArrayList<Proveedores> lstProveedores= new GestionProveedores().listado();
		if (lstProveedores==null) {
			JOptionPane.showMessageDialog(this, "Tabla Proveedores, vacía");
		} else {
			cboProveedor.addItem("Seleccione");
			for(Proveedores t: lstProveedores) {
				cboProveedor.addItem(t.getNombre());
			}
		}
	}
	public void listado() {
		String nombre=leerTipo();
		ArrayList<Proveedores> lstProveedores =new GestionProveedores().listadoxnombre(nombre);
		if (lstProveedores==null || lstProveedores.size()==0) {
			JOptionPane.showMessageDialog(this,  "Listado vacío");
		} else {
			modelo.setRowCount(0);
			for(Proveedores n: lstProveedores) {
				Object aDatos[]= {n.getCodigo(), n.getNombre(),n.getTelefono(),n.getDireccion(),n.getCorreo(),n.getDescripcion()};
				modelo.addRow(aDatos);
				
			}
		}
		
		String codbus= (String) cboProveedor.getSelectedItem();
	}
   
	String fecha= new SimpleDateFormat("yyyy/MM/dd").format(new Date());

	private String leerTipo() {
		// validacion
		return (String) cboProveedor.getSelectedItem();}
	void imprimirPDF() {
		if(tblProveedor.getRowCount()== 0){
			JOptionPane.showMessageDialog(this, "Lista Vacia, no se puede generar PDF.");
		}else{		
			String nombrearchivo ="ListaProveedores.pdf";
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

				for (int i=0; i < tblProveedor.getRowCount(); i++) {					
					tabla.addCell(tblProveedor.getValueAt(i, 0).toString());
					tabla.addCell(tblProveedor.getValueAt(i, 1).toString());
					tabla.addCell(tblProveedor.getValueAt(i, 2).toString());
					tabla.addCell(tblProveedor.getValueAt(i, 3).toString());
					tabla.addCell(tblProveedor.getValueAt(i, 4).toString());
					tabla.addCell(tblProveedor.getValueAt(i, 5).toString());							
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

}
