package railSystem;

import monitor.Monitor;


public class FireMultipleTransition implements Runnable {
    private int[] transitions_to_fire;
    private Monitor monitor;

    
/**
 * 
 * @param transiciones_viaje arreglo de enteros que representan las transiciones que disparara el hilo
 * @param monitor
 */
    public FireMultipleTransition(
    		int[] transiciones_viaje, 
    		Monitor monitor
    		) {
        this.transitions_to_fire = transiciones_viaje;
        this.monitor = monitor;
        
    }

    @Override
    public void run() {
        int vueltas=0;
    	while(true) {
    		for(int i=0; i<this.transitions_to_fire.length; i++) {
    			monitor.dispararTransicion(this.transitions_to_fire[i]);
    	        if(transitions_to_fire[i]==36) {
    	        	System.out.println("El tren partio de la estacion B (disparo la transicion: "+this.transitions_to_fire[i]+") - Vuelta: "+vueltas);
    	        }
    	        else if(transitions_to_fire[i]==35) {
    	        	System.out.println("Tren en estacion C (disparo la transicion: "+this.transitions_to_fire[i]+") - Vuelta: "+vueltas);
    	        }
    	        else if(transitions_to_fire[i]==34) {
    	        	System.out.println("El tren partio de la estacion C (disparo la transicion: "+this.transitions_to_fire[i]+") - Vuelta: "+vueltas);
    	        }
    	        else if(transitions_to_fire[i]==21) {
    	        	System.out.println("-- TREN EN BARRERA--");
    	        }
    	        else if(transitions_to_fire[i]==30) {
    	        	System.out.println("Tren en estacion D (disparo la transicion: "+this.transitions_to_fire[i]+") - Vuelta: "+vueltas);
    	        }
    	        else if(transitions_to_fire[i]==19) {
    	        	System.out.println("El tren partio de la estacion D (disparo la transicion: "+this.transitions_to_fire[i]+") - Vuelta: "+vueltas);
    	        }
    	        else if(transitions_to_fire[i]==8) {
    	        	System.out.println("Tren en estacion A (disparo la transicion: "+this.transitions_to_fire[i]+") - Vuelta: "+vueltas);
    	        }
    	        else if(transitions_to_fire[i]==4) {
    	        	System.out.println("El tren partio de la estacion A (disparo la transicion: "+this.transitions_to_fire[i]+") - Vuelta: "+vueltas);
    	        }
    	        else if(transitions_to_fire[i]==16) {
    	        	System.out.println("-- TREN EN BARRERA--");
    	        }
    	        else if(transitions_to_fire[i]==5) {
    	        	vueltas=vueltas+1;
    	        	System.out.println("Tren en estacion B (disparo la transicion: "+this.transitions_to_fire[i]+") - Vuelta: "+vueltas);
    	        }

    		}
    	}
    }

}

