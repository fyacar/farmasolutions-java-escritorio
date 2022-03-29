package hilos;

import javax.swing.JFrame;

import vista.FmrAcceso;
public class HiloContador extends  Thread {
	private JFrame ventana;
	
	
	public HiloContador(JFrame ventana){	
		this.ventana= ventana;
	}

	public void run(){
		
		for (int i = 30 ;i >=0; i--){
		FmrAcceso.lblTiempo.setText(i+"s");			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Error en la Pausa del contador: "+ e.getMessage());
			} //
		}
		ventana.dispose();
	}
	


}
