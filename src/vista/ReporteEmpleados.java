package vista;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import mantenimientos.GestionClientes;
import mantenimientos.GestionEmpleados;
import model.ReportexTipoEmpleado;
import model.Tipos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class ReporteEmpleados extends JFrame {

	private JPanel contentPane;
	private JTable tblSalida;
	private JComboBox cboTipoEmpleado;
	
	

	//Variable global de tabla
	DefaultTableModel modelo= new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteEmpleados frame = new ReporteEmpleados();
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
	public ReporteEmpleados() {
		setTitle("Listado de Empleados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListadoDeEmpleados = new JLabel("Listado de Empleados:");
		lblListadoDeEmpleados.setBounds(10, 11, 145, 14);
		contentPane.add(lblListadoDeEmpleados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 414, 153);
		contentPane.add(scrollPane);
		
		tblSalida = new JTable();
		tblSalida.setModel(modelo);
		modelo.addColumn("Codigo Empleado");
		modelo.addColumn("Nombre Empleado");
		modelo.addColumn("Tipo Empleado");
		scrollPane.setViewportView(tblSalida);
		
		cboTipoEmpleado = new JComboBox();
		cboTipoEmpleado.setBounds(10, 36, 123, 20);
		contentPane.add(cboTipoEmpleado);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listadoxtipo();
			}
		});
		btnListar.setBounds(163, 30, 89, 23);
		contentPane.add(btnListar);
		
		JButton btnPdf = new JButton("PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generarPDF();
			}
		});
		btnPdf.setBounds(163, 228, 89, 23);
		contentPane.add(btnPdf);
		llenacombo();
	}
	
	
	void listadoxtipo(){
		int tipo = leerTipoEmpleado();
			ArrayList<ReportexTipoEmpleado> lstEmpleado=new GestionEmpleados().listadoxTipo(tipo);
			
			if(lstEmpleado== null || lstEmpleado.size()==0){
				JOptionPane.showMessageDialog(this,">>>>Listado Vacío<<<<");
			}else {	
				JOptionPane.showMessageDialog(this, "MOSTRANDO LISTADO FARMA SOLUTIONS/EMPLEADOS");
				System.out.print("IMPRIMIENDO");
				modelo.setRowCount(0);
				
				for(ReportexTipoEmpleado r: lstEmpleado){
					Object aDatos[]={r.getCodigo(), r.getNombrecompleto(), r.getDescripcion()};
					modelo.addRow(aDatos);
		
								
				}
			}
	}
	
	private int leerTipoEmpleado(){
		return cboTipoEmpleado.getSelectedIndex();
	}
	void llenacombo(){
		ArrayList<Tipos> lsttipo= new  GestionEmpleados().listadoTipos();
		
		//Pasa listado a combo
		if(lsttipo==null || lsttipo.size()==0){
			JOptionPane.showMessageDialog(this, "Tabla Vacía");
		}
		else{
			cboTipoEmpleado.addItem("Seleccione Tipo");
			for (Tipos t: lsttipo){
				cboTipoEmpleado.addItem(t.getIdTipo()+ "-"+ t.getDescripcion());
			}
		}
	}
	
	private String obtenerFecha() {
		
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

	private void generarPDF(){
		if(tblSalida.getRowCount()== 0){
			JOptionPane.showMessageDialog(this, "Lista Vacia, no se puede generar PDF.");
		}
		else{		
		String nombrearchivo ="ListaEmpleados.pdf";
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
			
			
			Paragraph pa = new Paragraph("REPORTE DE EMPLEADOS: " + cboTipoEmpleado.getSelectedItem().toString().substring(2).toUpperCase(), 
					FontFactory.getFont("arial",15,Font.BOLD));
			pa.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(pa);
			
			doc.add(salto);
			doc.add(salto);

			//tabla:
			
			PdfPTable tabla = new PdfPTable(3);						
			tabla.addCell("CÓDIGO");
			tabla.addCell("NOMBRE");
			tabla.addCell("TIPO");				

			for (int i=0; i < tblSalida.getRowCount(); i++) {					
				tabla.addCell(tblSalida.getValueAt(i, 0).toString());
				tabla.addCell(tblSalida.getValueAt(i, 1).toString());
				tabla.addCell(tblSalida.getValueAt(i, 2).toString());							
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
