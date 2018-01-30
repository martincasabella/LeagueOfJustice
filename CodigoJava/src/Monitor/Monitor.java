package Monitor;

import java.util.concurrent.Semaphore;
import java.util.List;
//Se aplic� SINGLETON. 

public class Monitor { 
	//Elementos del monitor.
	private Politica politica;
	private int cantTransiciones; //Igual a cantidad de colas.
    private Cola colas[];
    private RedDePetri rdp;
    private Semaphore mutex;
    
    //Aplicaci�n de Singleton.
    private static final Monitor instance = new Monitor();
	 private Monitor(){
		//Sem�foro binario a la entrada del monitor.
		 //Fairness true: FIFO en cola de hilos bloqueados.
	       mutex=new Semaphore(1,true);
	       cantTransiciones=0;
	       //La red de petri y las transiciones se configuran posteriormente.
	  }

	
	public static Monitor getInstance(){return instance;}
	
	
	public void setNumeroTransiciones(int n){
		this.cantTransiciones=n;
	}

	public int getNumeroTransiciones(){
		return this.cantTransiciones;
	}
	
	
	public void configRdp(String path){
		try {
	            mutex.acquire();
	    }
		catch(InterruptedException e){
			e.printStackTrace();
		}
		this.rdp=new RedDePetri(path);
		this.setNumeroTransiciones(rdp.getNumeroTransiciones());
		colas= new Cola[this.getNumeroTransiciones()];
        for(int i=0;i<this.getNumeroTransiciones();i++){ 
            colas[i]=new Cola(); //Inicializaci�n de colas.
        }
		mutex.release();
	}
	
	public void setPolitica(int Modo){
		try{
			mutex.acquire(); //Adquiero acceso al monitor.
		}
		catch(InterruptedException e){
			e.printStackTrace();
			return;
		}
		this.politica=new Politica(Modo);
		mutex.release();
	}
	
	
	
	
	//Metodos basados en diagrama de secuencia.
	public void dispararTransicion(int transicion) throws InterruptedException{
		
		try{
			mutex.acquire(); //Adquiero acceso al monitor.
		}
		catch(InterruptedException e){
			e.printStackTrace();
			return;
		}
		boolean k=true;
		
		while(k){
			k=rdp.disparar(transicion); //Disparo red de petri.
			if(k){ //K=true verifica el estado de la red.
				List<Integer> Vs=rdp.getSensibilizadas(); //get transiciones sensibilizadas
				List<Integer> Vc=quienesEstanEnColas(); //get transiciones sensibilizadas
				try{
					List<Integer> m= andVector(Vs, Vc);
				}
				catch(IndexOutOfBoundsException e){
					m=null;
					e.printStackTrace();
				}	
				if(m!=0){
					
					int transicionADisparar=politica.cualDisparar(m);
					try{
						colas[transicionADisparar].resume(); //Sale de una cola de condici�n.
					}
					catch(IndexOutOfBoundsException e){e.printStackTrace();}
					
					mutex.release();
	                return;
				}
				else{
					k=false;
				}
			}
			else{
				mutex.release();
				try{
					colas[transicion].delay(); //Se encola en una cola de condicion.
				}
				catch(Exception e){ //Puede haber m�s de un tipo de Excepci�n.
					e.printStackTrace();
				}
			
                return;
			}
		}
		
		mutex.release(); //Libero al monitor.
		return;
	}
	
	
	
}
