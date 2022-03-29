package vista;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import mantenimientos.GestionVentas;
import model.VentaInforme;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;

public class ReporteVentas extends JFrame {

	private JPanel contentPane;
	private JDateChooser txtFecha;
	private JDateChooser txtfecha2;
	private JTable tb_Salida;
	
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteVentas frame = new ReporteVentas();
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
	public ReporteVentas() {
		setTitle("REPORTE DE VENTAS\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 373);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha inicio:");
		lblFecha.setBounds(92, 76, 72, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser();
		txtFecha.setBounds(182, 70, 95, 20);
		contentPane.add(txtFecha);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setBounds(92, 107, 67, 14);
		contentPane.add(lblFechaFin);
		
		txtfecha2 = new JDateChooser();
		txtfecha2.setBounds(182, 101, 95, 20);
		contentPane.add(txtfecha2);
		
		JButton btnConsultarIntevaloDe = new JButton("Consultar");
		btnConsultarIntevaloDe.setBounds(330, 68, 95, 30);
		btnConsultarIntevaloDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				reporteIntervaloxFecha();
			}

			
		});
		contentPane.add(btnConsultarIntevaloDe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 136, 499, 146);
		contentPane.add(scrollPane);
		
		tb_Salida = new JTable();
		tb_Salida.setModel(modelo);
		modelo.addColumn("Num. Boleta");
		modelo.addColumn("Fecha");
		modelo.addColumn("Codigo Cliente");
		modelo.addColumn("Código Vendedor");
		modelo.addColumn("Total Boleta");
		scrollPane.setViewportView(tb_Salida);
		
		JButton btnPdf = new JButton("PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generarPDF();
			}
		});
		btnPdf.setBounds(225, 293, 95, 30);
		contentPane.add(btnPdf);
		
		JLabel lblReporteDeVentas = new JLabel("REPORTE DE VENTAS POR FECHAS");
		lblReporteDeVentas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReporteDeVentas.setBounds(143, 22, 282, 14);
		contentPane.add(lblReporteDeVentas);
		
		cargarFecha();
		
	}
	
	private String leerfecha()  {
		
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	return sdf.format((txtFecha.getDate())); }
	
	private String leerfecha2()  {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format((txtfecha2.getDate())); }
	
	private void reporteIntervaloxFecha() {
		String fecha = leerfecha();
		String fecha2 = leerfecha2();
		
		ArrayList<VentaInforme> listaxIntervaloFecha = new GestionVentas().listadoIntervaloxFecha(fecha, fecha2);
		
		if(listaxIntervaloFecha.size()==0|| listaxIntervaloFecha == null){
			JOptionPane.showMessageDialog(this, "Lista Vacía");
		}
		else{				
			for (VentaInforme v: listaxIntervaloFecha) {				
				Object aDatos[] = {v.getNum_bol(), v.getFch_bol(), v.getCod_cliente(), v.getCod_vendedor(),
						v.getTotal_bol()};
				modelo.addRow(aDatos);
			}
		
	}
	
	}
	
	private String obtenerFecha() {
		
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

	private void generarPDF(){
		if(tb_Salida.getRowCount()== 0){
			JOptionPane.showMessageDialog(this, "Lista Vacia, no se puede generar PDF.");
		}
		else{		
		String nombrearchivo ="reporteVentas.pdf";
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
			
			
			Paragraph pa = new Paragraph("REPORTE DE VENTAS", 
					FontFactory.getFont("arial",15,Font.BOLD));
			pa.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(pa);
			
			Paragraph dt = new Paragraph("Desde: " + leerfecha() + " Hasta: " + leerfecha2(), 
					FontFactory.getFont("arial",11,Font.BOLD));
			dt.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(dt);
			
			doc.add(salto);
			doc.add(salto);

			//tabla:
			
			PdfPTable tabla = new PdfPTable(5);						
			tabla.addCell("NUM. BOLETA");
			tabla.addCell("FECHA");
			tabla.addCell("COD. CLI.");
			tabla.addCell("COD. VEND.");
			tabla.addCell("TOTAL BOLETA");

			for (int i=0; i < tb_Salida.getRowCount(); i++) {					
				tabla.addCell(tb_Salida.getValueAt(i, 0).toString());
				tabla.addCell(tb_Salida.getValueAt(i, 1).toString());
				tabla.addCell(tb_Salida.getValueAt(i, 2).toString());
				tabla.addCell(tb_Salida.getValueAt(i, 3).toString());
				tabla.addCell(tb_Salida.getValueAt(i, 4).toString());
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
	
	void cargarFecha(){		
		Date fechaAct = new Date();		
		txtFecha.setDate(fechaAct);
		txtfecha2.setDate(fechaAct);
	}
	
}
