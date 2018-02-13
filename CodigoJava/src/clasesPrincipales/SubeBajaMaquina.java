package clasesPrincipales;


import Monitor.Monitor;

import java.util.HashMap;

import Logueo.LogDeEventos;


public class SubeBajaMaquina implements Runnable {
	private Monitor monitor;
	private int indiceLog;
	private LogDeEventos log;
	
	public SubeBajaMaquina(Monitor monitor, LogDeEventos log, int indiceLog){
		this.monitor=monitor;
		this.indiceLog=indiceLog;
		this.log=log;
	}
	
	
	
	/**
     * Subir y bajar de la maquina.
     */
	public void run(){
		HashMap<String, String> tablaLogueo=new HashMap<String, String>();
		tablaLogueo.put("arriboA", "0");
		tablaLogueo.put("salgoA", "1");
		tablaLogueo.put("arriboB", "2");
		tablaLogueo.put("salgoB", "3");
		tablaLogueo.put("arriboC", "4");
		tablaLogueo.put("salgoC", "5");
		tablaLogueo.put("arriboD", "6");
		tablaLogueo.put("salgoD", "7");
		
		
		while(true){
			
		}
	}
	

}
